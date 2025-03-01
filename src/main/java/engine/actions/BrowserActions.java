package engine.actions;

import engine.reporter.CustomLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserActions {
    public static void navigateToURL(WebDriver driver,String url){
        driver.navigate().to(url);
        CustomLogger.logger.info("Navigate to url: {}",url);
    }

    public static void refreshTab(WebDriver driver){
        driver.navigate().refresh();
        CustomLogger.logger.info("Refresh tab");
    }

    public static void navigateToLastTab(WebDriver driver){
        driver.navigate().back();
        CustomLogger.logger.info("Navigate to last tab");
    }

    public static void acceptAlert(WebDriver driver){
        driver.switchTo().alert().accept();
        CustomLogger.logger.info("Accept url");
    }

    public static void navigateWindowByNum(WebDriver driver, int windowNum){
        List<String> window= new ArrayList<>();
        window.addAll(driver.getWindowHandles());
        driver.switchTo().window(window.get(windowNum));
        CustomLogger.logger.info("Navigate to tab: {}", windowNum);
            }
}
