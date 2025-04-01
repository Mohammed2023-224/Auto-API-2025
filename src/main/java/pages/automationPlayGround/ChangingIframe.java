package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import engine.gui.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangingIframe {
    private final WebDriver driver;
    public ChangingIframe (WebDriver driver){
        this.driver=driver;
    }

    By firstIframe= By.id("frame1");
    By msg= By.id("msg");
    By time= By.id("time");
    public void handleChangingIframe(){
        ElementActions.switchToFrameByLocator(driver,firstIframe);
        WaitActions.explicitWait(driver,15).until(ExpectedConditions.textToBePresentInElementLocated(time,"53"));
        ElementActions.assertElementContainsText(driver,time,"53");
        WaitActions.explicitWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(msg,"end"));
        ElementActions.switchToParentFrame(driver);
        ElementActions.switchToFrameByLocator(driver,firstIframe);
        ElementActions.assertElementContainsText(driver,msg,"end");
    }
}
