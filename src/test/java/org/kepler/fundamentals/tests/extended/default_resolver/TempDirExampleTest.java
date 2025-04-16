package org.kepler.fundamentals.tests.extended.default_resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;

class TempDirExampleTest {
    @Test
    void testTempFile(@TempDir Path tempDir) { // Built-in ParameterResolver
        Path file = tempDir.resolve("test.txt");
        // Use the file in the test
    }
}