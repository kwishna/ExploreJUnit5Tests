package org.kepler.fundamentals.extensions.listeners;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.util.Optional;
import java.util.logging.Logger;

public class BaseWatcher implements TestWatcher, TestExecutionListener, BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {
    private static final Logger logger = Logger.getLogger(BaseWatcher.class.getName());

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.severe("Test failed: " + context.getDisplayName() + " - " + cause.getMessage());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> cause) {
        logger.severe("Test disabled: " + context.getDisplayName() + " - " + cause);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("Test successful: " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.info("Test aborted: " + context.getDisplayName());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        logger.info("After all : " + context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        logger.info("After each : " + context.getDisplayName());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        logger.info("Before all : " + context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        logger.info("Before each : " + context.getDisplayName());
    }

    @Override
    public void testPlanExecutionStarted(TestPlan plan) {
        TestExecutionListener.super.testPlanExecutionStarted(plan);
    }

    @Override
    public void testPlanExecutionFinished(TestPlan plan) {
        TestExecutionListener.super.testPlanExecutionFinished(plan);
    }
}
