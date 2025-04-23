package org.kepler.fundamentals.tests.lifecycle;

import org.junit.jupiter.api.*;

class LifecycleTest {
    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void setupEach() {
        System.out.println("Before each test");
    }

    @Test
    void lifecycleTest() {
        System.out.println("Lifecycle Test");
    }

    @AfterEach
    void tearDownEach() {
        System.out.println("After each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests");
    }
}
