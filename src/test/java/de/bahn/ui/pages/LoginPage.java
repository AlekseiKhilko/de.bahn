package de.bahn.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement inputUsername;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;
    @FindBy(xpath = "//div[@class='login-page__message message message-error']/span")
    private WebElement messageError;

    public LoginPage fillLoginFormAndSubmit(String username, String password) {
        fillSearchForm(username, password);
        clickSubmit();
        return this;
    }

    public Boolean existsInputUsernaem() {
        return inputUsername.isDisplayed();
    }

    public Boolean existsInputPassword() {
        return inputPassword.isDisplayed();
    }

    public void fillSearchForm(String username, String password){
        waitElementsLoad(inputUsername, inputPassword);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public void clickSubmit(){
        waitForElementToBeClickable(buttonSubmit).click();
    }

    public String getErrorMessage(){
        String message = waitForVisibilityOfElement(messageError).getText();
        return message;
    }

    public Boolean isErrorMessage() {
        return getErrorMessage().contains("Bitte bestätigen Sie, dass Sie ein Mensch sind");
    }
}
