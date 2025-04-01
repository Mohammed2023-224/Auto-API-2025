package engine.gui.driverManager;

import engine.gui.driverManager.browsers.Chrome;
import engine.gui.driverManager.browsers.Edge;
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
