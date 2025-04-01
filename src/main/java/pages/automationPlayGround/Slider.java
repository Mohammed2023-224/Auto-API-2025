package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Slider {
    private final WebDriver driver;
    public Slider (WebDriver driver){
        this.driver=driver;
    }
    By slider= By.tagName("input");
    By assertion= By.tagName("p");
    By btn= By.tagName("button");
    By sliderAssertion= By.cssSelector(".progress-bar");
public void handleSlider(){
    ElementActions.dragAndDropByLocation(driver,slider,0,0);
    ElementActions.clickElement(driver,btn);
    ElementActions.assertElementVisible(driver,assertion);
}

}
