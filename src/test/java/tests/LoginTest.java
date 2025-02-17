package tests;

import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import pages.HomePageElements;
import pages.LoginPageElements;
import utils.DataReader;
import utils.Feature;

import java.io.IOException;
import java.util.HashMap;

import static driver.DriverManager.getDriver;
import static utils.Constants.LOGIN;


public class LoginTest extends BaseTest {
    private final Logger log = LogManager.getLogger(LoginTest.class.getName());
    private HashMap<String, String> data;
    LoginPageElements loginPageElements;
    HomePageElements homePageElements;
    
    LoginTest(){
    loginPageElements = new LoginPageElements(getDriver());
    homePageElements = new HomePageElements(getDriver());

    }

    @Feature(LOGIN)
    @Test(priority = 1, description = "This method is to check the valid scenarios for login page")
    public void performLoginWithValidCredentials() throws IOException, FilloException {
        loginPageElements.goToLoginUrl();
        // get data from excel sheet for TC5
        data = new DataReader().getTestData("login", "TC5");
        // enter valid email and password and click on login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // verify homepage url and check home page is displayed.
        homePageElements.checkHomePageAfterLogin(data.get("url"));
        log.info("Successfully Logged In");
    }
    @Feature(LOGIN)
    @Test(priority = 2, description = "This method is to check all the invalid scenarios for login page")
    public void performLoginWithInvalidCredentials() throws IOException, FilloException {
        loginPageElements.goToLoginUrl();
        // get data from excel sheet for TC1
        data = new DataReader().getTestData("login", "TC1");
        // enter invalid email and password and click on login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // check error message for invalid login
        loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
        log.info("verified invalid login error message for TC1");
        // get data from excel sheet for TC2
        data = new DataReader().getTestData("login", "TC2");
        // enter invalid email and password and click on login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // check error message for invalid login
        loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
        log.info("verified invalid login error message for TC2");
        // get data from excel sheet for TC3
        data = new DataReader().getTestData("login", "TC3");
        // enter invalid email and password and click on login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // check error message for invalid login
        loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
        log.info("verified invalid login error message for TC3");
        // get data from excel sheet for TC4
        data = new DataReader().getTestData("login", "TC4");
        // enter invalid email and password and click on login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // check error message for invalid login
        loginPageElements.checkErrorMessageForInvalidLogin(data.get("error"));
        log.info("verified invalid login error message for TC4");
    }
    @Feature(LOGIN)
    @Test(priority = 3, description = "This method is to check forgot password and remember me option")
    public void checkForgotPasswordAndRememberMeOption() throws IOException, FilloException {
        loginPageElements.goToLoginUrl();
        // check forgot password option on Login page
        loginPageElements.checkForgotPasswordPage();
        // Go back to login page from forgot password screen
        loginPageElements.goBackToLoginScreen();
        // checked remember me option on login screen
        loginPageElements.clickOnRememberMe();
        data = new DataReader().getTestData("login", "TC5");
        // enter valid data and proceed to login
        loginPageElements.completeLoginForm(data.get("Username"), data.get("Password"));
        // perform logout from home page
        homePageElements.performLogout();
        // check email on homepage after logged out
        loginPageElements.checkEmailIDAfterLoggedout(data.get("Username"));
    }



}
