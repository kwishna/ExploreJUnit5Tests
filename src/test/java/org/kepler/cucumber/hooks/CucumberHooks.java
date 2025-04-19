package org.kepler.cucumber.hooks;

import io.cucumber.java.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberHooks {
    private static final Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @BeforeStep
    public static void beforeStep() {
        logger.info("----- before each step -----");
    }

    @AfterStep
    public static void afterStep(Scenario scenario) {
        logger.info("----- after each step -----");
    }

    @Before
    public static void beforeScenario() {
        logger.info("----- before scenario -----");
    }

    @After
    public static void afterScenario(Scenario scenario) {
        if(scenario.isFailed()) {
            logger.info("----- after scenario -----");
            scenario.log("Failed scenario: "+scenario.getName() + " in file: " + scenario.getUri());
        }
    }

    @BeforeAll
    public static void beforeAll() {
        logger.info("----- before all scenario -----");
    }

    @AfterAll
    public static void afterAll(Scenario scenario) {
        logger.info("----- after all scenario -----");
    }
}
