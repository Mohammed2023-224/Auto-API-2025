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
    ShadowDomTest shadowDomTest;
    StarsRatingWidget starsRatingWidget;
    CoveredElement coveredElement;
    UploadFile uploadFile;
    DownloadFile downloadFile;
    OnboardingModelPopup onboardingModelPopup;
    RightClick rightClick;
    MouseHover mouseHover;
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

    @Test
    public void shadowDomTest(){
        homePage.clickPage("Shadow DOM");
        shadowDomTest.handleShadow();
    }

    @Test
    public void startRatingTest(){
        homePage.clickPage("Stars Rating Widget");
        starsRatingWidget.handleRating();
    }
    @Test
    public void coveredElementTest(){
        homePage.clickPage("Covered Elements");
        coveredElement.handleCoveredElement();
    }

    @Test
    public void uploadFileTest(){
        homePage.clickPage("Upload File");
        uploadFile.handleUploads();
    }

    @Test
    public void DownloadFileTest(){
        homePage.clickPage("Download File");
        downloadFile.handleDownload();
    }
    @Test
    public void onboardingPopUpTest(){
        homePage.clickPage("Onboarding Modal Popup");
        onboardingModelPopup.handlePopup();
    }
    @Test
    public void rightClickTest(){
        homePage.clickPage("Right-Click Context Menu");
        rightClick.handleRightClick();
    }
    @Test
    public void mouseHoverTest(){
        homePage.clickPage("Mouse Hover");
        mouseHover.handleHovering();
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
    shadowDomTest=new ShadowDomTest(driver);
    starsRatingWidget=new StarsRatingWidget(driver);
    coveredElement=new CoveredElement(driver);
    uploadFile=new UploadFile(driver);
    downloadFile=new DownloadFile(driver);
    onboardingModelPopup=new OnboardingModelPopup(driver);
    rightClick=new RightClick(driver);
    mouseHover=new MouseHover(driver);
}

}
