package org.kepler.fundamentals.tests.paramterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParameterizedExampleTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void testOddNumbers(int number) {
        assertTrue(number % 2 != 0);
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "hello,HELLO"})
    void upperCaseTest(String input, String expected) {
        assertEquals(expected, input.toUpperCase());
    }
}
