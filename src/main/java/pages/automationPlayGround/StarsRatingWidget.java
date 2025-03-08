package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarsRatingWidget {
    private final WebDriver driver;
    public StarsRatingWidget (WebDriver driver){
        this.driver=driver;
    }

    By starRatings= By.tagName("label");
    By text=By.cssSelector(".text");
    By num=By.cssSelector(".numb");
    By img(String index){return By.xpath("//li//img[contains(@src,'emoji-"+index+"')]");}

    public void handleRating(){
        List<WebElement> list= driver.findElements(starRatings);
        List<String> textList=new ArrayList<>(Arrays.asList("hate","don't like", "awesome","like","love" ));
        for(int i= list.size()-1;i>=0;i-- ){
            list.get(i).click();
            Assert.assertTrue(ElementActions.getPseudoElementContent(driver,text,"::before").contains(textList.get(i)));
            Assert.assertTrue(ElementActions.getPseudoElementContent(driver,num,"::before").contains(String.valueOf(i+1)));
            WaitActions.explicitWait(driver,2).until(ExpectedConditions.visibilityOfElementLocated(img(String.valueOf(i+1))));
            Assert.assertTrue(ElementActions.isElementVisible(driver,img(String.valueOf(i+1))));
        }
    }

}
