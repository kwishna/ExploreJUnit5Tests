package org.kepler.fundamentals.tags;

import org.junit.jupiter.params.provider.ArgumentsSource;
import org.kepler.fundamentals.extensions.providers.CsvArgumentsProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ArgumentsSource(CsvArgumentsProvider.class)
public @interface CustomCsvSource {
    String[] filePath();
    Class<?> type();
    char delimiter() default ',';
    String lineSeparator() default "\n";
    boolean skipHeader() default true;
}
