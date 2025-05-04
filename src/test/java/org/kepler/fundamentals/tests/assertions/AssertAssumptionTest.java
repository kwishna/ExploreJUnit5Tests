package org.kepler.fundamentals.tests.assertions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

// Theory: Assertions validate results; assumptions skip tests based on conditions.
public class AssertAssumptionTest {
    @Test
    void testAssertions() {
        assertAll(
                () -> assertEquals(4, 2 + 2),
                () -> assertTrue(5 > 4)
        );
    }

    @Test
    void testAssumption() {
        assumeTrue(System.getProperty("os.name").contains("Windows"));
    }
}
