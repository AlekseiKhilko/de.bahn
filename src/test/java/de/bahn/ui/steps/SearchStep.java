package de.bahn.ui.steps;

import de.bahn.ui.pages.HomePage;
import de.bahn.ui.pages.SearchPage;
import org.openqa.selenium.support.ui.Select;

public class SearchStep {
    public SearchPage openSearchForm() {
        return new HomePage()
                .openPage()
                .openSearchForm();
    }

}
