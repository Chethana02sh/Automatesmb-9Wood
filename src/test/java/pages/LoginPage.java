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
}
