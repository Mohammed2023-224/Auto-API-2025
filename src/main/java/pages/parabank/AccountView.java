package pages.parabank;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class AccountView {
    private final WebDriver driver;
    public AccountView(WebDriver driver){
        this.driver=driver;
    }
//private final By header= By.xpath("//h1[@class='title']");
//private  final By totalBalance=By.xpath("(//table[@id='accountTable']//b)[2]");
///* -------------------------- Account details ----------------------------*/
//private final By accountNum=By.id("accountId");
//    private final By accountType=By.id("accountType");
//    private final By availableBalance=By.id("availableBalance");
//    private final By balance=By.id("balance");
//
//
//
//private By accountNumbers(String  text){
//    return By.xpath("(//table[@id='accountTable']//td//a[text()='"+text+"']");
//}
//
//    private By accountBalance(String  text, int balanceType){
//        return By.xpath("(//table[@id='accountTable']//td//a[text()='"+text+"']//ancestor::table//td)["+balanceType+"]");
//    }
//
//    @Step("get account ID")
//    public String getAccountID(){
//        WaitActions.explicitWaitByCondition(driver,accountNum,"visible",5);
//       return ElementActions.getText(driver,accountNum);
//    }
//
//    @Step("get account type")
//    public String getAccountType(){
//        WaitActions.explicitWaitByCondition(driver,accountType,"visible",5);
//        return ElementActions.getText(driver,accountType);
//    }
//
//    @Step("get Account balance")
//    public String getAccountBalance(){
//        WaitActions.explicitWaitByCondition(driver,balance,"visible",5);
//        return ElementActions.getText(driver,balance);
//    }
//
//    @Step("get Account available balance")
//    public String getAccountAvailableBalance(){
//        WaitActions.explicitWaitByCondition(driver,availableBalance,"visible",5);
//        return ElementActions.getText(driver,availableBalance);
//    }
//

}
