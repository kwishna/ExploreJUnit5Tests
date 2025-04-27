package org.kepler.fundamentals.tests.extended.retry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

@ExtendWith({RetryHandler.class, RetryInterceptor.class})
public class RetryTest {
    @Test
    void flakyTest() {
        Assertions.assertTrue(false);
    }
}
