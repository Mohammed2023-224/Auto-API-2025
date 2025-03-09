package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationMenu {
    private final WebDriver driver;
    public NavigationMenu (WebDriver driver){
        this.driver=driver;
    }

    By links(String option){
        return By.xpath("//a[text()='"+option+"']");
    }
By assertion=By.id("title");
By goBack=By.xpath("//a[text()='Go Back']");
    public void handleNavigation(){
        ElementActions.clickElement(driver,links("About"));
        WaitActions.explicitWaitByCondition(driver,assertion,"visible",3);
        ElementActions.assertElementContainsText(driver,assertion,"About Page");
        ElementActions.clickElement(driver,goBack);
        WaitActions.explicitWaitByCondition(driver,links("Blog"),"visible",3);
        ElementActions.clickElement(driver,links("Blog"));
        WaitActions.explicitWaitByCondition(driver,assertion,"visible",3);
        ElementActions.assertElementContainsText(driver,assertion,"Blog Page");
        ElementActions.clickElement(driver,goBack);
        WaitActions.explicitWaitByCondition(driver,links("Contact"),"visible",3);

    }
}
