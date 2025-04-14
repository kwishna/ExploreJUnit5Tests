package org.kepler.fundamentals.tests.extended.retry;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kepler.fundamentals.extensions.providers.RetryTestInvocationContextProvider;

public class RetryTestTemplateTest {
    @TestTemplate
    @ExtendWith(RetryTestInvocationContextProvider.class)
    void flakyTest() {
        // May fail intermittently
    }
}
