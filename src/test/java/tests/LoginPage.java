package tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import base.Base;
import pages.HomePageElements;
import pages.LoginPageElements;
import utils.CommonActions;
import utils.ListenerClass;
import utils.Utils;

@Listeners(ListenerClass.class)
public class LoginPage extends Base {

	public WebDriver driver;
	public CommonActions commonActions;
	LoginPageElements loginPageElements;
	HomePageElements homePageElements;
	ExtentTest test;
	private Logger log = LogManager.getLogger(LoginPage.class.getName());
	private HashMap<String, String> data;

	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized.");
		commonActions = new CommonActions(driver);
		loginPageElements = new LoginPageElements(driver);
		homePageElements = new HomePageElements(driver);
		commonActions.navigateTo(prop.getProperty("url"));
	}

	@Test(priority = 0, description = "This method is to check the valid scenarios for login page")
	public void performLoginWithValidCredentials() throws IOException, FilloException {
		// get data from excel sheet for TC5
		data = new Utils().getTestData("login","TC5");
		// enter valid email and password and click on login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// verify homepage url and check home page is displayed.
		homePageElements.checkHomePageAfterLogin(data.get("url"));
		log.info("Successfully Logged In");
	}

	@Test(priority = 1, description = "This method is to check all the invalid scenarios for login page")
	public void performLoginWithInvalidCredentials() throws IOException, FilloException {
		// get data from excel sheet for TC1
		data = new Utils().getTestData("login","TC1");
		// enter invalid email and password and click on login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// check error message for invalid login
		loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
		log.info("verified inavlid login error message for TC1");
		// get data from excel sheet for TC2
		data = new Utils().getTestData("login","TC2");
		// enter invalid email and password and click on login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// check error message for invalid login
		loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
		log.info("verified inavlid login error message for TC2");
		// get data from excel sheet for TC3
		data = new Utils().getTestData("login","TC3");
		// enter invalid email and password and click on login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// check error message for invalid login
		loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
		log.info("verified inavlid login error message for TC3");
		// get data from excel sheet for TC4
		data = new Utils().getTestData("login","TC4");
		// enter invalid email and password and click on login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// check error message for invalid login
		loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
		log.info("verified inavlid login error message for TC4");
	}

	@Test(priority = 2, description = "This method is to check forgot password and remember me option")
	public void checkForgotPasswordAndRememberMeOption() throws IOException, FilloException {
		// check forgot password option on Login page
		loginPageElements.checkForgotPasswordPage();
		// Go back to login page from forgot password screen
		loginPageElements.goBackToLoginScreen();
		// checked remember me option on login screen
		loginPageElements.clickOnRememberMe();
		data = new Utils().getTestData("login","TC5");
		// enter valid data and proceed to login
		loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
		// perform logout from home page
		homePageElements.performLogout();
		// check email on homepage after logged out
		loginPageElements.checkEmailIDAfterLoggedout(data.get("Username"));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
