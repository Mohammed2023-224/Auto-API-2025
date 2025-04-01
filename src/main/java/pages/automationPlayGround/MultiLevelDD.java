package pages.automationPlayGround;

import engine.gui.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

public class MultiLevelDD {
private final WebDriver driver;
public MultiLevelDD(WebDriver driver){
    this.driver=driver;
}
private By firstLayer (int option){
   return By.xpath("(//a[contains(@href,'home')])["+option+"]");
}

    private By links (String option){
        return new ByAll(By.xpath("//a[text()='"+option+"']"),
                By.xpath("//h2[text()='"+option+"']/parent::a")) ;
    }
public void handleMultiLevelDropDown(){
    ElementActions.clickElement(driver,firstLayer(4));
    ElementActions.clickElement(driver,links("Settings"));
    ElementActions.clickElement(driver,links("HTML"));

}

}
