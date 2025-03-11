package pages.automationPlayGround;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BudgetTracker {
    private final WebDriver driver;
    public BudgetTracker (WebDriver driver){
        this.driver=driver;
    }
    By total=By.cssSelector(".total");
    By delete=By.tagName("button");
    By addNew=By.xpath("//button[@class='btn btn-green-outline new-entry']");
By dateFields(String row){
        return By.xpath("(//input[@type='date'])["+row+"]");
}
By textFields(String row){
        return By.xpath("(//input[@type='text'])["+row+"]");
}
By selectionFields(String row){
        return By.xpath("(//select)["+row+"]");
}
By amountFields(String row){
        return By.xpath("(//input[@type='number'])["+row+"]");
}

public void handleBudget(){
    ElementActions.assertElementContainsText(driver,total,"$0.00");
    ElementActions.clickElement(driver,addNew);
    ElementActions.assertElementVisible(driver,dateFields("1"));
    ElementActions.typeInElement(driver,dateFields("1"),"04/11/2025");
    ElementActions.typeInElement(driver,textFields("1"),"test");
    ElementActions.selectDDLOption(driver,selectionFields("1"),"Income");
    ElementActions.typeInElement(driver,amountFields("1"),"5");
    ElementActions.clickElement(driver,total);
    ElementActions.assertElementContainsText(driver,total,"$5.00");

    ElementActions.clickElement(driver,addNew);
    ElementActions.assertElementVisible(driver,dateFields("2"));
    ElementActions.typeInElement(driver,dateFields("2"),"02/11/2025");
    ElementActions.typeInElement(driver,textFields("2"),"test");
    ElementActions.selectDDLOption(driver,selectionFields("2"),"Expense");
    ElementActions.typeInElement(driver,amountFields("2"),"2");
    ElementActions.clickElement(driver,total);
    ElementActions.assertElementContainsText(driver,total,"$3.00");

    ElementActions.clickElement(driver,delete);
    ElementActions.assertElementContainsText(driver,total,"-$2.00");
}
}
