package org.kepler.fundamentals.tests.meta_annotation;

import org.kepler.fundamentals.tags.FastIntegrationTag;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetaAnnotationTest {
    // Combines @Test + @Tag("fast") + @Tag("integration")
    @FastIntegrationTag
    void fastIntegrationTest() {
        assertTrue(true, "Fast test");
    }
}
