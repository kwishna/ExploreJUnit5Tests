package org.kepler.cucumber.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    @Given("User is on login page {string}")
    public void navigateToLoginPage(String url) {
        logger.info("User is on login page: {}", url);
    }

    @When("User enters username as {string} and password as {string}")
    public void entersUsernamesAndPassword(String userName, String password) {
        logger.info("User enters username as {} and password as {}", userName, password);
    }

    @Then("User should be able to see error message {string}")
    public void shouldBeAbleToSeeErrorMessage(String expectedErrorMessage) {
        logger.info("User should be able to see error message {}", expectedErrorMessage);
    }

    @Then("User should be able to login successfully and new page open")
    public void shouldBeAbleToLoginSuccessfully() {
        logger.info("User should be able to login successfully");
    }
}