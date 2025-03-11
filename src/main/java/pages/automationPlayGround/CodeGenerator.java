package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CodeGenerator {
    private final WebDriver driver;
    public CodeGenerator (WebDriver driver){
        this.driver=driver;
    }
    By textField= By.tagName("input");
    By generate= By.tagName("button");
    By ing= By.xpath("(//img)[2]");

    public void handleCode(){
        ElementActions.typeInElement(driver,textField,"4352");
        ElementActions.clickElement(driver,generate);
        WaitActions.explicitWaitByCondition(driver,ing,"visible",3);
        CustomLogger.logger.info(driver.findElement(ing).getDomAttribute("src"));
        Assert.assertTrue(driver.findElement(ing).getDomAttribute("src").contains("4352"));
    }

}
