package org.kepler.fundamentals.tests.dynamic;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicTests {
    @TestFactory
    Stream<DynamicTest> dynamicTests() {
        return Stream.of("A", "B", "C")
                .map(str ->
                        DynamicTest.dynamicTest(
                        "Test " + str,
                            () -> assertTrue(str.length() == 1)
                        )
                );
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        // Input data: pairs of numbers and their expected squares
        List<Integer> inputs = List.of(1, 2, 3, 4);
        List<Integer> expectedOutputs = List.of(1, 4, 9, 16);

        return IntStream.range(0, inputs.size())
                .mapToObj(
                        i -> DynamicTest.dynamicTest(
                        "Square of " + inputs.get(i),
                        () -> assertEquals(Optional.ofNullable(expectedOutputs.get(i)), Math.pow(inputs.get(i), 2))
                        )
                );
    }
}