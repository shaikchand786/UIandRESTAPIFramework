package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentBaseTest {

	protected ExtentReports extent;
    protected ExtentTest test;
    
    @BeforeClass
    public void setup() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Reports/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
	
}
