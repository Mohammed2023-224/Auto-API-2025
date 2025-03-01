package engine.fileReaders;

import engine.reporter.CustomLogger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class PropertyReader {
    static FileReader fr;
    static  Properties prop;

    public static void readAllFiles() {
        prop = new Properties();
        ArrayList<String> files = new ArrayList<String>();
        files.add("path");
        for (String file : files) {
            try {
                CustomLogger.logger.info("Read property file: {}", file);
                fr = new FileReader("src/main/resources/Properties/" + file + ".properties");
                prop.load(fr);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static String  getProperty(String value){
        return prop.getProperty(value);
    }


    public String getSingleProp(String path, String value){
        prop=new Properties();
        try {
            fr=new FileReader(path);
            prop.load(fr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CustomLogger.logger.info("Read value of {} : {}", value,prop.getProperty(value) );
        return prop.getProperty(value);
    }

}
