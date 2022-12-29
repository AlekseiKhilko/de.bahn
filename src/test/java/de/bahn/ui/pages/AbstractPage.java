package de.bahn.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import de.bahn.ui.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractPage {
    protected static final Logger logger = LogManager.getLogger();
    protected WebDriver driver;
    public final int WAIT_TIMEOUT_SECONDS = 20;

    protected AbstractPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {}
        waitForPageLoad();
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds);
        }catch (InterruptedException e) {}
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
        actions.moveToElement(element).build().perform();;
    }

}