package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.CommonActions;

import static driver.DriverManager.getDriver;
import static utils.Constants.URL;


public class RegisterPageElements extends CommonActions {

    public CommonActions commonActions;
    // All objects should be defined here
    private final By email_Txt = By.cssSelector("#email");
    private final By password_Txt = By.cssSelector("#password");
    private final By passwordConfirm_Txt = By.cssSelector("#password-confirm");
    private final By register_Btn = By.xpath("//input[@type='submit']");
    private final By register_Lbl = By.cssSelector("#kc-page-title");
    private final By errorMsg = By.xpath("(//div/span)[3]");
    private final By backToLogin_Btn = By.linkText("« Back to Login");

    public RegisterPageElements(WebDriver driver) {
        super(driver);
    }

    // To fill the login form using email and password and click on sign in button
    public void completeRegisterForm(String email, String password, String confirmPassword) {
        Assert.assertTrue(checkElementIsDisplayed(register_Lbl));
        Assert.assertTrue(checkElementIsDisplayed(backToLogin_Btn));
        if (email.equals("")){
            email=randomEmailGenerator();
        }
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

    public void goToRegisterPage() {
        navigateTo(URL);
    }

    public boolean checkForgetText(){
        LoginPageElements loginPageElements=new LoginPageElements(getDriver());
        return checkElementIsDisplayed(loginPageElements.forgotPassword_Txt);
    }
}
