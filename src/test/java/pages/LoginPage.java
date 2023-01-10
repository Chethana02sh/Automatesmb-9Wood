package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Pages{
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	
	@FindBy(id="username")
	private WebElement username;
	@FindBy(xpath = "//span[@title='Ballistix Automates Admin(admin)']")
	private WebElement adminIcon;
	@FindBy(id = "menubar_item_right_LBL_SIGN_OUT")
	private WebElement signout;
	@FindBy(xpath = "//button[text()='Get Started']")
	private WebElement getStarted;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//button[text()='Sign in']")
	private WebElement signIn;

	@Override
	public LoginPage

	navigateToMetaInfo(String url) {
		driver.navigate().to(url);
		return this;
	}

	@Override
	public Pages convertJsonToPojo() {
		return null;
	}

	public void login(String userName, String password) {
		report.info("logged in to application");
		actions.type(username, userName);
		report.info("username: "+userName);
		actions.type(this.password, password);
		report.info("password: "+password);
		actions.click(signIn);
		report.info("click on sign in");
	}

	public void logout(){
		report.info("log out from application");
		actions.click(adminIcon);
		actions.jsElementClick(signout);
	}

	public void clickOnGetStarted() {
		report.info("click on get started button");
		try {
			actions.click(getStarted);
		}catch (Exception e){
			System.out.println("get started button not displayed");
		}
	}
}
