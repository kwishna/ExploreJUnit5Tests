package org.kepler.fundamentals.extensions.providers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.kepler.fundamentals.tags.ImprovedJsonSource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

public class ImprovedJsonArgumentsProviders implements ArgumentsProvider, AnnotationConsumer<ImprovedJsonSource> {
    /*
    Your CustomCsvSource annotation has these fields:
        String[] filePath();
        Class<?> type();
        char delimiter() default ',';
        boolean skipHeader() default true;
        String lineSeparator() default "\n";
     */
    private String filePath;
    private Class<?>  targetType;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void accept(ImprovedJsonSource annotation) {
        if (null == annotation.filePath()) {
            throw new RuntimeException("source file path should not be null.");
        }
        this.filePath = annotation.filePath();
        this.targetType = annotation.type();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // Load JSON from classpath
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
            if (is == null) {
                throw new IllegalArgumentException("JSON file not found on classpath: " + filePath);
            }
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Construct collection type for List<targetType>
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, targetType);

            // Deserialize JSON array into list of objects
            List<?> list = objectMapper.readValue(json, listType);

            // Map each object into an Arguments instance
            return list.stream().map(Arguments::of);
        }
        catch (Exception e) {
            return Stream.empty();
        }
    }
}
