package org.kepler.fundamentals.tags;

import org.junit.jupiter.params.provider.ArgumentsSource;
import org.kepler.fundamentals.extensions.providers.JsonArgumentsProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ArgumentsSource(JsonArgumentsProvider.class)
public @interface ImprovedJsonSource {
    String filePath();

    Class<?> type();
}
