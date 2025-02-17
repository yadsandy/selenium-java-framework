package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.CommonActions;
import static utils.Constants.URL;


public class LoginPageElements extends CommonActions {

    public By forgotPassword_Txt = By.xpath("//h1[@id='kc-page-title']");
    // All objects should be defined here
    private final By email_Txt = By.id("username");
    private final By password_Txt = By.id("password");
    private final By login_Btn = By.cssSelector("#kc-login");
    private final By remeberMe_Btn = By.id("rememberMe");
    private final By forgotPassword_Lbl = By.linkText("Forgot Password?");
    private final By errorMsg = By.cssSelector("#input-error");
    private final By backToLogin_Btn = By.linkText("« Back to Login");
    private final By register_Btn = By.linkText("Register");

    public LoginPageElements(WebDriver driver) {
        super(driver);
    }

    // naviagte to login page
    public void goToLoginUrl(){
        navigateTo(URL);
    }
    // To fill the login form using email and password and click on sign in button
    public void completeLoginForm(String email, String password) {
        Assert.assertTrue(checkElementIsDisplayed(remeberMe_Btn));
        Assert.assertTrue(checkElementIsDisplayed(forgotPassword_Lbl));
        clearFieldAndEnterText(email_Txt, email);
        clearFieldAndEnterText(password_Txt, password);
        click(login_Btn);
    }

    // To check error message in case of invalid login credentials.
    public void checkErrorMessageForInvalidLogin(String error) {
        Assert.assertTrue(checkElementIsDisplayed(errorMsg));
        Assert.assertTrue(compareText(errorMsg, error));
    }

    // verify the forgot password page
    public void checkForgotPasswordPage() {
        click(forgotPassword_Lbl);
        Assert.assertTrue(checkElementIsDisplayed(forgotPassword_Txt));
    }

    // go back to login screen
    public void goBackToLoginScreen() {
        click(backToLogin_Btn);
    }

    // checked the remember me option
    public void clickOnRememberMe() {
        click(remeberMe_Btn);
    }

    // logged out and check the email is showing or not
    public void checkEmailIDAfterLoggedout(String email) {
        String emailAfterLoggedout = getText(email_Txt);
        Assert.assertTrue(emailAfterLoggedout.equalsIgnoreCase(email));
    }

    public void clickOnRegister() {
        click(register_Btn);
    }

}
