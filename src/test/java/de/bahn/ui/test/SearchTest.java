package de.bahn.ui.test;

import de.bahn.ui.pages.HomePage;
import de.bahn.ui.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest {
    SearchPage page;
    String TEST_FROM = "Düsseldorf Hbf";
    String TEST_TO = "FRANKFURT(MAIN)";
    Integer TEST_TRAVELLERS = 2;

    @BeforeMethod
    public void beforeMethod() {
        page = new HomePage()
                .openPage()
                .openSearchForm();
    }

    @AfterMethod
    public void afterMethod() {
        //DriverSingleton.closeDriver();
    }

    @Test
    public void existsInputFromTest() {
        Assert.assertTrue(page.existsInputFrom());
    }

    @Test
    public void existsInputToTest() {
        Assert.assertTrue(page.existsInputTo());
    }

    @Test
    public void searchErrorMessageFromTest() {
        page.fillAndSubmit("", TEST_FROM, 1);
        Assert.assertTrue(page.isErrorMessageFrom());
    }

    @Test
    public void searchErrorMessageToTest() {
        page.fillAndSubmit(TEST_FROM, "", 1);
        Assert.assertTrue(page.isErrorMessageTo());
    }

    @Test
    public void searchErrorMessageFromAndToTest() {
        page.fillAndSubmit("", "", 1);
        Assert.assertTrue(page.isErrorMessageFrom() && page.isErrorMessageTo());
    }

    @Test
    public void searchCorrectFromAndToTest() {
        page.fillAndSubmit(TEST_FROM, TEST_TO, 1);
        Assert.assertTrue(page.isSearchResults());
    }

    @Test
    public void searchTwoTravellersTest() {
        page.fillAndSubmit(TEST_FROM, TEST_TO, TEST_TRAVELLERS);
        Assert.assertEquals(page.getTravellers(), TEST_TRAVELLERS);
    }

}