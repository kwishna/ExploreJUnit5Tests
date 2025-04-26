package org.kepler.fundamentals.tests.extended.retry;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.Method;

class RetryHandler implements TestExecutionExceptionHandler, InvocationInterceptor {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (context.getExecutionException().isPresent()) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Retrying: " + i);
                try {
                    context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
                    return;
                } catch (Exception e) {
                    // Log retry attempt
                }
            }
        }
        throw throwable;
    }

    @Override
    public void interceptTestMethod(
            Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext
    ) throws Throwable {
        int maxRetries = 3;
        for (int i = 1; i <= maxRetries; i++) {
            try {
                invocation.proceed();
                return;
            } catch (Exception e) {
                if (i == maxRetries) throw e;
                System.out.println("Retry #" + i + " for " + extensionContext.getDisplayName());
            }
        }
    }
}
