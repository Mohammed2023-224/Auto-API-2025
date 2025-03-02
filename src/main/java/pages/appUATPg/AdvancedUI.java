package pages.appUATPg;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdvancedUI {
private final WebDriver driver;

public AdvancedUI(WebDriver driver){
    this.driver=driver;
}
public void handleTab(){
    By starRating= By.className("star-rating");
    By textRating= By.id("txt_rating");
    By checkRating= By.id("check_rating");
    By validateRating= By.id("validate_rating");

    String text=ElementActions.getPseudoElementContent(driver,starRating);
    ElementActions.typeInElement(driver,textRating,text);
    ElementActions.clickElement(driver,checkRating);
    Assert.assertTrue(ElementActions.getText(driver,validateRating).contains("Well done!"));

}

}
