package org.kepler.fundamentals.tests.extended.exception_handler;

import org.dbunit.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kepler.fundamentals.extensions.resolvers.DatabaseConnectionResolver;

// Purpose: Handle exceptions thrown during test execution (e.g., retry flaky tests).
@ExtendWith(LogExceptionExtension.class)
class ExceptionHandlingTest {
    @Test
    void testWithException() {
        throw new RuntimeException("Oops!");
    }

    // Step 2: Use the resolver in a test class
    @ExtendWith(DatabaseConnectionResolver.class)
    public static class DatabaseTest {
        @Test
        void databaseQueryTest(DatabaseConnection connection) { // Parameter injected here!
           // Assertions
        }
    }
}
