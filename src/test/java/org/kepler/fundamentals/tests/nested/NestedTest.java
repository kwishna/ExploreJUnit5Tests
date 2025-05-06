package org.kepler.fundamentals.tests.nested;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedTest{
    @Nested
    class WhenNew {
        @Test
        void testNestedOne() {
            assertEquals(3 % 2, 1);
        }

        @Nested
        class ThenNew {
            @Test
            void testNestedTwo() {
                assertEquals(3 % 2, 1);
            }
        }
    }

    @Test
    void testOne() {
        assertEquals(3 % 2, 2);
    }
}
