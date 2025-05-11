package org.kepler.fundamentals.extensions.providers;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.Stream;

public class CsvArgumentsProvider implements ArgumentsProvider {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        CsvFileSource annotation = context.getRequiredTestMethod()
                .getAnnotation(CsvFileSource.class);

        Class<?> targetType = getFirstParameterType(context);
        return readCsv(annotation.resources()[0], targetType)
                .stream()
                .map(Arguments::of);
    }

    private Class<?> getFirstParameterType(ExtensionContext context) {
        return context.getRequiredTestMethod()
                .getParameterTypes()[0];  // First parameter of the test method
    }

    private List<Object> readCsv(String filePath, Class<?> targetType) throws Exception {
        List<Object> items = new ArrayList<>();
        try (Reader reader = new InputStreamReader(
                getClass().getResourceAsStream(filePath), StandardCharsets.UTF_8)) {

            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : parser) {
                Object item = convertRecordToType(record, targetType);
                items.add(item);
            }
        }
        return items;
    }

    private Object convertRecordToType(CSVRecord record, Class<?> targetType) throws Exception {
        if (targetType == String.class) {
            return record.get(0);  // Single-column CSV → String
        }

        // For custom types (e.g., Person, Fruit)
        Constructor<?> constructor = targetType.getDeclaredConstructor();
        Object instance = constructor.newInstance();

        for (Field field : targetType.getDeclaredFields()) {
            field.setAccessible(true);
            String value = record.get(field.getName());
            field.set(instance, convertValue(value, field.getType()));
        }

        return instance;
    }

    private Object convertValue(String value, Class<?> fieldType) {
        if (fieldType == String.class) return value;
        if (fieldType == int.class) return Integer.parseInt(value);
        if (fieldType == LocalDate.class) return LocalDate.parse(value, DATE_FORMATTER);
        if (fieldType.isEnum()) return Enum.valueOf((Class<Enum>) fieldType, value);
        throw new IllegalArgumentException("Unsupported type: " + fieldType);
    }
}