package engine.driverManager.browsers;

import engine.reporter.CustomLogger;

import java.util.ArrayList;
import java.util.Arrays;

public class BrowserOptions {
    public ArrayList<String> getOptions(){
        ArrayList<String> opt= new ArrayList<>();
opt.add("--start-maximized");

        return opt;
    }
}
