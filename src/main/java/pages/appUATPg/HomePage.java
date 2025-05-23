package pages.appUATPg;

import engine.gui.actions.BrowserActions;
import engine.gui.actions.ElementActions;
import engine.gui.constants.FrameWorkConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
WebDriver driver;

public HomePage (WebDriver driver){
    this.driver=driver;
}

public By pages(String tabName){
    return By.xpath("//h5[contains(text(),'"+tabName+"')]//parent::div//parent::div//a");
}

@Step("Navigate to home page")
    public void navigateHomePage(){
    BrowserActions.navigateToURL(driver, FrameWorkConstants.testPlayGroundMainPage);
}

    @Step("click page")
    public void clickPage(String tab){
        ElementActions.clickElement(driver,pages(tab));
    }

}
