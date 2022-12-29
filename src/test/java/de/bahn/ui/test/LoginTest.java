package de.bahn.ui.test;

import de.bahn.ui.driver.DriverSingleton;
import de.bahn.ui.pages.HomePage;
import de.bahn.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {
    LoginPage page;
    String TEST_USERNAME = "mylogin";
    String TEST_PASSWORD = "12345678";

    @BeforeMethod
    public void beforeMethod() {
        page = new HomePage()
                .openPage()
                .clickLogin();
    }

    @AfterMethod
    public void afterMethod() {
        page.close();
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
        page.fillLoginForm(TEST_USERNAME, TEST_PASSWORD);
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyLoginTest() {
        page.fillLoginForm("", TEST_PASSWORD);
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyPasswordTest() {
        page.fillLoginForm(TEST_USERNAME, "");
        Assert.assertTrue(page.isErrorMessage());
    }

    @Test
    public void loginEmptyLoginAndPasswordTest() {
        page.fillLoginForm("", "");
        Assert.assertTrue(page.isErrorMessage());
    }
}