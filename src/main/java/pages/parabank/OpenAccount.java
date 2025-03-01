package pages.parabank;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

//@Step("Select account type [{type}]")
//public void SelectAccountType(String type){
//    ElementActions.selectDDLOption(driver,accountType,type);
//}
//
//    @Step("Select first account from the ddl")
//    public void SelectFirstAccount(){
//        ElementActions.selectDDLFirstOption(driver,fromAccount);
//    }
//
//
//    @Step("Click open account")
//    public void clickOpenAccount( ){
//        ElementActions.clickElement(driver,openNewAccount);
//    }
//
//    @Step("get account ID")
//    public String getAccountID(){
//        WaitActions.explicitWaitByCondition(driver,header,"visible",5);
//        return ElementActions.getText(driver,newAccountID);
//    }
//
//
//    @Step("click account id")
//    public void clickAccountID(){
//        WaitActions.explicitWaitByCondition(driver,header,"visible",5);
//        ElementActions.clickElement(driver,newAccountID);
//    }
}
