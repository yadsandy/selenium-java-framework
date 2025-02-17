package tests;

import com.codoid.products.exception.FilloException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePageElements;
import pages.LoginPageElements;
import pages.RegisterPageElements;
import utils.DataReader;
import utils.Feature;
import utils.TestListener;

import java.io.IOException;
import java.util.HashMap;

import static driver.DriverManager.getDriver;
import static utils.Constants.REGISTER;

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest {

    LoginPageElements loginPageElements;
    RegisterPageElements registerPageElements;
    HomePageElements homePageElements;
    private final Logger log = LogManager.getLogger(RegisterTest.class.getName());
    private HashMap<String, String> data;

    RegisterTest(){
        loginPageElements = new LoginPageElements(getDriver());
        homePageElements = new HomePageElements(getDriver());
        registerPageElements = new RegisterPageElements(getDriver());

    }
    @Feature(REGISTER)
    @Test(priority = 1, description = "This method is to check the valid scenarios for register page")
    public void performRegistrationWithValidCredentials() throws IOException, FilloException {
        registerPageElements.goToRegisterPage();

        // get data from excel sheet for TC5
        data = new DataReader().getTestData("register", "TC6");
        // enter valid email, password and confirm password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm("", data.get("Password"),
                data.get("ConfirmPassword"));
        // verify homepage url and check home page is displayed.
        homePageElements.checkHomePageAfterLogin(data.get("url"));
        log.info("Successfully Registered");
    }
    @Feature(REGISTER)
    @Test(priority = 2, description = "This method is to check all the invalid scenarios for register page")
    public void performRegistrationWithInvalidCredentials() throws IOException, FilloException {
        registerPageElements.goToRegisterPage();

        // get data from excel sheet for TC1
        data = new DataReader().getTestData("register", "TC1");
        // enter invalid email and password and click on register
        loginPageElements.clickOnRegister();
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC1");
        // get data from excel sheet for TC2
        data = new DataReader().getTestData("register", "TC2");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC2");
        // get data from excel sheet for TC3
        data = new DataReader().getTestData("register", "TC3");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC3");
        // get data from excel sheet for TC4
        data = new DataReader().getTestData("register", "TC4");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC4");
        // get data from excel sheet for TC4
        data = new DataReader().getTestData("register", "TC5");
        // enter invalid email and password and click on register
        registerPageElements.completeRegisterForm(data.get("Username"), data.get("Password"),
                data.get("ConfirmPassword"));
        // check error message for invalid register
        registerPageElements.checkErrorMessageForInvalidRegister(data.get("error"));
        log.info("verified invalid register error message for TC5");
    }
    @Feature(REGISTER)
    @Test(priority = 3, description = "Check back button on register page")
    public void verifyBackButtonOnRegister() {
        registerPageElements.goToRegisterPage();
        loginPageElements.clickOnRegister();
        registerPageElements.checkAndClickOnBackButton();
        Assert.assertTrue(registerPageElements.checkForgetText());

    }


}
