package pages.parabank;

import engine.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TransferFunds {
private final WebDriver driver;

public TransferFunds (WebDriver driver){
    this.driver=driver;
}
    private final By header= By.xpath("//h1[@class='title']");
private final By amountTxtArea= By.id("amount");
    private final By fromAccount= By.id("fromAccountId");
    private final By toAccount= By.id("toAccountId");
    private final By transferBtn= By.xpath("//input[@value='Transfer']");

    @Step("Type amount to transfer [{amount}]")
    public void TypeAmount(String amount){
        ElementActions.typeInElement(driver,amountTxtArea,amount);
}

    @Step("select from account [{acc}]")
    public void selectFromAccount(String acc){
        ElementActions.selectDDLOption(driver,fromAccount,acc);
    }

    @Step("select to account [{acc}]")
    public void selectToAccount(String acc){
        ElementActions.selectDDLOption(driver,toAccount,acc);
    }
    @Step("click transfer button")
    public void clickTransferButton(){
        ElementActions.clickElement(driver,transferBtn);
    }

    public void assertTransferIsCompleted(){
        Assert.assertEquals(ElementActions.getText(driver,header),"Transfer Complete!");
    }
}
