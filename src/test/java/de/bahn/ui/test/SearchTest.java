package de.bahn.ui.test;

import de.bahn.ui.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends AbstractTest {

    @Test
    public void searchTest() {
        new HomePage()
                .openPage()
                .openSearchForm()
                .fillAndSubmit("Düsseldorf Hbf", "FRANKFURT(MAIN)", 2);
    }

    @Test
    public void loginTest() {
        new HomePage()
                .openPage()
                .clickLogin();
    }
}