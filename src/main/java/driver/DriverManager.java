package driver;

import org.openqa.selenium.WebDriver;

import static utils.Constants.BROWSER;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }

    public WebDriver createInstance() {
        return BrowserFactory.valueOf(BROWSER.toUpperCase()).createDriver();
    }

}
