package reports;

import java.io.File;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import base.FilePaths;
import utils.FileManager;

public class ExtentReportManager {
	
	private static ExtentReports reports;
	private static ExtentTest test;
	
	private static ExtentReportManager manager;
	
	private ExtentReportManager() {}
	
	public void configReport(String filePath, String reportName, String documentTitle) {
		ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
		reporter.config().setReportName(reportName);
		reporter.config().setDocumentTitle(documentTitle);
		reporter.config().setTheme(Theme.DARK);
		reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("URL", FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("url"));
		reports.setSystemInfo("Browser", FileManager.getInstance(FilePaths.PROPERTY_FILE).getPropertyValue("browsername"));
	}
	
	public void createTest(String testcaseName) {
		test=reports.createTest(testcaseName);
	}
	
	public void flushReport() {
		reports.flush();
	}
	
	
	public void info(String message) {
		test.info(message);
	}

	public void pass(String message){
		test.pass(message);
	}

	public void fail(String message){
		test.fail(message);}
	
	public  static ExtentReportManager getInstance() {
		if(manager==null) {
			return manager==null?manager=new ExtentReportManager():manager;
		}
		return manager;
	}
	
	public void getTestResult(ITestResult result, String screenShot) {
		if(ITestResult.SUCCESS==result.getStatus()) {
			test.pass(result.getMethod().getMethodName());
		}else if(ITestResult.SKIP==result.getStatus()) {
			test.skip(result.getMethod().getMethodName());
			test.info(result.getThrowable());
			test.addScreenCaptureFromBase64String(screenShot);
		}
		else if(ITestResult.FAILURE==result.getStatus()) {
			test.fail(result.getMethod().getMethodName());
			test.info(result.getThrowable());
			test.addScreenCaptureFromBase64String(screenShot);
		}
	}
	
	

}
