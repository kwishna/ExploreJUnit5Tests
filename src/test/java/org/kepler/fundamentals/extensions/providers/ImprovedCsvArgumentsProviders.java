package org.kepler.fundamentals.extensions.providers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.kepler.fundamentals.tags.CustomCsvSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ImprovedCsvArgumentsProviders implements ArgumentsProvider, AnnotationConsumer<CustomCsvSource> {

    private String[] filePath;
    private Class<?> targetType;
    private char delimiter;
    private boolean skipHeader;
    private CsvParserSettings settings;

    @Override
    public void accept(CustomCsvSource annotation) {
        this.filePath = annotation.filePath();
        this.targetType = annotation.type();
        this.delimiter = annotation.delimiter();
        this.skipHeader = annotation.skipHeader();

        settings = new CsvParserSettings();
        settings.getFormat().setDelimiter(annotation.delimiter());
        settings.getFormat().setLineSeparator(annotation.lineSeparator());
        settings.getFormat().setQuote('"');
        settings.getFormat().setQuoteEscape('"');
        settings.setEmptyValue("");
        settings.setAutoConfigurationEnabled(false);
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader reader = mapper.readerFor(targetType);
        List<Arguments> allArguments = new ArrayList<>();

        for (String path : filePath) {
            try (InputStream is = getClass().getResourceAsStream(path)) {
                if (is == null) {
                    throw new IllegalArgumentException("CSV file not found: " + path);
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                if (skipHeader) {
                    bufferedReader.readLine(); // Skip the first line (header)
                }

                MappingIterator<?> iterator = reader.readValues(bufferedReader);
                iterator.forEachRemaining(obj -> allArguments.add(Arguments.of(obj)));
            } catch (IOException e) {
                throw new RuntimeException("Failed to read or parse CSV file: " + path, e);
            }
        }

        return allArguments.stream();
    }
}
