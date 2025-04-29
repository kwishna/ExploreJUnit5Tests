package org.kepler.fundamentals.tests.extended.timings;

import org.junit.jupiter.api.extension.*;

public class TimingExtension implements BeforeEachCallback, AfterEachCallback {
    private long startTime;

    @Override
    public void beforeEach(ExtensionContext context) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Test " + context.getDisplayName() + " took " + duration + "ms");
    }
}

