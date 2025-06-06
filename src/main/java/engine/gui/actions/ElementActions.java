package engine.gui.actions;

import engine.gui.reporter.CustomLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ElementActions {
/* ----------------------- mouse Actions ----------------------------------------*/
public static void clickElement(WebDriver driver, By locator){
    WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
    WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
    driver.findElement(locator).click();
    CustomLogger.logger.info("Clicking element: [{}]",locator);
}

    public static void clickElementUsingJavaScript(WebDriver driver, By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(locator));
        CustomLogger.logger.info("Clicking using java script element: [{}]",locator);
    }

    public static void doubleClickElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
        new Actions(driver).doubleClick(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" double Clicking element: [{}]",locator);
    }

    public static void contextClickElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
        new Actions(driver).contextClick(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" right Clicking element: [{}]",locator);
    }
    public static void hoverOverElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
        CustomLogger.logger.info(" hover over element: [{}]",locator);
    }

    public static void dragAndDropByElement(WebDriver driver, By locatorSource, By locatorTarget){
        WaitActions.explicitWaitByCondition(driver,locatorSource,"visible",5);
        WaitActions.explicitWaitByCondition(driver,locatorTarget,"visible",5);
        new Actions(driver).dragAndDrop(driver.findElement(locatorSource),driver.findElement(locatorTarget)).perform();
        CustomLogger.logger.info(" drag element from [{}] to [{}]",locatorSource,locatorTarget);
    }
    public static void dragAndDropByMouse(WebDriver driver, By locatorSource, By locatorTarget){
        WaitActions.explicitWaitByCondition(driver,locatorSource,"clickable",5);
        WaitActions.explicitWaitByCondition(driver,locatorTarget,"clickable",5);
        new Actions(driver).scrollToElement(driver.findElement(locatorSource))
                .clickAndHold(driver.findElement(locatorSource))
                .scrollToElement(driver.findElement(locatorTarget))
                .moveToElement(driver.findElement(locatorTarget))
                .release(driver.findElement(locatorTarget)).build().perform();
        CustomLogger.logger.info(" drag element from [{}] to [{}] by mouse",locatorSource,locatorTarget);
    }

    public static void dragAndDropByLocation(WebDriver driver, By locatorSource ,int horizontal, int vertical){
        WaitActions.explicitWaitByCondition(driver,locatorSource,"visible",5);
        new Actions(driver).dragAndDropBy(driver.findElement(locatorSource),horizontal,vertical).perform();
        CustomLogger.logger.info(" drag element from [{}]",locatorSource);
    }

    /* --------------------------- Keyboard actions ------------------------------*/
    public static void typeInElement(WebDriver driver, By locator,String text){
        WaitActions.explicitWaitByCondition(driver,locator,"clickable",5);
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
        CustomLogger.logger.info("typing [{}] in element: [{}]",text,locator);
    }

    public static void pressKey(WebDriver driver, By locator,Keys key){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        driver.findElement(locator).sendKeys(key);
        CustomLogger.logger.info("pressing the [{}] key",key);
    }

    public static void scrollToElement(WebDriver driver, By locator){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        new Actions(driver).scrollToElement(driver.findElement(locator)).perform();
        CustomLogger.logger.info("Scrolling to element [{}]",locator);
    }

    /* -------------------- Lists actions ------------------------------*/
    public static void selectDDLOptionText(WebDriver driver, By locator,String option){
        WaitActions.explicitWaitByCondition(driver,locator,"visible",5);
        WaitActions.explicitWait(driver, 6).until(x -> {
            Select dynamicSelect = new Select(driver.findElement(locator));
            return !dynamicSelect.getOptions().isEmpty();
        });
        Select select=new Select(driver.findElement(locator));
        select.selectByVisibleText(option);
        CustomLogger.logger.info("Select option with text [{}] from locator [{}]",option,locator);
    }
    public static void selectDDLOptionValue(WebDriver driver, By locator,String option){
        WaitActions.explicitWait(driver, 6).until(x -> {
            Select dynamicSelect = new Select(driver.findElement(locator));
            return !dynamicSelect.getOptions().isEmpty();
        });
        Select select=new Select(driver.findElement(locator));
        select.selectByValue(option);
        CustomLogger.logger.info("Select option with value [{}] from locator [{}]",option,locator);
    }

    public static void selectDDLFirstOption(WebDriver driver, By locator ){
        WaitActions.explicitWait(driver, 6).until(x -> {
            Select dynamicSelect = new Select(driver.findElement(locator));  // Reload select each time
            return !dynamicSelect.getOptions().isEmpty();
        });
        Select select=new Select(driver.findElement(locator));
            select.getFirstSelectedOption();
        CustomLogger.logger.info("Select first option");
    }
/* ---------------------------- getting info actions-------------------*/
    public static  String getText(WebDriver driver, By locator){
        String text=driver.findElement(locator).getText();
        CustomLogger.logger.info("get text from locator [{}] : [{}]",locator, text);
        return text;
    }
    public static String getElementPropertyJSExecutor(WebDriver driver, By locator, String property){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value= (String) js.executeScript("return arguments[0][arguments[1]];", driver.findElement(locator), property);
        CustomLogger.logger.info("Get the property [{}] value : [{}]", property,value);
        return value;
    }

    public static String getElementAttribute(WebDriver driver, By locator, String property){
        String attributeValue=driver.findElement(locator).getDomAttribute(property);
        CustomLogger.logger.info("Get the attribute of [{}] value : [{}]", property,attributeValue);
        return attributeValue;
    }
    public static String getElementAttribute( WebElement locator, String property){
        String attributeValue=locator.getDomAttribute(property);
        CustomLogger.logger.info("Get the attribute of web element [{}] value : [{}]", property,attributeValue);
        return attributeValue;
    }

    public static String getCssValue(WebDriver driver, By locator,String property){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value= (String) js.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue(arguments[1]);", driver.findElement(locator), property);
        CustomLogger.logger.info("Get the css value [{}] value : [{}]", property,value);
        return value;
    }
    public static String getPseudoElementContent(WebDriver driver, By locator,String pseudoElement ){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value= (String) js.executeScript("return window.getComputedStyle(arguments[0], arguments[1]).getPropertyValue('content');"
                , driver.findElement(locator),pseudoElement);
        CustomLogger.logger.info("Get the pseudo element content : [{}] from element [{}] ", value,locator);
        return value.replace("\"","");
    }

    /* ------------------Frame, window and tabs actions ------------------*/
    public static void switchToFrameByLocator(WebDriver driver, By locator){
        driver.switchTo().frame(driver.findElement(locator));
        CustomLogger.logger.info("switch to frame by locator: [{}]",locator);
    }

    public static void switchToParentFrame(WebDriver driver){
        driver.switchTo().parentFrame();
        CustomLogger.logger.info("switch to parent frame");
    }

/* --------------------------- Shadow dom actions ---------------*/
    public static WebElement getShadowElement(WebDriver driver,By head, By element){
        SearchContext shadowHost=  driver.findElement(head).getShadowRoot();
        CustomLogger.logger.info("return shadow element located by [{}]",element);
        return shadowHost.findElement(element);
    }

/* --------------------- Element assertion actions ---------------------------*/
    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            CustomLogger.logger.info("Element [{}] isn't visible",locator);
            return false; // Element is not visible or not found
        }
    }

    public static boolean isElementClickable(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed() && driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            CustomLogger.logger.info("Element [{}] isn't clickable",locator);
            return false; // Element is not clickable or not found
        }
    }
    public static void assertElementContainsText(WebDriver driver, By locator, String text){
        Assert.assertTrue(ElementActions.getText(driver,locator).contains(text));
        CustomLogger.logger.info("text [{}] was found at locator text [{}]",text,locator);
        }

    public static void assertTextEquals(WebDriver driver,By locator, String text){
            Assert.assertEquals(text, ElementActions.getText(driver, locator));
            CustomLogger.logger.info("text [{}] equals text found at locator [{}]",text,locator);
    }
    public static void assertElementVisible(WebDriver driver,By locator){
            Assert.assertTrue(isElementVisible(driver,locator));
            CustomLogger.logger.info("Element [{}] is visible",locator);
    }

    /* -------------------- Alter elements --------------------------*/
    public static void changeElementAttributeJSExecutor(WebDriver driver, By locator, String property,String newValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                driver.findElement(locator), property,newValue);
        CustomLogger.logger.info("change locator [{}] attribute value to [{}]", locator,newValue);
    }

    public static void executeJSScript(WebDriver driver, String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
        CustomLogger.logger.info("execute java script  [{}]", script);
    }
}
