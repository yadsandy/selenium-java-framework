package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;
import static driver.DriverManager.getDriver;

public class CommonActions {
     WebDriver driver;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
    }
    public  void click(By locator) {
       getDriver().findElement(locator).click();
    }

    public  void clearFieldAndEnterText(By locator, String value) {
       getDriver().findElement(locator).clear();
       getDriver().findElement(locator).sendKeys(value);
    }

    public  void navigateTo(String url) {
       getDriver().get(url);
    }

    public  void selectFromDropdown(WebElement dropdown, String value) {
        Select dd = new Select(dropdown);
        dd.selectByValue(value);
    }

    public boolean checkElementIsDisplayed(By locator) {
        return getDriver().findElement(locator).isDisplayed();
    }

    public boolean compareText(By locator, String message) {
        return getDriver().findElement(locator).getText().trim().equalsIgnoreCase(message.trim());
    }

    public String getText(By locator) {
        return getDriver().findElement(locator).getText();
    }

    public String randomEmailGenerator() {
        String name = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder nameBuild = new StringBuilder();
        Random rnd = new Random();
        while (nameBuild.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * name.length());
            nameBuild.append(name.charAt(index));
        }
        String saltStr = nameBuild.toString();
        saltStr = saltStr + "@yopmail.com";
        return saltStr;

    }
}
