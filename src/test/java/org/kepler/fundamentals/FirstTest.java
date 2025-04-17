package org.kepler.fundamentals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstTest {
    @Test
    void helloJUnit5() {
        String actual = "JUnit5";
        assertEquals("JUnit5", actual);
    }
}
