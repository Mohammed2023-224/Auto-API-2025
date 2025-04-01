package engine.gui.driverManager.browsers;

import java.util.ArrayList;

public class BrowserOptions {
    public ArrayList<String> getOptions(){
        ArrayList<String> opt= new ArrayList<>();
opt.add("--start-maximized");

        return opt;
    }
}
