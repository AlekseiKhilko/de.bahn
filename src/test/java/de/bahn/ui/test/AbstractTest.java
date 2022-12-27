package de.bahn.ui.test;

import de.bahn.ui.driver.DriverSingleton;
import de.bahn.ui.utils.TestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class AbstractTest {

    @BeforeTest
    public void logIn() {

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}