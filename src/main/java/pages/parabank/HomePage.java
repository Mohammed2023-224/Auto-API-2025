package pages.parabank;

import engine.gui.actions.BrowserActions;
import engine.gui.actions.ElementActions;
import engine.gui.constants.FrameWorkConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
WebDriver driver;
public HomePage(WebDriver driver){
    this.driver=driver;
}
private final By userNameField=By.name("username");
    private final By passwordField=By.name("password");
    private final By loginButton=By.xpath("//input[@value='Log In']");
    private final By registerButton=By.xpath("//a[text()='Register']");
    private final By forgetInfoButton=By.xpath("//a[text()='Forgot login info?']");
    private final By loginError=By.cssSelector(".error");

private By headerLinks(String text){
    return By.xpath("//div[@id='headerPanel']//li[@class='"+text+"']");
}

    private By footerLinks(String text){
        return By.xpath("//div[@id='footerPanel']//a[text()='"+text+"']");
    }

    private By bodyLinks(String text){
        return By.xpath("//div[@id='bodyPanel']//a[text()='"+text+"']");
    }
@Step("Navigate to home page url")
public void navigateToHomePage(){
    BrowserActions.navigateToURL(driver, FrameWorkConstants.paraBankMainPage);
}

    @Step("Click footer link [{option}]")
public void clickFooterLink(String option ){
    ElementActions.clickElement(driver,footerLinks(option));
}
    @Step("Click header link [{option}]")
    public void clickHeaderLink(String option ){
        ElementActions.clickElement(driver,headerLinks(option));
    }
    @Step("Click body info link [{option}]")
    public void clickBodyInfoLink(String option ){
        ElementActions.clickElement(driver,bodyLinks(option));
    }

    @Step("Click register button")
    public void clickRegisterLink( ){
        ElementActions.clickElement(driver,registerButton);
    }

    @Step("Click login button")
    public void clickLoginLink( ){
        ElementActions.clickElement(driver,loginButton);
    }

    @Step("Click forget info button")
    public void clickForgetInfoLink( ){
        ElementActions.clickElement(driver,forgetInfoButton);
    }


    @Step("Type userName [{text}]")
    public void clickUserName(String text ){
        ElementActions.typeInElement(driver,userNameField,text);
    }

    @Step("Type password [{text}]")
    public void clickPassword(String text ){
        ElementActions.typeInElement(driver,passwordField,text);
    }
@Step("Get error text")
    public String getErrorText(){
    return ElementActions.getText(driver,loginError);
}

public void assertError(String text){
    ElementActions.assertElementContainsText(driver,loginError,text);
}

}
