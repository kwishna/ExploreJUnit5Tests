package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.kepler.fundamentals.extensions.providers.FibonacciArgumentsProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FibonacciTest {
    @ParameterizedTest
    @ArgumentsSource(FibonacciArgumentsProvider.class)
    void testFibonacci(int input, int expected) {
        assertEquals(expected, 6);
    }
}

