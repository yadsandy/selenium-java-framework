package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonActions;

public class LoginPageElements extends CommonActions {

    public By forgotPassword_Txt = By.xpath("//h1[@id='kc-page-title']");
    // All objects should be defined here
    private By email_Txt = By.id("username");
    private By password_Txt = By.id("password");
    private By login_Btn = By.cssSelector("#kc-login");
    private By remeberMe_Btn = By.id("rememberMe");
    private By forgotPassword_Lbl = By.linkText("Forgot Password?");
    private By errorMsg = By.cssSelector("#input-error");
    private By backToLogin_Btn = By.linkText("« Back to Login");
    private By register_Btn = By.linkText("Register");

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
