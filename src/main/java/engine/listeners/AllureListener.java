package engine.listeners;

import engine.reporter.CustomLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AllureListener {


    public static void saveTextLog(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Allure.addAttachment("test logs", fis);
        } catch (FileNotFoundException e) {
            CustomLogger.logger.info("File can't be attached to report");
        }
    }

protected void takeScreenShot(WebDriver driver , String text){
    CustomLogger.logger.info("Taking screenshot and saving to allure report");
    Allure.addAttachment(text, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
}
}
