package engine.actions;

import engine.reporter.CustomLogger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SystemMethods {

    public static void deleteDirectory(String path) {
        File directory = new File(path);
        try {
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            context.stop();
            FileUtils.deleteDirectory(directory);
            context.start(); // Restart Log4j2 context
            CustomLogger.logger.info("Deleted the directory {}", path);
        } catch (Exception e) {
            CustomLogger.logger.info("couldn't delete directory {}", path);
        }
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            CustomLogger.logger.warn("File does not exist: {}", path);
            return;
        }
        try {
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            context.stop();
            FileUtils.delete(file);
                context.start();
                CustomLogger.logger.info("Deleted the file: {}", path);
        } catch (Exception e) {
            CustomLogger.logger.error("Couldn't delete file: {}. Error: {}", path, e.getMessage());
        }

    }

    public static void runFile(String path) {
        File file = new File(path);
        file.setExecutable(true);
        if (file.canExecute()) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(file.getAbsolutePath());
                Process process = processBuilder.start();
                process.waitFor();
                CustomLogger.logger.info("File executed: {}",path);
            } catch (IOException | InterruptedException e) {
                CustomLogger.logger.info("file isn't executed: {}",path);
            }
        } else {
            CustomLogger.logger.info("file {} isn't executable type",path);
        }
    }
}
