package pages.automationPlayGround;

import engine.actions.DevToolsActions;
import engine.actions.ElementActions;
import engine.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.*;

public class RedirectChain {
    private final WebDriver driver;
    public RedirectChain(WebDriver driver) {
        this.driver = driver;
    }
    By directBtn = By.id("redirect");
    By goBack = By.xpath("//a[text()='Go Back']");

    public void handleRedirection() {
        DevToolsActions devToolsActions=DevToolsActions.createDevToolsActions(driver);
        devToolsActions.createSession();
        devToolsActions.enableNetwork();
        Set<String> uniqueUrls = devToolsActions.filterAllUrlsContainingText("redirect");
        List<String> expectedTexts = Arrays.asList("second", "third", "fourth", "fifth", "sixth", "last");
        ElementActions.clickElement(driver, directBtn);
WaitActions.explicitWaitByCondition(driver,goBack,"visible",15);
        for (String expectedText : expectedTexts) {
            boolean found = uniqueUrls.stream().anyMatch(url -> url.contains(expectedText));
            Assert.assertTrue(found, "Expected text '" + expectedText + "' not found in captured URLs.");
        }
        ElementActions.assertElementVisible(driver, goBack);
        ElementActions.clickElement(driver, goBack);
        WaitActions.explicitWaitByCondition(driver, directBtn, "visible", 5);
        ElementActions.assertElementVisible(driver, directBtn);
        devToolsActions.closeSession();
    }
}