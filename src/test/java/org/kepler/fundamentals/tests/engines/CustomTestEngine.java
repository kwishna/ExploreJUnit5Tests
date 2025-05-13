package org.kepler.fundamentals.tests.engines;

import org.junit.platform.engine.*;

public class CustomTestEngine implements TestEngine {
    @Override
    public String getId() {
        return "custom-engine";
    }

    @Override
    public TestDescriptor discover(EngineDiscoveryRequest request, UniqueId uniqueId) {
        // Discover tests here
        return null;
    }

    @Override
    public void execute(ExecutionRequest request) {
        // Execute tests here
    }
}