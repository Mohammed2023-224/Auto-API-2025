package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RightClick {
    private final WebDriver driver;
    public RightClick (WebDriver driver){
        this.driver=driver;
    }

    By msg= By.id("msg");
    By links(String option){
        return By.xpath("//li//span[text()='"+option+"']");
    }
    public void handleRightClick(){
        ElementActions.contextClickElement(driver,msg);
        ElementActions.clickElement(driver,links("Rename"));
        ElementActions.assertElementContainsText(driver,msg,"Rename");
        ElementActions.contextClickElement(driver,msg);
        ElementActions.hoverOverElement(driver,links("Share"));
        ElementActions.clickElement(driver,links("Dribble"));
        ElementActions.assertElementContainsText(driver,msg,"Dribble");
        ElementActions.contextClickElement(driver,msg);
        ElementActions.clickElementUsingJavaScript(driver,links("Preview"));
        ElementActions.assertElementContainsText(driver,msg,"Preview");

    }
}
