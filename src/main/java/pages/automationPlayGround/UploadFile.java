package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import engine.gui.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UploadFile {
    private final WebDriver driver;
    public UploadFile (WebDriver driver){
        this.driver=driver;
    }
    By inputField= By.id("file-input");
    By numOfFiles= By.id("num-of-files");
String path="C:\\Users\\USER\\Pictures\\Screenshots\\";
    public void handleUploads(){
        ElementActions.changeElementAttributeJSExecutor(driver,inputField,"style", "display:block;");
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(inputField));
        ElementActions.typeInElement(driver,inputField,path+"1.png \n "+path+"2.png");
        ElementActions.assertElementContainsText(driver,numOfFiles,"2");
        ElementActions.typeInElement(driver,inputField,path+"3.png");
        ElementActions.assertElementContainsText(driver,numOfFiles,"1");
    }
}
