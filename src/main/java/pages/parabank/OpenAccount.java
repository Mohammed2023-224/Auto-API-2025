package pages.parabank;

import engine.gui.actions.ElementActions;
import engine.gui.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpenAccount {
private final WebDriver driver;
public OpenAccount(WebDriver driver){
    this.driver=driver;
}
private final By accountType= By.id("type");
    private final By fromAccount= By.id("fromAccountId");
private final By openNewAccount=By.xpath("//input[@value='Open New Account']");
private final By header=By.xpath("//h1[@class='title']");
private final By newAccountID=By.id("newAccountId");

@Step("Select account type [{type}]")
public void selectAccountType(String type){
    ElementActions.selectDDLOptionText(driver,accountType,type);
}

    @Step("Select first account from the ddl")
    public void selectFirstAccount(){
        ElementActions.selectDDLFirstOption(driver,fromAccount);
    }


    @Step("Click open account")
    public void clickOpenAccount( ){
        ElementActions.clickElement(driver,openNewAccount);
    }

    @Step("get account ID")
    public String getAccountID(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                newAccountID,"")));
        return ElementActions.getText(driver,newAccountID);
    }


    @Step("click account id")
    public void clickAccountID(){
        WaitActions.explicitWaitByCondition(driver,newAccountID,"visible",5);
        ElementActions.clickElement(driver,newAccountID);
    }
}
