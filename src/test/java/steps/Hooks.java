package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverFactory.getDriver();
    }

    @After
    public void afterScenario() {
        DriverFactory.quitDriver();
    }
}
