import engine.driverManager.SetupDriver;
import engine.listeners.TestNGListeners;
import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;


@Listeners(TestNGListeners.class)
public class BaseTest {
    public WebDriver driver;

    @BeforeClass
    public void startDriver(ITestContext context){
        this.driver=new SetupDriver().initDriver("chrome");
    context.setAttribute("driver",this.driver);
    }

    @AfterMethod
    public void refreshDriverAndDeleteCookies(){
        this.driver.navigate().refresh();
        this.driver.manage().deleteAllCookies();
        CustomLogger.logger.info("delete all cookies and refresh page");
    }

    @AfterClass
    public void tearDriver(){
        this.driver.quit();
        CustomLogger.logger.info("Close driver");
    }

}
