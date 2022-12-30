package de.bahn.ui.steps;

import de.bahn.ui.pages.HomePage;
import de.bahn.ui.pages.LoginPage;

public class LoginStep {
    public LoginPage openLoginForm() {
        return new HomePage()
                .openPage()
                .clickLogin();
    }
}
