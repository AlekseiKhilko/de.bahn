package de.bahn.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    private final String BASE_URL = "https://www.bahn.de/";
    @FindBy(xpath = "//a[@href='/angebot']")
    private WebElement linkTikcet;
    @FindBy(xpath = "//li[contains(@class,'display-on-log-out')]//a[@class='nav__link nav__link--arrow js-sub-menu-trigger nav__link--login']")
    private WebElement linkMenuLogin;
    @FindBy(xpath = "//ul/li/a[contains(@href, 'kmu_start.post')]")
    private WebElement linkLogin;

    public LoginPage clickLogin() {
        moveToElement(waitForVisibilityOfElement(linkMenuLogin));
        waitForElementToBeClickable(linkLogin).click();
        return new LoginPage();
    }

    public HomePage openPage() {
        driver.get(BASE_URL);
        closePopupAllowCookies();
        return this;
    }

    public SearchPage openSearchForm() {
        linkTikcet.click();
        return new SearchPage();
    }


}