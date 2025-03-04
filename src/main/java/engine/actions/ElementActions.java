package engine.actions;

import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementActions {

public static void clickElement(WebDriver driver, By locator){
    WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
    WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
    driver.findElement(locator).click();
    CustomLogger.logger.info("Clicking element: {}",locator);
}

    public static void clickElementUsingJavaScript(WebDriver driver, By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(locator));
        CustomLogger.logger.info("Clicking using java script element: {}",locator);
    }

    public static void doubleClickElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
        new Actions(driver).doubleClick(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" double Clicking element: {}",locator);
    }

    public static void contextClickElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
        new Actions(driver).contextClick(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" right Clicking element: {}",locator);
    }
    public static void hoverOverElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" hover over element: {}",locator);
    }

    public static void dragAndDropByElement(WebDriver driver, By locatorSource, By locatorTarget){
        WaitActions.explicitWaitByCondition(driver,locatorSource,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locatorTarget,"visible",5);
        new Actions(driver).dragAndDrop(driver.findElement(locatorSource),driver.findElement(locatorTarget)).perform();
        CustomLogger.logger.info(" drag element from {} to {}",locatorSource,locatorTarget);
    }

    public static void dragAndDropByLocation(WebDriver driver, By locatorSource ,int horizontal, int vertical){
        WaitActions.explicitWaitByCondition(driver,locatorSource,"visible",5);
        new Actions(driver).dragAndDropBy(driver.findElement(locatorSource),horizontal,vertical).perform();
        CustomLogger.logger.info(" drag element from {}",locatorSource);
    }

    public static void typeInElement(WebDriver driver, By locator,String text){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
        CustomLogger.logger.info("typing {} in element: {}",text,locator);
    }

    public static void pressKey(WebDriver driver, By locator,Keys key){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        driver.findElement(locator).sendKeys(key);
        CustomLogger.logger.info("pressing the {} key",key);
    }

    public static void selectDDLOption(WebDriver driver, By locator,String option){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
    Select select=new Select(driver.findElement(locator));
        select.selectByVisibleText(option);
        CustomLogger.logger.info("Select option with text {} from locator {}",option,locator);
    }

    public static void selectDDLFirstOption(WebDriver driver, By locator ){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
    Select select=new Select(driver.findElement(locator));
        select.getFirstSelectedOption();
        CustomLogger.logger.info("Select first option from locator {}",locator);
    }

    public static  String getText(WebDriver driver, By locator){
        String text=driver.findElement(locator).getText();
        CustomLogger.logger.info("get text from locator {} : {}",locator, text);
        return text;
    }

    public static void switchToFrameByLocator(WebDriver driver, By locator){
        driver.switchTo().frame(driver.findElement(locator));
        CustomLogger.logger.info("switch to frame by locator: {}",locator);
    }

    public static void switchToParentFrame(WebDriver driver){
        driver.switchTo().parentFrame();
        CustomLogger.logger.info("switch to parent frame");
    }
    public static String getElementProperty(WebDriver driver, By locator,String property){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value= (String) js.executeScript("return arguments[0][arguments[1]];", driver.findElement(locator), property);
        CustomLogger.logger.info("Get the property {} value : {}", property,value);
        return value;
    }
    public static String getPseudoElementContent(WebDriver driver, By locator ){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value= (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');"
                , driver.findElement(locator));
        CustomLogger.logger.info("Get the pseudo element content : {}", value);
        return value.replace("\"","");
    }
}
