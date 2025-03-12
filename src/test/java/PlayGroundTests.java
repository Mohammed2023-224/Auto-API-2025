import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.automationPlayGround.*;

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
    ChangingIframe changingIframe;
    NavigationMenu navigationMenu;
    RedirectChain redirectChain;
    BudgetTracker budgetTracker;
    CodeGenerator codeGenerator;
    Slider slider;
@Test
public void dynamicTableTest(){
    homePage.clickPage("Dynamic Table");
    dynamicTableTab.handleDynamicTable();
    getDriver().navigate().refresh();
    dynamicTableTab.handleDynamicTable();
    getDriver().navigate().refresh();
    dynamicTableTab.handleDynamicTable();
    getDriver().navigate().refresh();
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
    @Test
    public void changingIframe(){
        homePage.clickPage("Changeable Iframe");
        changingIframe.handleChangingIframe();
    }
    @Test
    public void navigationMeuTest(){
        homePage.clickPage("Navigation Menu");
        navigationMenu.handleNavigation();
    }
    @Test
    public void redirectChainTest(){
        homePage.clickPage("Redirect Chain");
        redirectChain.handleRedirection();
    }
    @Test
    public void handleBudget(){
        homePage.clickPage("Budget Tracker");
        budgetTracker.handleBudget();
    }
    @Test
    public void codeGenerator(){
        homePage.clickPage("QR Code Generator");
        codeGenerator.handleCode();
    }
    @Test
    public void slider(){
        homePage.clickPage("Rating Range Slider");
        slider.handleSlider();
    }


    @BeforeMethod()
public void navigateToHomePage(){
    homePage.navigateToWebSite();
}

@BeforeClass
private void initClasses(){
    homePage=new HomePage(getDriver());
    dynamicTableTab=new DynamicTableTab(getDriver());
    verifyAccount=new VerifyAccount(getDriver());
    tagsInputBox=new TagsInputBox(getDriver());
    multiLevelDD=new MultiLevelDD(getDriver());
    sortableList=new SortableList(getDriver());
    shadowDomTest=new ShadowDomTest(getDriver());
    starsRatingWidget=new StarsRatingWidget(getDriver());
    coveredElement=new CoveredElement(getDriver());
    uploadFile=new UploadFile(getDriver());
    downloadFile=new DownloadFile(getDriver());
    onboardingModelPopup=new OnboardingModelPopup(getDriver());
    rightClick=new RightClick(getDriver());
    mouseHover=new MouseHover(getDriver());
    changingIframe=new ChangingIframe(getDriver());
    navigationMenu=new NavigationMenu(getDriver());
    redirectChain=new RedirectChain(getDriver());
    budgetTracker=new BudgetTracker(getDriver());
    codeGenerator =new CodeGenerator(getDriver());
    slider=new Slider(getDriver());
}

}
