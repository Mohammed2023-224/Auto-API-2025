package pages.automationPlayGround;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class VerifyAccount {
    private final WebDriver driver;
    public VerifyAccount (WebDriver driver){
        this.driver=driver;
    }
    By input= By.tagName("input");
    By confirmationCode=By.xpath("//*[@class='info']");
    By confirmationCSuccessMessage=By.xpath("//*[@class='info success']");
    public void handleVerification(){
        List<WebElement> list=driver.findElements(input);
        int size=list.size();
        String confirmationCod=ElementActions.getText(driver,confirmationCode).substring(25);
        String[] myArray=confirmationCod.split("-");
        for (int i=0;i<size;i++){
            list.get(i).sendKeys(myArray[i]);
        }
        Assert.assertTrue(ElementActions.getText(driver,confirmationCSuccessMessage).contains("Success"));
    }

}
