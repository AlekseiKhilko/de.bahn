package de.bahn.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    private final String BASE_URL = "https://www.bahn.de/";
    @FindBy(xpath = "//a[@href='/angebot']")
    private WebElement linkTikcet;
    @FindBy(xpath = "//li[contains(@class,'display-on-log-out')]//a[@class='nav__link nav__link--arrow js-sub-menu-trigger nav__link--login']")
    private WebElement linkMenuLogin;
    @FindBy(xpath = "//a[normalize-space()='Login für Geschäftskunden']")
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

    public void closePopupAllowCookies(){
            int frameIndex = 1;
            driver.switchTo().frame(frameIndex);
            int i = 0;
            By byBody = By.tagName("body");
            while (i < 5) {
                driver.findElement(byBody).sendKeys(Keys.TAB);
                i++;
            }
            driver.findElement(byBody).sendKeys(Keys.ENTER);
            driver.switchTo().defaultContent();
    }

    public SearchPage openSearchForm() {
        linkTikcet.click();
        return new SearchPage();
    }


}