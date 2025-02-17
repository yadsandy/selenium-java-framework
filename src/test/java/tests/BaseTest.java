package tests;

import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DataReader;
import utils.TestListener;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static driver.DriverManager.getDriver;
import static utils.Constants.WAIT_IMPLICIT;

@Listeners(TestListener.class)
public class BaseTest {
    private final Logger log = LogManager.getLogger(BaseTest.class.getName());
    public Properties prop;

    @BeforeSuite
    public void beforeExecution() {
        log.info("Browser execution started");
    }

    @BeforeMethod
    public void initialize() throws IOException {
        prop = DataReader.loadConfig();
        log.info("Driver is initialized.");
        WebDriver driver = new DriverManager().createInstance();
        DriverManager.setDriver(driver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_IMPLICIT));
        getDriver().manage().window().maximize();
    }


    @AfterMethod()
    public void afterMethod() {
        log.info("close the browser");
        DriverManager.quit();
    }


    @AfterSuite
    public void afterExecution() {
        log.info("Browser execution over");
    }

}
