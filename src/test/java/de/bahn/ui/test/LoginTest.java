package de.bahn.ui.test;

import de.bahn.ui.driver.DriverSingleton;
import de.bahn.ui.pages.LoginPage;
import de.bahn.ui.steps.LoginStep;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {
    LoginPage page;
    String TEST_USERNAME = "mylogin";
    String TEST_PASSWORD = "12345678";

    @BeforeMethod
    public void beforeMethod() {
        page = new LoginStep()
                .openLoginForm();
    }

    @AfterMethod
    public void afterMethod() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void existsInputUsernaemTest() {
        Assert.assertTrue(page.existsInputUsernaem());
    }

    @Test
    public void existsInputPasswordTest() {
        Assert.assertTrue(page.existsInputPassword());
    }

    @Test
    public void loginNotCorrectLoginAndPasswordTest() {
        page.fillLoginFormAndSubmit(TEST_USERNAME, TEST_PASSWORD);
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyLoginTest() {
        page.fillLoginFormAndSubmit("", TEST_PASSWORD);
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyPasswordTest() {
        page.fillLoginFormAndSubmit(TEST_USERNAME, "");
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyLoginAndPasswordTest() {
        page.fillLoginFormAndSubmit("", "");
        Assert.assertTrue(page.isErrorMessage());
    }
}