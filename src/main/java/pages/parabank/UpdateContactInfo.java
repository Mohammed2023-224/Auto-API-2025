package pages.parabank;

import engine.gui.actions.ElementActions;
import engine.gui.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UpdateContactInfo {
private final WebDriver driver;
public UpdateContactInfo(WebDriver driver){
    this.driver=driver;
}

By firstName= By.name("customer.firstName");
    By lastName= By.name("customer.lastName");
    By address= By.name("customer.address.street");
    By city= By.name("customer.address.city");
    By zipCode= By.name("customer.address.zipCode");
    By state= By.name("customer.address.state");
    By phone= By.name("customer.phoneNumber");
    By updateBtn= By.xpath("//input[@type='button']");
    By assertion= By.xpath("//h1[text()='Profile Updated']");

    @Step("Type [{text}] in first name field")
    public void typeFirstName(String text){
        ElementActions.typeInElement(driver,firstName,text);
    }
    @Step("Type [{text}] in last name field")
    public void typeLastName(String text){
        ElementActions.typeInElement(driver,lastName,text);
    }
    @Step("Type [{text}] in address field")
    public void typeAddress(String text){
        ElementActions.typeInElement(driver,address,text);
    }
    @Step("Type [{text}] in city field")
    public void typeCity(String text){
        ElementActions.typeInElement(driver,city,text);
    }
    @Step("Type [{text}] in state field")
    public void typeState(String text){
        ElementActions.typeInElement(driver,state,text);
    }
    @Step("Type [{text}] in zip code field")
    public void typeZipCode(String text){
        ElementActions.typeInElement(driver,zipCode,text);
    }
    @Step("Type [{text}] in phone field")
    public void typePhone(String text){
        ElementActions.typeInElement(driver,phone,text);
    }

    @Step("Click update btn")
    public void clickUpdate(){
        ElementActions.clickElement(driver,updateBtn);
    }

    public void assertUpdate(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                assertion,"")));
        ElementActions.assertElementContainsText(driver,assertion,"Updated");
    }
}
