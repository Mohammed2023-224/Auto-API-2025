package engine.constants;

import engine.fileReaders.PropertyReader;

public class FrameWorkConstants {


    static {
        PropertyReader.readAllFiles();
    }
    public static final String paraBankMainPage =PropertyReader.getProperty("paraBankLink");
    public static final String testPlayGroundMainPage =PropertyReader.getProperty("testAutomationPlayGroundLink");
    public static final String playGroundMainPage =PropertyReader.getProperty("QAPlayGroundLink");
    public static final String robotMainPage =PropertyReader.getProperty("RobotFrameworkLink");

}
