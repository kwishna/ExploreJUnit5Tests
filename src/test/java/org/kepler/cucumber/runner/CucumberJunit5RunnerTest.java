package org.kepler.cucumber.runner;

import org.junit.platform.suite.api.*;
import static io.cucumber.core.options.Constants.*;

@Suite(failIfNoTests = true)
@SuiteDisplayName("Cucumber JUnit5")
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/org/kepler/cucumber/features")
@SelectClasspathResource("src/test/java/org/kepler/cucumber/stepDefs")
@ConfigurationParameters({
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.kepler.cucumber.hooks"),
        @ConfigurationParameter(key = OBJECT_FACTORY_PROPERTY_NAME, value = "io.cucumber.picocontainer.PicoFactory"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.kepler.cucumber.stepDefs"),
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/java/org/kepler/cucumber/features"),
        @ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false"),
//        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/cucumber-results/timeline,html:build/cucumber-results/cucumber-report.html,com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,timeline:target/cucumber-results/timeline,html:target/cucumber-results/cucumber-report.html")
})
//@ExcludeClassNamePatterns({"*Disabled*"})
@ExcludePackages({"org.kepler.allure"})
@ExcludeTags({"skip"})
@IncludeTags("all")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ui")
public class CucumberJunit5RunnerTest {
}

/*
    public static final String ANSI_COLORS_DISABLED_PROPERTY_NAME = "cucumber.ansi-colors.disabled";
    public static final String CUCUMBER_PROPERTIES_FILE_NAME = "cucumber.properties";
    public static final String EXECUTION_DRY_RUN_PROPERTY_NAME = "cucumber.execution.dry-run";
    public static final String EXECUTION_LIMIT_PROPERTY_NAME = "cucumber.execution.limit";
    public static final String EXECUTION_ORDER_PROPERTY_NAME = "cucumber.execution.order";
    public static final String WIP_PROPERTY_NAME = "cucumber.execution.wip";
    public static final String FEATURES_PROPERTY_NAME = "cucumber.features";
    public static final String FILTER_NAME_PROPERTY_NAME = "cucumber.filter.name";
    public static final String FILTER_TAGS_PROPERTY_NAME = "cucumber.filter.tags";
    public static final String GLUE_PROPERTY_NAME = "cucumber.glue";
    public static final String OBJECT_FACTORY_PROPERTY_NAME = "cucumber.object-factory";
    public static final String UUID_GENERATOR_PROPERTY_NAME = "cucumber.uuid-generator";
    static final String OPTIONS_PROPERTY_NAME = "cucumber.options";
    public static final String PLUGIN_PROPERTY_NAME = "cucumber.plugin";
    public static final String PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME = "cucumber.publish.enabled";
    public static final String PLUGIN_PUBLISH_TOKEN_PROPERTY_NAME = "cucumber.publish.token";
    public static final String PLUGIN_PUBLISH_URL_PROPERTY_NAME = "cucumber.publish.url";
    public static final String PLUGIN_PUBLISH_PROXY_PROPERTY_NAME = "cucumber.publish.proxy";
    public static final String PLUGIN_PUBLISH_QUIET_PROPERTY_NAME = "cucumber.publish.quiet";
    public static final String SNIPPET_TYPE_PROPERTY_NAME = "cucumber.snippet-type";
 */

// Alternate Ways To Pass Cucumber Parameters:-
//1) mvn -Dtest="org.kepler.runner.CucumberJunit5Test" test
//2) mvn clean install -Dcucumber.glue="features.step_definitions" -Dcucumber.features="src/test/java/features"
//3) junit-platform.properties


