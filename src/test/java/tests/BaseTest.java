package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.DataReader;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static driver.DriverManager.getDriver;
import static utils.Constants.WAIT_IMPLICIT;

public class BaseTest {

    private final WebDriver driver = new DriverManager().createInstance();
    public Properties prop;

    @BeforeSuite
    public void beforeExecution() throws IOException {
        prop = DataReader.loadConfig();
        DriverManager.setDriver(driver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_IMPLICIT));
        getDriver().manage().window().maximize();
    }


    @AfterSuite()
    public void afterSuite() {
        DriverManager.quit();
    }

}
