package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CommonActions;

public class RegisterPageElements extends CommonActions {

    public CommonActions commonActions;
    // All objects should be defined here
    private By email_Txt = By.cssSelector("#email");
    private By password_Txt = By.cssSelector("#password");
    private By passwordConfirm_Txt = By.cssSelector("#password-confirm");
    private By register_Btn = By.xpath("//input[@type='submit']");
    private By register_Lbl = By.cssSelector("#kc-page-title");
    private By errorMsg = By.xpath("(//div/span)[3]");
    private By backToLogin_Btn = By.linkText("« Back to Login");


    // To fill the login form using email and password and click on sign in button
    public void completeRegisterForm(String email, String password, String confirmPassword) {
        Assert.assertTrue(checkElementIsDisplayed(register_Lbl));
        Assert.assertTrue(checkElementIsDisplayed(backToLogin_Btn));
        clearFieldAndEnterText(email_Txt, email);
        clearFieldAndEnterText(password_Txt, password);
        clearFieldAndEnterText(passwordConfirm_Txt, confirmPassword);
        click(register_Btn);
    }

    // To check error message in case of invalid login credentials.
    public void checkErrorMessageForInvalidRegister(String error) {
        Assert.assertTrue(checkElementIsDisplayed(errorMsg));
        Assert.assertTrue(compareText(errorMsg, error));
    }

    public void checkAndClickOnBackButton() {
        Assert.assertTrue(checkElementIsDisplayed(backToLogin_Btn));
        click(backToLogin_Btn);
    }
}
