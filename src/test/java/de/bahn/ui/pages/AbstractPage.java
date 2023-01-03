package de.bahn.ui.pages;

import de.bahn.ui.utils.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import de.bahn.ui.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractPage {
    protected static final Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    public final int WAIT_TIMEOUT_SECONDS = 20;
    private final int CLOSE_POPUP_FRAME_INEDX = 1;
    private final int CLOSE_POPUP_TAB_ELEMENTS_COUNT = 5;

    protected AbstractPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        Util.waitSeconds(1000);
        waitForPageLoad();
    }

    public void closePopupAllowCookies(){
        driver.switchTo().frame(CLOSE_POPUP_FRAME_INEDX);
        int i = 0;
        By byBody = By.tagName("body");
        while (i < CLOSE_POPUP_TAB_ELEMENTS_COUNT) {
            driver.findElement(byBody).sendKeys(Keys.TAB);
            i++;
        }
        driver.findElement(byBody).sendKeys(Keys.ENTER);
        driver.switchTo().defaultContent();
    }

    public void waitElementsLoad(WebElement ... elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected WebElement waitForElementToBeClickable(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .elementToBeClickable(webElement));
    }

    protected WebElement waitForVisibilityOfElement(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).until(ExpectedConditions
                .visibilityOf(webElement));
    }

    protected List<WebElement> waitForPresenceOfAllElementsLocatedBy(String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }

    protected WebElement waitForPresenceOfElementLocated(String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    protected WebElement lazyFindElement(String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    protected void waitForInvisibilityOfElementLocated(String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    protected void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver wdriver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                                .equals("complete");
                    }
                });
    }

    protected void clickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

}