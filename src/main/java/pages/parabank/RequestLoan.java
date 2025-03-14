package pages.parabank;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RequestLoan {
    private final WebDriver driver;
    public RequestLoan(WebDriver driver){
        this.driver=driver;
    }

    By amount= By.id("amount");
    By downPayment= By.id("downPayment");
    By account= By.id("fromAccountId");
    By loanStatus= By.id("loanStatus");
    By denied= By.id("loanRequestDenied");
    By approved= By.id("loanRequestApproved");
    By btn= By.xpath("//input[@type='button']");

    @Step("Type [{text}] in amount field")
    public void typeAmount(String text){
        ElementActions.typeInElement(driver,amount,text);
    }
    @Step("Type [{text}] in down payment field")
    public void typeDownPayment(String text){
        ElementActions.typeInElement(driver,downPayment,text);
    }

    @Step("Select account [{text}]")
    public void selectAccount(String text){
        ElementActions.selectDDLOptionText(driver,account,text);
    }
    @Step("click apply")
    public void clickApply(){
        ElementActions.clickElement(driver,btn);
    }
    public void deniedAssertion(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                denied,"")));
        ElementActions.assertElementVisible(driver,denied);
    }
    public void validAssertion(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                approved,"")));
        ElementActions.assertElementVisible(driver,approved);
    }


public void loanStatusAssertion(String text){
        ElementActions.assertElementContainsText(driver,loanStatus,text);
    }


}
