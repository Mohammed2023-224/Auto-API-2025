package pages.parabank;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


public class AccountView {
    private final WebDriver driver;
    public AccountView(WebDriver driver){
        this.driver=driver;
    }
private final By header= By.xpath("//h1[@class='title']");
private  final By totalBalance=By.xpath("(//table[@id='accountTable']//b)[2]");
/* -------------------------- Account details ----------------------------*/
private final By accountNum=By.id("accountId");
    private final By accountType=By.id("accountType");
    private final By availableBalance=By.id("availableBalance");
    private final By balance=By.id("balance");

By accountNumberElements=By.xpath("(//table[@id='accountTable']//td//a)");
private By accountNumbers(int  index){
    return By.xpath("(//table[@id='accountTable']//td//a)["+index+"]");
}
private By accountByID(String  id){
    return By.xpath("//table[@id='accountTable']//td//a[text()='"+id+"']");
}

    private By accountBalanceByID(String  text){
        return By.xpath("(//table[@id='accountTable']//td//a[text()='"+text+"']//parent::td//following::td)[1]");
    }

    @Step("get account ID")
    public String getAccountID(){
        WaitActions.explicitWaitByCondition(driver,accountNum,"visible",5);
       return ElementActions.getText(driver,accountNum);
    }

    @Step("get account type")
    public String getAccountType(){
        WaitActions.explicitWaitByCondition(driver,accountType,"visible",5);
        return ElementActions.getText(driver,accountType);
    }

    @Step("get Account balance")
    public String getAccountBalance(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                balance,"")));
        return ElementActions.getText(driver,balance);
    }
    @Step("get Account available balance")
    public String getAccountAvailableBalance(){
        WaitActions.explicitWaitByCondition(driver,availableBalance,"visible",5);
        return ElementActions.getText(driver,availableBalance);
    }

    @Step("get Account number ")
    public ArrayList<String> getAccountNumber(){
        ArrayList<String>  list=new ArrayList<>();
        driver.findElements(accountNumberElements);
    for (int i=1 ;i<=driver.findElements(accountNumberElements).size();i++){
        list.add(ElementActions.getText(driver,accountNumbers(i)));
    }
        return list;
    }

    @Step("get total balance")
    public String getTotalBalance(){
        WaitActions.explicitWaitByCondition(driver,totalBalance,"visible",5);
        return ElementActions.getText(driver,totalBalance);
    }

    @Step("get account balance")
    public String getCertainAccountAccountBalance(String text){
        WaitActions.explicitWaitByCondition(driver,accountBalanceByID(text),"visible",5);
        return ElementActions.getText(driver,accountBalanceByID(text));
    }
    @Step("click account number")
    public void clickAccountBalance(){
        WaitActions.explicitWaitByCondition(driver,accountNumbers(1),"visible",5);
         ElementActions.clickElement(driver,accountNumbers(1));

    }


}
