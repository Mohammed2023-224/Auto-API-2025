package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import engine.gui.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShadowDomTest {
    private final WebDriver driver;
    public ShadowDomTest (WebDriver driver){
        this.driver=driver;
    }
    By host= By.tagName("progress-bar");
    By btn= By.cssSelector(".btn-green-outline");
    By element=By.cssSelector(".fill");


    public void handleShadow(){
        ElementActions.getShadowElement(driver,host,btn).click();
        CustomLogger.logger.info(ElementActions.getShadowElement(driver,host,btn).getDomAttribute("style"));
        Assert.assertTrue(ElementActions.getElementAttribute(ElementActions.getShadowElement(driver,host,element)
                ,"style").contains("95%"));
    }
}
