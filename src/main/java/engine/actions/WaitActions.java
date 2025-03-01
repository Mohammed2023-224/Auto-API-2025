package engine.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;

import java.time.Duration;

public class WaitActions {
 static Wait explicitWait;
 static Wait fluentWait;

    public static Wait explicitWait(WebDriver driver, int time){
        if (explicitWait ==null){
            explicitWait=new WebDriverWait(driver,Duration.ofSeconds(time));
        }
        return explicitWait;
    }

    public static Wait fluentWait(WebDriver driver){
        if (fluentWait ==null){
            fluentWait=new FluentWait(driver);
        }
        return fluentWait;
    }

    public static void implicitWait(WebDriver driver,int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static void waitForAlert(WebDriver driver,int time){
        explicitWait(driver,time).until(ExpectedConditions.alertIsPresent());
    }


    public static void explicitWaitByCondition(WebDriver driver,  By locator, String condition,@Optional int time){
        time = String.valueOf(time).isEmpty() || time==0? 10 : time;
        switch (condition.toLowerCase()){
            case "visible":
                if(!driver.findElement(locator).isDisplayed()){
                explicitWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
                }
                break;
            case "invisible":
                if(driver.findElement(locator).isDisplayed()){
                    explicitWait(driver,time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
                }
                break;
            case "enabled":
                if(!driver.findElement(locator).isEnabled()){
                    explicitWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
                }
                break;
            case "clickable":
                    if(!driver.findElement(locator).isDisplayed()){
                explicitWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
                }
                    break;
        }
    }
}
