package org.kepler.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.*;

class AllureJunit5Test {

    @Test
    @DisplayName("Test Authentication")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void testAuthentication() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testAuthenticationAPI() {
        Allure.getLifecycle().updateTestCase(result -> result.setName("Test Authentication"));
        Allure.description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.");
        Allure.label("severity", "critical");
        Allure.label("owner", "John Doe");
        Allure.link("Website", "https://dev.example.com/");
        Allure.issue("AUTH-123", "https://example.com/issues/AUTH-123");
        Allure.tms("TMS-456", "https://example.com/tms/TMS-456");
    }
}
