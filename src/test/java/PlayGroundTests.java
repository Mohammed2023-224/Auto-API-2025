import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.automationPlayGround.DynamicTableTab;
import pages.automationPlayGround.HomePage;
import pages.automationPlayGround.VerifyAccount;

import java.time.Duration;

public class PlayGroundTests extends BaseTest{
HomePage homePage;
DynamicTableTab dynamicTableTab;
VerifyAccount verifyAccount;
@Test
public void dynamicTableTest(){
    homePage.clickPage("Dynamic Table");
    dynamicTableTab.handleDynamicTable();
    driver.navigate().refresh();
    dynamicTableTab.handleDynamicTable();
    driver.navigate().refresh();
    dynamicTableTab.handleDynamicTable();
    driver.navigate().refresh();
    dynamicTableTab.handleDynamicTable();
}
    @Test
    public void verifyAccountTest(){
        homePage.clickPage("Verify Your Account");
        verifyAccount.handleVerification();

    }

@BeforeMethod()
public void navigateToHomePage(){
    homePage.navigateToWebSite();
}

@BeforeClass
private void initClasses(){
    homePage=new HomePage(driver);
    dynamicTableTab=new DynamicTableTab(driver);
    verifyAccount=new VerifyAccount(driver);
}

}
