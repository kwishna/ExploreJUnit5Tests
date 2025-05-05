package org.kepler.fundamentals.tests.extended.exception_handler;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class LogExceptionExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        // Log the exception instead of failing
        System.err.println("Test failed with exception: " + throwable.getMessage());
        // Optionally rethrow to mark the test as failed
        throw throwable;
    }
}