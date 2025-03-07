package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import engine.reporter.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicTableTab {

    private final WebDriver driver;
    public DynamicTableTab (WebDriver driver){
        this.driver=driver;
    }

    By superHero=By.xpath("//div[@class='text-sm font-medium text-white-900']");
    private By superHeroTitle(int index){
        return By.xpath("(//div[@class='text-sm font-medium text-white-900'])["+index+"]");
    }
    private By superHeroName(int index){
        return By.xpath("(//span[@class='text-sm font-medium text-white-900'])["+index+"]");
    }

    public void handleDynamicTable(){
    WaitActions.explicitWaitByCondition(driver,superHeroTitle(1),"visible",5);
        int size=driver.findElements(superHero).size();
        String name= "";
        int elementNo=0;
        for(int i=1; i<=size;i++){
            if (ElementActions.getText(driver,superHeroTitle(i)).contains("Spider-Man")){
                name=ElementActions.getText(driver,superHeroName(i));
                elementNo=i;

                break;
            }
        }
        CustomLogger.logger.info("Name {} was found at index : {}",name,elementNo);
    }
}
