package pages.automationPlayGround;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.ArrayList;


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
        ElementActions.scrollToElement(driver,ratings(2));
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
