package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Pages{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//button[text()='Sign in']")
	private WebElement signIn;
	
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
