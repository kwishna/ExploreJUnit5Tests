package org.kepler.fundamentals.tags;

import org.junit.jupiter.params.provider.ArgumentsSource;
import org.kepler.fundamentals.extensions.providers.ExcelArgumentsProvider;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(ExcelArgumentsProvider.class)
public @interface ExcelSource {
    String filePath();
    String sheetName() default "Sheet1";
}
