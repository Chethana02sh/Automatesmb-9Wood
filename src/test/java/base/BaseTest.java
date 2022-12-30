package base;

import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import reports.ExtentReportManager;
import utils.FileManager;

public class BaseTest {
	public WebDriver driver;
	protected WebActions actions;
	protected LoginPage loginPage;
    public SoftAssert softAssert;
	
	 @BeforeSuite
     public void configBSuite(){
		ExtentReportManager.getInstance().configReport(FilePaths.EXTENT_REPORT_PATH, 
				"Automation", FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url"));
         RestAssured.baseURI=FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url");
	 }

     @BeforeMethod
     public void setUp(ITestResult result){
         softAssert=new SoftAssert();
    	 ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
       driver= new DriverFactory().initBrowser(FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("browsername"));
       actions=new WebActions(driver);
       actions.setImpicitWait(10);
       actions.maximizeScreen();
       driver.navigate().to(FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url"));
       loginPage=new LoginPage(driver);
       loginPage.login(FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("username"),
    		   FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("password"));
     }

     @AfterMethod
     public void tearDown(ITestResult result){
    	 ExtentReportManager.getInstance().getTestResult(result, getScreenshot());
    	// driver.close();
         softAssert.assertAll();
     }

     @AfterSuite
     public void configASuite(){
    	 ExtentReportManager.getInstance().flushReport();
     }
     
     public String getScreenshot() {
 		TakesScreenshot screenshot=(TakesScreenshot)driver;
 		return screenshot.getScreenshotAs(OutputType.BASE64);
 	}


}
