package pages.automationPlayGround;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MouseHover {
    private final WebDriver driver;
    public MouseHover (WebDriver driver){
        this.driver=driver;
    }
    By pic = By.xpath("//img[contains(@src,'spider')]");
    By currentPrice = By.cssSelector(".current-price");
    By oldPrice = By.cssSelector(".old-price");
    By movieName = By.cssSelector(".movie-title");
    public void handleHovering(){
        ElementActions.hoverOverElement(driver,pic);
        ElementActions.assertElementContainsText(driver,currentPrice,"24");
        ElementActions.assertElementContainsText(driver,oldPrice,"38");
        ElementActions.assertElementContainsText(driver,movieName,"NO");

    }
}
