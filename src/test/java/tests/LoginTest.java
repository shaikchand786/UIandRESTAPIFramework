package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.ExtentBaseTest;
import pages.HomePage;
import pages.LoginPage;
import utilities.ScreenshotUtil;

public class LoginTest extends ExtentBaseTest {
	
	WebDriver driver;
	
    @Test
    public void loginTest() {
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
    }

    @Test
    public void invalidLoginTest() {
        test = extent.createTest("Invalid Login Test", "Test to validate login functionality with invalid credentials");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Your username is invalid!"), "Expected error message not found!");

        if (errorMessage.contains("Your username is invalid!")) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, "InvalidLoginTest");
            test.fail("Invalid Login Test Failed").addScreenCaptureFromPath(screenshotPath);
        } else {
            test.pass("Invalid Login Test Passed");
        }
    }
    
    @AfterMethod
    public void quitBrowser() {
    	if(driver != null) {
    		driver.quit();
    	}
    }
}
