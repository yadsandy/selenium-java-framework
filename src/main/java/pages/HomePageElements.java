package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utils.CommonActions;

public class HomePageElements {

	public WebDriver driver;
	public CommonActions commonActions;

	public HomePageElements(WebDriver driver) {
		this.driver = driver;
		commonActions = new CommonActions(driver);
	}

	// All objects should be defined here
	private By forgotPassword_Lbl = By.xpath("//button[contains(text(),'Top Up')]");
	private By logout_Btn = By.xpath("//button[contains(text(),'Logout')]");
	private By header_Lbl = By.xpath("//header/ul[3]/li[1]/a[1]");

	// To check Home page is visible after login
	public void checkHomePageAfterLogin(String url) {
		Assert.assertTrue(driver.getCurrentUrl().equals(url));
		Assert.assertTrue(commonActions.checkElementIsDisplayed(forgotPassword_Lbl));
	}

	// Open Profile and click on logout button on Home page
	public void performLogout() {
		commonActions.click(header_Lbl);
		commonActions.click(logout_Btn);
	}

}
