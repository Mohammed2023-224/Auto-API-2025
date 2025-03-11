package pages.automationPlayGround;

import engine.actions.ElementActions;
import engine.actions.WaitActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.ConnectionType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RedirectChain {
    private final WebDriver driver;
    public RedirectChain (WebDriver driver){
        this.driver=driver;
    }
By directBtn= By.id("redirect");
By info= By.id("info");
    By goBack=By.xpath("//a[text()='Go Back']");

    public void handleRedirection(){
        ElementActions.clickElement(driver, directBtn);
        List<String> expectedTexts = Arrays.asList(
                "second",
                "third",
                "fourth",
                "fifth",
                "sixth",
                "last"
        );
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        ((HasDevTools) driver).getDevTools().send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,             // Not offline
                2000,               // Latency (ms)
                40 * 100,        // Download speed (500 kbps)
                40 * 100,        // Upload speed (200 kbps)
                Optional.of(ConnectionType.CELLULAR3G),  // Connection type
                Optional.empty(),  // Packet loss (default)
                Optional.empty(),  // Packet queue length (default)
                Optional.empty()   // Packet reordering (default)
        ));
        for (String expectedText : expectedTexts) {
            WaitActions.explicitWait(driver,4).until(
                    ExpectedConditions.urlContains(expectedText));
//            ElementActions.assertElementContainsText(driver, info, expectedText);
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedText));
        }
        devTools.send(Network.disable());
        ElementActions.assertElementVisible(driver, goBack);
        ElementActions.clickElement(driver, goBack);
        WaitActions.explicitWaitByCondition(driver, directBtn, "visible", 5);
        ElementActions.assertElementVisible(driver, directBtn);
        }


}
