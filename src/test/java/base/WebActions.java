package base;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebActions {
	
	private WebDriver driver;
	
	public WebActions(WebDriver driver) {
		this.driver=driver;
	}
	
	public void setImpicitWait( int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
	}
	
	public void maximizeScreen() {
		driver.manage().window().maximize();
	}
	/**
	 * Pass the element and provide the text to be type in field
	 * @param element
	 * @param text
	 */
	public void type(WebElement element, String text) {
		element.sendKeys(text);
		
	}
	
	/**
	 * Perform click on element
	 * @param element
	 */
	public void click(WebElement element) {
		element.click();
	}
	
	/**
	 * wait for the visbility of element for 10 seconds
	 * @param element
	 */
	public void waitForVisibilityOfElement(WebElement element) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * Get the element attribute 
	 * Eg: <a href="url">text</a>
	 * @param element
	 * @param attributeName
	 * @return
	 */
	public String getElementAttribute(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	/**
	 * Select the drop down based on index
	 * @param element
	 * @param index
	 */
	public void selectDropDownByIndex(WebElement element, int index) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * Select the dropdown based on text or value
	 * @param element
	 * @param text
	 */
	public void selectDropDownByText(WebElement element, String text) {
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * accept the alert popup
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * dismiss the alert pop up
	 */
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * swicthes the control from one tab to another by passing page title
	 * @param pageTitle
	 */
	public void switchToWindowOrTab(String pageTitle) {
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows) {
			String title=driver.switchTo().window(window).getTitle();
			if(title.contains(pageTitle)) {
				break;
			}
		}
	}
	
	/**
	 * pause the script for for five seconds
	 */
	public void waitOrPause() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	

}
