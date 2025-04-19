package org.kepler.fundamentals.tests.conditionals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionalTest {
    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void runOnlyOnMacTest() {
        assertEquals(1 % 0, 0);
    }
}
