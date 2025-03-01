package engine.driverManager.browsers;

import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {

    public WebDriver initChrome(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments(new BrowserOptions().getOptions());
        CustomLogger.logger.info("initiate chrome with options: {}", new BrowserOptions().getOptions());
        return new ChromeDriver(options);
}

}
