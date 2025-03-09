package pages.automationPlayGround;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OnboardingModelPopup {
    private final WebDriver driver;
    public OnboardingModelPopup (WebDriver driver){
        this.driver=driver;
    }
    By text= By.xpath("//li//a");
    By closePopup=By.tagName("i");
    By textAfterClose=By.cssSelector(".title");

    public void handlePopup(){
        ElementActions.assertElementContainsText(driver,text,"Welcome");
        ElementActions.clickElement(driver,closePopup);
        ElementActions.assertElementContainsText(driver,textAfterClose,"Peter");
        ElementActions.clickElement(driver,closePopup);
        ElementActions.assertElementContainsText(driver,text,"Welcome");
    }
}
