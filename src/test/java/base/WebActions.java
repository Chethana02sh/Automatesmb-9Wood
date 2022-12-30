package base;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.primitives.Bytes;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.sikuli.script.FindFailed;
//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;


public class WebActions {
	
	private WebDriver driver;
	
	public WebActions(WebDriver driver) {
		this.driver=driver;
	}

	public void waitTillElementClickable(WebElement ele){
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void clickOnElementByIMG(String imgPath){
//		Screen screen=new Screen();
//		try {
//			screen.click(new Pattern(System.getProperty("user.dir")+imgPath));
//		} catch (FindFailed e) {
//			throw new RuntimeException(e);
//		}
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
		if(!element.getAttribute("value").isEmpty() || element.getAttribute("value")!=null ||element.getAttribute("value").length()!=0){
			element.clear();
		}
		element.sendKeys(text);
		
	}
	public void type(WebElement element, String text, Keys key) {
		element.sendKeys(text, key);

	}
	
	/**
	 * Perform click on element
	 * @param element
	 */
	public void click(WebElement element) {
		element.click();
	}

	public void waitAndClick(WebElement ele){
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
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

	/**
	 * Performs drag and drop where source element dragged to target element
	 * @param sourceElement
	 * @param targetElement
	 */
	public void dragAndDropElement(WebElement sourceElement, WebElement targetElement){
		Actions actions=new Actions(driver);
		actions.dragAndDrop(sourceElement, targetElement).build().perform();
	}

	/**
	 * performs scroll actions till where element is visible
	 * @param element
	 */
	public void scrollToElement(WebElement element){
		int y=element.getLocation().getY();
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,"+y+")", element);
	}


	public void waitForAllElement(List<WebElement> elements) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public WebElement getElement(String xpath){
		return driver.findElement(By.xpath(xpath));
	}

	public List<String> getAllDropDownOptions(WebElement element){
		Select select=new Select(element);
		return select.getOptions().stream().map(ele->ele.getText()).collect(Collectors.toList());
	}

	public void jsElementClick(WebElement element){

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}

	public WebElement clickOnElement(String xpath){
		WebElement ele= driver.findElement(By.xpath(xpath));
		ele.click();
		return ele;
	}

	public boolean isAlertPresent(){
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		Alert value = wait.until(ExpectedConditions.alertIsPresent());
		if(value!=null){
			return true;
		}
		return false;
	}


	public void mousehoverAndClick(WebElement ele) {
		Actions actions=new Actions(driver);
		actions.moveToElement(ele).click().build().perform();
	}

}
