package org.kepler.fundamentals.tests.meta_annotation;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("slow")
public class TagFilterTest {
    @Test
    @Tag("integration")
    void longRunningTest() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofMillis(2000));
        assertEquals(4 % 2, 2);
    }
}
