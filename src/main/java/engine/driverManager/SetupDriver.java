package engine.driverManager;

import engine.driverManager.browsers.Chrome;
import engine.driverManager.browsers.Edge;
import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;

public class SetupDriver {

    public WebDriver initDriver(String browser){
        WebDriver driver = null;
        if(browser.equalsIgnoreCase("edge")){
            driver= new Edge().initEdge();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            driver= new Chrome().initChrome();
        }
        return driver;
    }
}
