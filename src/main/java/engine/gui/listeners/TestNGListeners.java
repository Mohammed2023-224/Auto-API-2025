package engine.gui.listeners;

import engine.gui.actions.SystemMethods;
import engine.gui.reporter.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.Arrays;

public class TestNGListeners extends AllureListener implements ITestListener {

    int numberOfFailedTests=0;
    int numberOfSuccessTest=0;
    int numberOfSkippedTests=0;
    ArrayList<String> successfulTests= new ArrayList<>();
    ArrayList<String> failedTests= new ArrayList<>();
    ArrayList<String> skippedTests= new ArrayList<>();

    public void onTestStart(ITestResult result){
        SystemMethods.deleteDirectory("test_output/output/logs/tests");
        SystemMethods.deleteDirectory("allure-results");
        CustomLogger.logger.info("Start test : {}",result.getName());
    }

    public void onTestSuccess(ITestResult result){
        CustomLogger.logger.info("Test succeded: {}" ,result.getName() );
        numberOfSuccessTest++;
        successfulTests.add(result.getName());
        saveTextLog("test_output/output/logs/tests/currentTest.log");
    }

    public void onTestFailure(ITestResult result){
        WebDriver driver=(WebDriver) result.getTestContext().getAttribute("driver");
        CustomLogger.logger.info("Test failed: {}" ,result.getName() );
        numberOfFailedTests++;
        failedTests.add(result.getName());
        saveTextLog("test_output/output/logs/tests/currentTest.log");
        takeScreenShot(driver, "failed test screenshot");

    }

    public void onTestSkipped(ITestResult result){
        CustomLogger.logger.info("Test skipped: {}",result.getName() );
        numberOfSkippedTests++;
        skippedTests.add(result.getName());
        saveTextLog("test_output/output/logs/tests/currentTest.log");
    }


    public void onStart(ITestContext context){
        SystemMethods.deleteFile("test_output/output/logs/MainLog.log");
        CustomLogger.logger.info("Start Execution");
    }


    public void onFinish(ITestContext context){
        CustomLogger.logger.info("finished Execution");
        CustomLogger.logger.info("Number of all tests: {}", numberOfSuccessTest+numberOfFailedTests+numberOfSkippedTests);
        CustomLogger.logger.info("Number of successful tests: {}", numberOfSuccessTest);
        CustomLogger.logger.info("Name of successful tests: {}",Arrays.deepToString(successfulTests.toArray()));
        CustomLogger.logger.info("Number of failed tests: {}", numberOfFailedTests);
        CustomLogger.logger.info("Name of failed tests: {}",Arrays.deepToString(failedTests.toArray()));
        CustomLogger.logger.info("Number of skipped tests: {}", numberOfSkippedTests);
        CustomLogger.logger.info("Name of skipped tests: {}",Arrays.deepToString(skippedTests.toArray()));
//        SystemMethods.runFile("runAllure.bat");
    }
}
