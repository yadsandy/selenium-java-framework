package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static utils.Constants.HEADLESS;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.setAcceptInsecureCerts(true);

            if (HEADLESS) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1850,1000");
            }

            return new ChromeDriver(options);
        }
    }, EDGE {
        @Override
        public WebDriver createDriver() {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.setAcceptInsecureCerts(true);

            if (HEADLESS) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1850,1000");
            }
            return new EdgeDriver(options);
        }
    }, FIREFOX {
        @Override
        public WebDriver createDriver() {
            FirefoxOptions options = new FirefoxOptions();

            options.setAcceptInsecureCerts(true);

            if (HEADLESS) {
                options.addArguments("-headless");
                options.addArguments("--width=1850");
                options.addArguments("--height=1000");
            }

            return new FirefoxDriver(options);
        }
    };

    public abstract WebDriver createDriver();

}
