import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.appUATPg.*;

public class AutomationPlaygroundTests extends BaseTest {
pages.appUATPg.HomePage homePage;
WaitActionsPage waitActionsPage;
PopUpWindows popUpWindows;
Frames frames;
MouseActions mouseActions;
KeyboardActions keyboardActions;
FormPage formPage;
SamplePage samplePage;
AdvancedUI advancedUI;

    @Test()
    public void waitActionsTest() {
        int minWait=2; int maxWait=6;
        homePage.clickPage("Wait Conditions");
    waitActionsPage.typeMinWait(String.valueOf(minWait));
    waitActionsPage.typeMaxWait(String.valueOf(maxWait));
    waitActionsPage.handleWaitForAlert(maxWait);
    waitActionsPage.handleWaitForVisibility(maxWait);
waitActionsPage.handleWaitForInvisibility(maxWait);
waitActionsPage.handleWaitForEnabled(maxWait);
waitActionsPage.handleWaitForTitle(maxWait);
waitActionsPage.handleWaitForText(maxWait);
waitActionsPage.handleWaitForFrame(maxWait);
    }

    @Test()
    public void popUpWindowsTest() {
        homePage.clickPage("Popup Windows");
        popUpWindows.handlePopups();
        }

    @Test()
    public void framesTest() {
        homePage.clickPage("Frames");
        frames.handleFrames();
    }

    @Test()
    public void mouseActionsTest() {
        homePage.clickPage("Mouse Actions");
        mouseActions.mouseActions();
    mouseActions.hovering();
    mouseActions.dragAndDrop();
    }

    @Test()
    public void keyboardActions() {
        homePage.clickPage("Keyboard Actions");
        keyboardActions.handleKeyboard();
    }

    @Test()
    public void formPageTests() {
        homePage.clickPage("Forms");
        formPage.handleForm();
    }

    @Test()
    public void samplePageTest() {
        homePage.clickPage("Sample Pages");
        samplePage.newUser();
        samplePage.loginForm();
        samplePage.pizzaForm();
    }

    @Test()
    public void advancedUITests() {
        homePage.clickPage("Advanced UI Features");
        advancedUI.handleTab();
    }

@BeforeMethod()
public void navigateToHomePage(){
    homePage.navigateHomePage();
}

    @BeforeClass
    private void initClasses(){
        popUpWindows=new PopUpWindows(getDriver());
        homePage=new HomePage(getDriver());
        waitActionsPage=new WaitActionsPage(getDriver());
        frames=new Frames(getDriver());
        mouseActions=new MouseActions(getDriver());
        keyboardActions=new KeyboardActions(getDriver());
        formPage=new FormPage(getDriver());
        samplePage=new SamplePage(getDriver());
        advancedUI=new AdvancedUI(getDriver());
    }
}
