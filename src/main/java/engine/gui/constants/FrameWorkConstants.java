package engine.gui.constants;

import engine.gui.fileReaders.PropertyReader;

public class FrameWorkConstants {


    static {
        PropertyReader.readAllFiles();
    }
    public static final String paraBankMainPage =PropertyReader.getProperty("paraBankLink");
    public static final String testPlayGroundMainPage =PropertyReader.getProperty("testAutomationPlayGroundLink");
    public static final String playGroundMainPage =PropertyReader.getProperty("QAPlayGroundLink");
    public static final String schemasPath =PropertyReader.getProperty("schemasPath");
    public static final String payLoadPath =PropertyReader.getProperty("payloadFilesPath");


}
