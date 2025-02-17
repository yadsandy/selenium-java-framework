package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import driver.BrowserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;

import static com.aventstack.extentreports.reporter.configuration.ViewName.*;
import static driver.DriverManager.getDriver;
import static org.testng.ITestResult.*;
import static utils.Constants.BROWSER;
import static utils.PropertyReader.getConfigValue;


public class ExtentReportService {
    public static final String FILE_SEPARATOR = File.separator;
    public static final String EXTENT_FULL_REPORT_DIRECTORY = getConfigValue("extent_full_report_dir");
    public static final String REPORT_FILE_NAME_PREFIX = getConfigValue("extent_report_file_name_prefix");
    public static final String REPORTER_THEME = getConfigValue("extent_reporter_theme");
    public static final String DOCUMENT_TITLE = getConfigValue("extent_document_title");
    public static final String REPORT_NAME = getConfigValue("extent_reporter_name");
    public static final String PROJECT_NAME = getConfigValue("project_name");
    public static final String SCREENSHOTS_DIRECTORY = getConfigValue("extent_screenshots_dir");
    public static final String EXECUTOR = getConfigValue("test_executor");
    private static final ExtentReports extentReports = new ExtentReports();
    private static final Logger logger = LogManager.getLogger();

    public void initializeExtentReporter(String timestamp) {
        ExtentSparkReporter sparkAllTestsReporter = new ExtentSparkReporter(EXTENT_FULL_REPORT_DIRECTORY
                + FILE_SEPARATOR + REPORT_FILE_NAME_PREFIX + timestamp + ".html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{DASHBOARD, TEST, CATEGORY, EXCEPTION})
                .apply();

        extentReports.attachReporter(sparkAllTestsReporter);

        sparkAllTestsReporter.config().setTheme(
                REPORTER_THEME.equalsIgnoreCase("dark")
                        ? Theme.DARK : Theme.STANDARD
        );

        sparkAllTestsReporter.config().setDocumentTitle(DOCUMENT_TITLE);
        sparkAllTestsReporter.config().setReportName(REPORT_NAME);

        extentReports.setSystemInfo("Application Name", PROJECT_NAME.toUpperCase());
        extentReports.setSystemInfo("Test Developer", EXECUTOR.toUpperCase());
        extentReports.setSystemInfo("Platform", BROWSER.toUpperCase());
    }

    public void updateExtentReport(ITestResult iTestResult, String timestamp) {
        ExtentTest test = appendTestInformation(iTestResult);

        switch (iTestResult.getStatus()) {
            case FAILURE:
                appendFailureDetails(test, iTestResult, timestamp);
                break;
            case SKIP:
                appendSkipDetails(test, iTestResult, timestamp);
                break;
            case SUCCESS:
                appendSuccessDetails(test, iTestResult, timestamp);
                break;
            default:
                logger.warn("Test result for the test method '{}' is unknown.", iTestResult.getMethod());
        }

        addFeature(iTestResult, test);
    }


    private void addFeature(ITestResult iTestResult, ExtentTest extentTest) {
        String feature = AnnotationReader.getFeatureInTestMethod(iTestResult);

        if (feature != null) {
            extentTest.assignCategory(feature);
        }
    }


    public void flushExtentReport() {
        extentReports.flush();
    }

    private ExtentTest appendTestInformation(ITestResult iTestResult) {
        ExtentTest test = extentReports.createTest(iTestResult.getName())
                .info("<b> Test Class: </b> <br />" + iTestResult.getTestClass().getName())
                .info("<b> Test Method Name: </b> <br />" + iTestResult.getName());

        String iTestDescription = iTestResult.getMethod().getDescription();

        if (iTestDescription != null && !iTestDescription.isEmpty()) {
            test.info("<b> Test Method Description: </b> <br />" + iTestDescription);
        }

        return test;
    }

    private void appendSuccessDetails(ExtentTest test, ITestResult iTestResult, String timestamp) {
        String screenshotPath = takeScreenshotAndReturnFilePath(iTestResult.getName(), timestamp);

        if (screenshotPath != null) {
            test.createNode("<b> Screenshot: </b>")
                    .pass(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    private void appendFailureDetails(ExtentTest test, ITestResult iTestResult, String timestamp) {
        String screenshotPath = takeScreenshotAndReturnFilePath(iTestResult.getName(), timestamp);

        test.createNode("<b> Error Details: </b>")
                .fail("<b> Error Message: </b> <br />" + iTestResult.getThrowable())
                .fail(iTestResult.getThrowable());

        if (screenshotPath != null) {
            test.createNode("<b> Screenshot: </b>")
                    .fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    private void appendSkipDetails(ExtentTest test, ITestResult iTestResult, String timestamp) {
        String screenshotPath = takeScreenshotAndReturnFilePath(iTestResult.getName(), timestamp);

        test.createNode("<b> Error Details: </b>")
                .skip("<b> Error Message: </b> <br />" + iTestResult.getThrowable())
                .skip(iTestResult.getThrowable());

        if (screenshotPath != null) {
            test.createNode("<b> Screenshot: </b>")
                    .skip(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    private String takeScreenshotAndReturnFilePath(String screenshotName, String timestamp) {
        String relativePath = null;

        if (getDriver() != null) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
                File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String destination = SCREENSHOTS_DIRECTORY + FILE_SEPARATOR + screenshotName
                        + " - " + timestamp + ".png";
                relativePath = SCREENSHOTS_DIRECTORY
                        .substring(SCREENSHOTS_DIRECTORY.lastIndexOf('/') + 1).trim()
                        + FILE_SEPARATOR + screenshotName + " - " + timestamp + ".png";
                File finalDestination = new File(destination);
                FileUtils.copyFile(source, finalDestination);
            } catch (Exception e) {
                logger.error("Failed to embed the screenshot to the report!");
                logger.error(e.getMessage());
            }
        }
        return relativePath;
    }

}
