package pages.parabank;

import engine.gui.actions.ElementActions;
import engine.gui.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class TransferFunds {
private final WebDriver driver;

public TransferFunds (WebDriver driver){
    this.driver=driver;
}
    private final By header= By.xpath("//h1[@class='title' and text()='Transfer Complete!']");
private final By amountTxtArea= By.id("amount");
    private final By fromAccount= By.id("fromAccountId");
    private final By toAccount= By.id("toAccountId");
    private final By transferBtn= By.xpath("//input[@value='Transfer']");

    @Step("Type amount to transfer [{amount}]")
    public void typeAmount(String amount){
        ElementActions.typeInElement(driver,amountTxtArea,amount);
}

    @Step("select from account [{acc}]")
    public void selectFromAccount(String acc){
        ElementActions.selectDDLOptionValue(driver,fromAccount,acc);
    }

    @Step("select to account [{acc}]")
    public void selectToAccount(String acc){
        ElementActions.selectDDLOptionValue(driver,toAccount,acc);
    }
    @Step("click transfer button")
    public void clickTransferButton(){
        ElementActions.clickElement(driver,transferBtn);
    }

    public void assertTransferIsCompleted(){
        WaitActions.explicitWait(driver,5).until(ExpectedConditions.not(ExpectedConditions.textToBe(
                header,"")));
        Assert.assertEquals(ElementActions.getText(driver,header),"Transfer Complete!");
    }
}
