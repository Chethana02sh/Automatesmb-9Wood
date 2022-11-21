package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import base.WebActions;
import reports.ExtentReportManager;

public abstract class Pages {
	
	public WebActions actions;
	public ExtentReportManager report;
	 
	public Pages(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actions = new WebActions(driver);
		report=ExtentReportManager.getInstance();
	}

}
