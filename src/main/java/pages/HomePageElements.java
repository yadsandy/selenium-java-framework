package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.CommonActions;

import static driver.DriverManager.getDriver;

public class HomePageElements extends CommonActions {

    // All objects should be defined here
    private final By topUp_Lbl = By.xpath("//button[contains(text(),'Top Up')]");
    private final By logout_Btn = By.xpath("//button[contains(text(),'Logout')]");
    private final By header_Lbl = By.xpath("//header/ul[3]/li[1]/a[1]");

    public HomePageElements(WebDriver driver) {
        super(driver);
    }

    // To check Home page is visible after login
    public void checkHomePageAfterLogin(String url) {
        Assert.assertTrue(getDriver().getCurrentUrl().equals(url));
        Assert.assertTrue(checkElementIsDisplayed(topUp_Lbl));
    }

    // Open Profile and click on logout button on Home page
    public void performLogout() {
        click(header_Lbl);
        click(logout_Btn);
    }

}
