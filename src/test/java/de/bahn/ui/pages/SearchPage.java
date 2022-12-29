package de.bahn.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    public SearchPage fillAndSubmit(String from, String to, Integer travellers) {
        linkSearch.click();
        fillForm(from, to, travellers);
        buttonSubmit.click();

        return this;
    }

    public void fillForm(String from, String to, Integer travellers) {
        //waitElementsLoad(inputFrom, inputTo, selectTravellers);
        inputFrom.sendKeys(from);
        inputTo.sendKeys(to);

        waitForElementToBeClickable(selectTravellers);
        Select dropdown = new Select(selectTravellers);
        dropdown.selectByValue(travellers.toString());
    }

}