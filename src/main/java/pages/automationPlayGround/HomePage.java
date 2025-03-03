package pages.automationPlayGround;

import engine.actions.BrowserActions;
import engine.actions.ElementActions;
import engine.constants.FrameWorkConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    public HomePage (WebDriver driver){
        this.driver=driver;
    }
    private By page(String option){
        return By.xpath("//h3[text()='"+option+"']//ancestor::a");
    }

    @Step("Navigate to website")
    public void navigateToWebSite(){
        BrowserActions.navigateToURL(driver, FrameWorkConstants.playGroundMainPage);
    }

    @Step("click page")
    public void clickPage(String tab){
        ElementActions.clickElement(driver,page(tab));
    }
}
