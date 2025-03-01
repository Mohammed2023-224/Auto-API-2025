package engine.actions;

import engine.reporter.CustomLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

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
    }
}
