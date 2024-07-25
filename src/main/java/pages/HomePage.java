package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By logoutButton = By.cssSelector("a[href='/logout']");
    By successMessage = By.cssSelector(".flash.success");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoutButtonDisplayed() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
