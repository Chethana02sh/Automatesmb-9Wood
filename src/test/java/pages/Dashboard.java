package pages;

import org.openqa.selenium.WebDriver;

public class Dashboard extends Pages  {
	private WebDriver driver;
	public Dashboard(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@Override
	public Dashboard navigateToMetaInfo(String url) {
		driver.navigate().to(url);
		return this;
	}

	@Override
	public Pages convertJsonToPojo() {
		return null;
	}

	public void dashboarfInfo() {}
	

}
