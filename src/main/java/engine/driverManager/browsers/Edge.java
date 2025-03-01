package engine.driverManager.browsers;

import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.List;

public class Edge {

    public WebDriver initEdge(){
        EdgeOptions options=new EdgeOptions();
        options.addArguments(new BrowserOptions().getOptions());
        CustomLogger.logger.info("initiate edge with options: {}", new BrowserOptions().getOptions());
        return new EdgeDriver(options);
    }
}
