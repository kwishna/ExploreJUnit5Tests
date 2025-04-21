package org.kepler.fundamentals.tags;

import org.junit.jupiter.params.provider.ArgumentsSource;
import org.kepler.fundamentals.extensions.providers.JsonArgumentsProvider;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonArgumentsProvider.class)
public @interface JsonSource {
    String filePath();
}
