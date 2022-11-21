package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Pages{

	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(xpath="//span[contains(@title,'(admin)')]")
	private WebElement profileIcon;
	
	@FindBy(id="menubar_item_right_LBL_SIGN_OUT")
	private WebElement signOut;
	
	
	public HomePage clickOnProfile() {
		report.info("click on profile");
		actions.click(profileIcon);
		return this;
	}
	
	public HomePage clickOnSignout() {
		report.info("click on signout");
		actions.waitOrPause();
		actions.waitForVisibilityOfElement(signOut);
		actions.click(signOut);
		return this;
	}
	
	
	public Dashboard clickOnDashBoard() {
		
		return new Dashboard(driver);
	}
	
	
	
	

}
