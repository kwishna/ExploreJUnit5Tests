package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodSourceTest {
    static Stream<Arguments> provideCalculatorInputs() {
        return Stream.of(
                Arguments.of(1, 2, 3),  // a=1, b=2, expected=3
                Arguments.of(5, -3, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCalculatorInputs")
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}