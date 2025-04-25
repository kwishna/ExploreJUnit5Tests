package org.kepler.fundamentals.tests.extended.retry;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;

class RetryInterceptor implements InvocationInterceptor {
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
