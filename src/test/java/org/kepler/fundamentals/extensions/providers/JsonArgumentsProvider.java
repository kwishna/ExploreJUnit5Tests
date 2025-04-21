package org.kepler.fundamentals.extensions.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.kepler.fundamentals.tags.JsonSource;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class JsonArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        JsonSource jsonSource = context.getRequiredTestMethod()
                .getAnnotation(JsonSource.class);

        ObjectMapper mapper = new ObjectMapper();
        Fruit[] fruits = mapper.readValue(new File(jsonSource.filePath()), Fruit[].class);

        return Arrays.stream(fruits)
                .map(fruit -> Arguments.of(fruit.name, fruit.quantity, fruit.price));
    }

    static class Fruit {
        public String name;
        public int quantity;
        public double price;
    }

    /*
    private static final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());  // Supports Java 8+ types like LocalDate

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        JsonSource annotation = context.getRequiredTestMethod()
            .getAnnotation(JsonSource.class);

        // Read JSON array into a typed array (e.g., Fruit[])
        Class<?> targetType = getFirstParameterType(context);
        Object[] items = (Object[]) mapper.readValue(
            new File(annotation.filePath()),
            mapper.getTypeFactory().constructArrayType(targetType)
        );

        return Stream.of(items).map(Arguments::of);
    }

    private Class<?> getFirstParameterType(ExtensionContext context) {
        return context.getRequiredTestMethod()
            .getParameterTypes()[0];  // First parameter of the test method
    }
     */
}