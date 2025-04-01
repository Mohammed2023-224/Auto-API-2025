package engine.gui.driverManager.browsers;

import engine.gui.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Edge {

    public WebDriver initEdge(){
        EdgeOptions options=new EdgeOptions();
        options.addArguments(new BrowserOptions().getOptions());
        CustomLogger.logger.info("initiate edge with options: {}", new BrowserOptions().getOptions());
        return new EdgeDriver(options);
    }
}
