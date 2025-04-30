package org.kepler.fundamentals.tests.extended.timings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
public class TimedTest {
    @Test
    void slowTest() throws InterruptedException {
        Thread.sleep(1000);
    }
}