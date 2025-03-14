package pages.parabank;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BillPay {

    private final WebDriver driver;
    public BillPay(WebDriver driver){
        this.driver=driver;
    }
By assertion=By.xpath("//h1[text()='Bill Payment Complete']");
    By payeeName= By.name("payee.name");
    By address= By.name("payee.address.street");
    By city= By.name("payee.address.city");
    By state= By.name("payee.address.state");
    By zipCode= By.name("payee.address.zipCode");
    By phone= By.name("payee.phoneNumber");
    By account= By.name("payee.accountNumber");
    By verifyAccount= By.name("verifyAccount");
    By amount= By.name("amount");
    By fromAccount= By.name("fromAccountId");
    By sendPayment= By.xpath("//input[@type='button']");
    By missingNameError= By.id("validationModel-name");

    @Step("Type [{text}] in payee name field")
    public void typePayeeName(String text){
        ElementActions.typeInElement(driver,payeeName,text);
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
    @Step("Type [{text}] in account field")
    public void typeAccount(String text){
        ElementActions.typeInElement(driver,account,text);
    }
    @Step("Type [{text}] in verify account field")
    public void typeVerifyAccount(String text){
        ElementActions.typeInElement(driver,verifyAccount,text);
    }
    @Step("Type [{text}] in phone field")
    public void typePhone(String text){
        ElementActions.typeInElement(driver,phone,text);
    }
    @Step("Type [{text}] in  amount field")
    public void typeAmount(String text){
        ElementActions.typeInElement(driver,amount,text);
    }

    @Step("Choose account [{}]")
    public void chooseAccount(String account){
        ElementActions.selectDDLOptionText(driver,fromAccount,account);
    }
    @Step("Click send payment")
    public void clickSendPayment(){
        ElementActions.clickElement(driver,sendPayment);
    }

    public void assertCompletePayment(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                assertion,"")));
        ElementActions.assertElementContainsText(driver,assertion,"Complete");
    }
    public void assertMissingNameError(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                missingNameError,"")));
        ElementActions.assertElementContainsText(driver,missingNameError,"required");
    }

}
