package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TagsInputBox {
private final WebDriver driver;
public TagsInputBox(WebDriver driver){
    this.driver=driver;
}
By removeAll= By.tagName("button");
By numOfTags=By.xpath("//p//span");
By textArea=By.tagName("input");
By removeTag=By.tagName("i");
By tagText=By.tagName("li");

public void handleTags(){
    Assert.assertTrue(driver.findElement(numOfTags).getText().contains("8"));
    List<WebElement> list=driver.findElements(tagText);
        Assert.assertTrue(list.get(0).getText().contains("node"));
        Assert.assertTrue(list.get(1).getText().contains("javascript"));
    ElementActions.typeInElement(driver,textArea,"test");
    ElementActions.pressKey(driver,By.tagName("input"), Keys.ENTER);
    Assert.assertTrue(driver.findElement(By.xpath("//p//span")).getText().contains("7"));
    List<WebElement> list2=driver.findElements(tagText);
    Assert.assertTrue(list2.get(2).getText().contains("test"));
    Assert.assertTrue(list2.get(0).getText().contains("node"));
    Assert.assertTrue(list2.get(1).getText().contains("javascript"));
    List<WebElement> removelist=driver.findElements(removeTag);
    removelist.get(1).click();
    List<WebElement> list3=driver.findElements(tagText);
    Assert.assertTrue(driver.findElement(numOfTags).getText().contains("8"));
    Assert.assertTrue(list3.get(0).getText().contains("no"));
    Assert.assertTrue(list3.get(1).getText().contains("test"));

    ElementActions.clickElement(driver,removeAll);
    Assert.assertTrue(driver.findElement(numOfTags).getText().contains("10"));
    ElementActions.typeInElement(driver,textArea,"test");
    ElementActions.pressKey(driver,textArea, Keys.ENTER);
    List<WebElement> addList=driver.findElements(tagText);
    Assert.assertTrue(driver.findElement(numOfTags).getText().contains("9"));
    Assert.assertEquals(addList.size(), 1);
    Assert.assertTrue(addList.get(0).getText().contains("test"));


}
}
