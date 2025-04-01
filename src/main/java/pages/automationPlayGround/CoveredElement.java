package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoveredElement {
    private final WebDriver driver;
    public CoveredElement (WebDriver driver){
        this.driver=driver;
    }
    By btn= By.id("fugitive");
    By assertion= By.id("info");
public void handleCoveredElement(){
    ElementActions.scrollToElement(driver,btn);
    ElementActions.clickElement(driver,btn);
    ElementActions.scrollToElement(driver,assertion);
    ElementActions.assertElementContainsText(driver,assertion,"Mission");
}
}
