package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.ExtentBaseTest;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTest extends ExtentBaseTest {
	
	WebDriver driver;
	
    @Test
    public void logoutTest() {
    	test = extent.createTest("Login Test", "Test to validate login functionality");
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
    	driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoutButtonDisplayed());

        String successMessage = homePage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("You logged into a secure area!"), "Expected success message not found!");

        test.pass("Login Test Passed");
        test = extent.createTest("Logout Test", "Test to validate logout functionality");

        homePage.clickLogoutButton();
        Assert.assertTrue(driver.findElement(loginPage.loginButton).isDisplayed());

        String logoutSuccessMessage = loginPage.getSuccessMessage();
        Assert.assertTrue(logoutSuccessMessage.contains("You logged out of the secure area!"), "Expected success message not found!");

        test.pass("Logout Test Passed");
    }
    
    @AfterMethod
    public void quitBrowser() {
    	if(driver != null) {
    		driver.quit();
    	}
    }
}
