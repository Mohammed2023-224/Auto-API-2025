import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.automationPlayGround.*;

import java.time.Duration;

public class PlayGroundTests extends BaseTest{
HomePage homePage;
DynamicTableTab dynamicTableTab;
VerifyAccount verifyAccount;
TagsInputBox tagsInputBox;
MultiLevelDD multiLevelDD;
SortableList sortableList;
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

    @Test
    public void tagsInputBox(){
        homePage.clickPage("Tags Input Box");
        tagsInputBox.handleTags();
    }

    @Test
    public void multiLevelDD(){
        homePage.clickPage("Multi Level Dropdown");
        multiLevelDD.handleMultiLevelDropDown();
    }

    @Test
    public void SortableList(){
        homePage.clickPage("Sortable List");
        sortableList.handleSorting();
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
    tagsInputBox=new TagsInputBox(driver);
    multiLevelDD=new MultiLevelDD(driver);
    sortableList=new SortableList(driver);
}

}
