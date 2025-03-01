package pages.appUATPg;

import engine.actions.BrowserActions;
import engine.actions.ElementActions;
import engine.actions.WaitActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WaitActionsPage {
private final WebDriver driver;


public WaitActionsPage(WebDriver driver){
    this.driver=driver;
}

private final By minWaitTime= By.id("min_wait");
    private final By maxWaitTime= By.id("max_wait");

    private By assertion(String id){
        return By.id(id);
    }
    private By h3Headers(String headers){
        return By.xpath("//h3[text()='"+headers+"']");
    }

    @Step("Type min wait [{wait}]")
    public void typeMinWait(String wait){
        ElementActions.typeInElement(driver,minWaitTime,wait);
    }

    @Step("Type max wait [{wait}]")
    public void typeMaxWait(String wait){
        ElementActions.typeInElement(driver,maxWaitTime,wait);
    }

    @Step("Handling wait for alert")
    public void handleWaitForAlert(int maxTime){
        By alertTrigger=By.id("alert_trigger");
        By promptTrigger=By.id("prompt_trigger");
        WaitActions.explicitWaitByCondition(driver,h3Headers("Wait for alert to be present"),"visible",5);
        ElementActions.clickElement(driver,alertTrigger);
        WaitActions.waitForAlert(driver,maxTime);
        BrowserActions.acceptAlert(driver);
        Assert.assertTrue(ElementActions.getText(driver,assertion("alert_handled_badge")).contains("Alert handled"));
        ElementActions.clickElement(driver,promptTrigger);
        WaitActions.waitForAlert(driver,maxTime);
        BrowserActions.acceptAlert(driver);
        Assert.assertTrue(ElementActions.getText(driver,assertion("confirm_ok")).contains("Confirm"));
    }


    @Step("Handling wait for visibility")
    public void handleWaitForVisibility(int maxTime){
        By visibleTrigger=By.id("visibility_trigger");
        By visibilitTarget =By.id("visibility_target");
        ElementActions.clickElement(driver,visibleTrigger);
        WaitActions.explicitWaitByCondition(driver, visibilitTarget,"clickable",maxTime);
        ElementActions.clickElement(driver, visibilitTarget);
        Assert.assertTrue(ElementActions.getText(driver,By.xpath("//h3[@class='popover-header']"))
                .contains("Can you see me?"));
    }

    @Step("Handling wait for invisibility")
    public void handleWaitForInvisibility(int maxTime){
        By invisibleTrigger =By.id("invisibility_trigger");
        By invisibilityTarget =By.id("invisibility_target");
        ElementActions.clickElement(driver, invisibleTrigger);
        WaitActions.explicitWaitByCondition(driver, invisibilityTarget,"invisible",maxTime);
        Assert.assertTrue(ElementActions.getText(driver,By.xpath("//p[@id='spinner_gone']"))
                .contains("Thank God that spinner is gone!"));
    }


    @Step("Handling wait for enabled")
    public void handleWaitForEnabled(int maxTime){
        By enabledTrigger =By.id("enabled_trigger");
        By enabledTarget=By.id("enabled_target");
        ElementActions.clickElement(driver, enabledTrigger);
        WaitActions.explicitWaitByCondition(driver,enabledTarget,"enabled",maxTime);
        Assert.assertTrue(ElementActions.getText(driver,enabledTarget)
                .contains("Enabled Button"));
    }

    @Step("Handling wait for title")
    public void handleWaitForTitle(int maxTime){
        By titleTrigger=By.id("page_title_trigger");
        ElementActions.clickElement(driver,titleTrigger);
        WaitActions.explicitWait(driver,maxTime).until(ExpectedConditions.titleContains("My New Title"));
        Assert.assertEquals(driver.getTitle(), ("My New Title!"));
    }

    @Step("Handling wait for text")
    public void handleWaitForText(int maxTime){
        By textTrigger =By.id("text_value_trigger");
        By textTarget =By.id("wait_for_value");
        ElementActions.clickElement(driver, textTrigger);
        WaitActions.explicitWait(driver,maxTime).until(ExpectedConditions.textToBePresentInElementValue(textTarget,"Dennis"));
    }

    @Step("Handling wait for frame")
    public void handleWaitForFrame(int maxTime){
        By frameTrigger =By.id("wait_for_frame");
        By frameButtonarget =By.id("inner_button");
        ElementActions.clickElement(driver, frameTrigger);
        WaitActions.explicitWait(driver,maxTime).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("frm")));
        ElementActions.clickElement(driver, frameButtonarget);
        Assert.assertTrue(ElementActions.getText(driver, frameButtonarget)
                .contains("Clicked"));
    }
}
