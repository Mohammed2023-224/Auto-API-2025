package engine.actions;

import engine.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class DevToolsActions {
private final DevTools devTools;

private  DevToolsActions(WebDriver driver)
{this.devTools = ((HasDevTools) driver).getDevTools();}


    public static DevToolsActions createDevToolsActions(WebDriver driver) {
        return new DevToolsActions(driver);
    }

public  void createSession(){
    devTools.createSession();
    CustomLogger.logger.info("Create new session");
}

public  void enableNetwork(){
    devTools.send((Network.enable(Optional.empty(), Optional.empty(),
            Optional.empty())));
    CustomLogger.logger.info("Enable network");
}

public  Set<String> filterAllUrlsContainingText(String text){
    Set<String> uniqueUrls = new LinkedHashSet<>();
    devTools.addListener(Network.requestWillBeSent(),request -> {
        String url = request.getRequest().getUrl();
        if (url.contains(text)) {
            uniqueUrls.add(url);
            CustomLogger.logger.info("Add [{}] to the list",url);
        }});
    return uniqueUrls;
}

public void closeSession(){
    devTools.close();
    CustomLogger.logger.info("Close session");
}
}

