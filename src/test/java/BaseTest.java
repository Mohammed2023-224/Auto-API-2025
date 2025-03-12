import engine.driverManager.SetupDriver;
import engine.listeners.TestNGListeners;
import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;


@Listeners(TestNGListeners.class)
public class BaseTest {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }
    @BeforeClass
    public void startDriver(ITestContext context){
        WebDriver driver = new SetupDriver().initDriver("chrome");
        threadLocalDriver.set(driver);
        context.setAttribute("driver",getDriver());
    }

//    @AfterMethod
//    public void refreshDriverAndDeleteCookies(){
//        getDriver().navigate().refresh();
//        getDriver().manage().deleteAllCookies();
//        CustomLogger.logger.info("delete all cookies and refresh page");
//    }
//
//    @AfterClass
//    public void tearDriver(){
//        getDriver().quit();
//                threadLocalDriver.remove(); // Clean up the ThreadLocal
//        CustomLogger.logger.info("Close driver");
//    }

}
