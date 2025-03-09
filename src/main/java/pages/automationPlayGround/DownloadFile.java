package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.SystemMethods;
import engine.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadFile {
    private final WebDriver driver;
    public DownloadFile (WebDriver driver){
        this.driver=driver;
    }
    By btn= By.id("file");
    String path="C:\\Users\\USER\\Downloads\\";
    public void handleDownload(){
        ElementActions.clickElement(driver,btn);
        WaitActions.waitForFileToBeDownloaded(driver,path+"sample.pdf",4);
        SystemMethods.checkExistenceOfFile(path+"sample.pdf");

    }
}
