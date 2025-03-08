package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SortableList {
private final WebDriver driver;
public SortableList(WebDriver driver){
    this.driver=driver;
}
//private  By ratings= By.tagName("span");
private By ratings(int rate){
    return By.xpath("(//span)["+rate+"]");
}
By checkOrder= By.id("check");

    private By personElement(String per, boolean getParent) {
        String xpath = getParent ? "//p[text()='" + per + "']/parent::div" : "//p[text()='" + per + "']";
        return By.xpath(xpath);
    }
public void handleSorting(){
    ArrayList<String> list=new ArrayList<>();
    list.add("Jeff Bezos");
    list.add("Bill Gates");
    list.add("Warren Buffett");
    list.add("Bernard Arnault");
    list.add("Carlos Slim Helu");
    list.add("Amancio Ortega");
    list.add("Larry Ellison");
    list.add("Mark Zuckerberg");
    list.add("Michael Bloomberg");
    list.add("Larry Page");

   for (int i=0; i<list.size();i++){
       ElementActions.dragAndDropByMouse(driver,personElement(list.get(i),false), ratings(i+2));
    }
    ElementActions.clickElement(driver,checkOrder);
    for (int i=0; i<list.size();i++){
            Assert.assertTrue(driver.findElement(personElement(list.get(i),false)).getCssValue("color")
            .contains("rgba(58, 227, 116, 1)"));
        }
}
}
