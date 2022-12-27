package de.bahn.ui.test;

import de.bahn.ui.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest {
    @AfterMethod
    public void tearDown() {
        //DriverSingleton.closeDriver();
    }

    @Test
    public void searchTest() {
        new HomePage()
                .openPage()
                .search("Düsseldorf Hbf", "FRANKFURT(MAIN)", 2);
    }
}