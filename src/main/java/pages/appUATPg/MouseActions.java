package pages.appUATPg;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;

public class MouseActions {
    private final WebDriver driver;
    public MouseActions(WebDriver driver){
        this.driver=driver;
    }

    public void mouseActions(){
        By mouseArea= By.id("click_area");
        By mouseClickType= By.id("click_type");
        ElementActions.doubleClickElement(driver,mouseArea);
        Assert.assertTrue(ElementActions.getText(driver,mouseClickType).contains("Double-Click"));
        ElementActions.clickElement(driver,mouseArea);
        Assert.assertEquals(ElementActions.getText(driver,mouseClickType),"Click");
        ElementActions.contextClickElement(driver,mouseArea);
        Assert.assertTrue(ElementActions.getText(driver,mouseClickType).contains("Right-Click"));
    }

    public void hovering(){
        By hoverTarget=By.className("dropbtn");
        By choice=By.xpath("(//div[@class='dropdown-content']//p)[2]");
        ElementActions.hoverOverElement(driver,hoverTarget);
        ElementActions.clickElement(driver,choice);
        Assert.assertTrue(ElementActions.getText(driver,By.id("hover_validate")).contains("Python"));
    }


    public void dragAndDrop(){
        By dragSource=By.id("drag_source");
        By dropSource=By.id("drop_target");
        By assertion=new ByChained(dropSource,By.tagName("h3"));
        ElementActions.dragAndDropByElement(driver,dragSource,dropSource);
        Assert.assertTrue(ElementActions.getText(driver,assertion).contains("Drop is suc"));
    }
}
