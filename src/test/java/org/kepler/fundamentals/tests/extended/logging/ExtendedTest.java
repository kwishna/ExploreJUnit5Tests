package org.kepler.fundamentals.tests.extended.logging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

// Theory: Extensions add custom behavior to tests. Apply extension to a test class
@ExtendWith(LoggingExtension.class)
public class ExtendedTest {
    @Test
    void withExtensionTest() {
        assumeTrue(System.getProperty("os.name").contains("Windows"));
    }
}

/*
Extensions allow you to customize JUnit5’s test execution lifecycle. They can:
    - Inject dependencies into tests (via ParameterResolver).
    - Add custom logic before/after tests (e.g., BeforeEachCallback).
    - Provide arguments for parameterized tests (via ArgumentsProvider).
 */
