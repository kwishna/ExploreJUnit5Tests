package org.kepler.fundamentals.tests.extras.default_method;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public interface DefaultMethodTests {
    @Test
    default void testHasValidRole() {
        assertTrue(!getRole().isEmpty()); // Default test logic
    }

    String getRole(); // To be implemented by concrete classes
}

