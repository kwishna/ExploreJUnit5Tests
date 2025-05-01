package org.kepler.fundamentals.tests.extended.listen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kepler.fundamentals.extensions.listeners.ResultWatcherListener;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(ResultWatcherListener.class)
class WatcherTest {
    @Test
    void passingTest() { /* ... */ }

    @Test
    void failingTest() { fail("Intentional failure"); }
}

