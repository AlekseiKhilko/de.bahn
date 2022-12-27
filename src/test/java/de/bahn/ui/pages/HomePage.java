package de.bahn.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

public class HomePage extends AbstractPage {
    private final String BASE_URL = "https://www.bahn.de/";

    @FindBy(xpath = "//input[@id='qf-from']")
    private WebElement inputFrom;
    @FindBy(xpath = "//input[@id='qf-to']")
    private WebElement inputTo;
    @FindBy(xpath = "//select[@id='qf-travellers']")
    private WebElement selectTravellers;
    @FindBy(xpath = "//input[@id='qf-search-city']")
    private WebElement buttonSubmit;
    @FindBy(xpath = "//button[contains(@class, 'js-accept-all-cookies')]")
    private WebElement buttonClose;

    public HomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public static void clickRobot(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(1000);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public HomePage search(String from, String to, Integer travellers) {

        //waitForElementToBeClickable(buttonClose);
        //driver.switchTo().frame(3);

        try{
                 Thread.sleep(6000);


            driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).sendKeys(Keys.TAB);
            Thread.sleep(2000);
            driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
            Thread.sleep(500);
            driver.findElement(By.tagName("body")).sendKeys(Keys.ENTER);
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            System.out.println("main thread interrupted");
        }





        //elem.getAttribute("innerHTML");
        //System.out.println(driver.getPageSource());
        System.out.println("--------------");

        String javascript = "return document.getElementsByTagName('iframe').length;";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String sText =  jsExecutor.executeScript("return document.getElementsByTagName('iframe').length;").toString();
        System.out.println("size: " + sText);

        Integer size = Integer.parseInt(sText);
        WebElement element = null;
        String str;
        for(Integer i = 0; i < size; i++) {
            str = jsExecutor.executeScript("return document.getElementsByTagName('iframe')[" + i + "].innerText;").toString();
            System.out.println(str + "\n----------");

            driver.switchTo().frame(i);
            System.out.println(driver.getPageSource());
            driver.switchTo().defaultContent();

            if(element != null){
                element.click();
                System.out.println("click " + i);
            }
        }

        //element.click();

/*
        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Size: " + iframes.size());
        Integer i = 0;
        for (WebElement iframe : iframes) {

            driver.switchTo().frame(i);
            System.out.println(driver.getPageSource());

            try{
                driver.findElement(By.xpath("//button[contains(@class, 'js-accept-all-cookies')]")).click();

            }catch (Exception e){}

            driver.switchTo().defaultContent();

            System.out.println("-------------------------");
            i++;
        }

*/

        //fillForm(from, to, travellers);
        //buttonSubmit.click();
        return this;
    }

    public void fillForm(String from, String to, Integer travellers) {
        inputFrom.sendKeys(from);
        inputTo.sendKeys(from);
        Select dropdown = new Select(selectTravellers);
        dropdown.selectByValue(travellers.toString());
    }

}