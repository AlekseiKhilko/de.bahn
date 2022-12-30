package de.bahn.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPage extends AbstractPage {
    private final String BASE_URL = "https://www.bahn.de/";
    @FindBy(xpath = "//input[@id='locS0']")
    private WebElement inputFrom;
    @FindBy(xpath = "//input[@id='locZ0']")
    private WebElement inputTo;
    @FindBy(xpath = "//select[@id='traveller_Nr']")
    private WebElement selectTravellers;
    @FindBy(xpath = "//input[@id='searchConnectionButton']")
    private WebElement buttonSubmit;
    @FindBy(xpath = "//a[@class='flex-reset link']")
    private WebElement linkSearch;
    @FindBy(xpath = "//div[@id='errormsg_S']")
    private WebElement errorMessgeFrom;
    @FindBy(xpath = "//div[@id='errormsg_Z']")
    private WebElement errorMessgeTo;
    @FindBy(xpath = "//div[@id='conTravellers']")
    private WebElement textTravellers;
    private String textTravellersLocator = "//div[@id='conTravellers']";
    private String searchResultsLocator = "//div[contains(@id, 'overview_updateC1-')]";

    public SearchPage() {
        super();
        waitForElementToBeClickable(linkSearch).click();
    }
    public SearchPage fillAndSubmit(String from, String to, Integer travellers) {
        fillForm(from, to, travellers);
        waitForElementToBeClickable(buttonSubmit).click();
        return this;
    }

    public void fillForm(String from, String to, Integer travellers) {
        waitElementsLoad(inputFrom, inputTo, selectTravellers);
        if(from.length() > 0) {
            inputFrom.sendKeys(from);
        }
        if(to.length() > 0) {
            inputTo.sendKeys(to);
        }
        if(travellers > 1) {
            waitForElementToBeClickable(selectTravellers);
            Select dropdown = new Select(selectTravellers);
            dropdown.selectByValue(travellers.toString());
        }
    }

    public String getErrorMessageFrom(){
        String message = waitForVisibilityOfElement(errorMessgeFrom).getText();
        return message;
    }

    public Boolean isErrorMessageFrom() {
        return getErrorMessageFrom().contains("Bitte geben Sie hier eine Haltestelle ein.");
    }

    public String getErrorMessageTo(){
        String message = waitForVisibilityOfElement(errorMessgeTo).getText();
        return message;
    }

    public Boolean isErrorMessageTo() {
        return getErrorMessageTo().contains("Bitte geben Sie hier eine Haltestelle ein.");
    }

    public Boolean existsInputFrom() {
        waitForVisibilityOfElement(inputFrom);
        return inputFrom.isDisplayed();
    }

    public Boolean existsInputTo() {
        return inputTo.isDisplayed();
    }

    public Boolean isSearchResults() {
        return (waitForPresenceOfAllElementsLocatedBy(searchResultsLocator).size() > 0) ? true : false;
    }

    public Integer getTravellers() {
        String travellers = waitForPresenceOfElementLocated(textTravellersLocator).getText();
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(travellers);
        if(matcher.find()){
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }
}