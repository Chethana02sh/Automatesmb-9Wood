package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class HomePage extends Pages{

	private WebDriver driver;
	private String json;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(xpath="//span[contains(@title,'(admin)')]")
	private WebElement profileIcon;
	
	@FindBy(id="menubar_item_right_LBL_SIGN_OUT")
	private WebElement signOut;
	@FindBy(xpath="//div[@class='dashboardTitle']")
	private List<WebElement> dashboardWidgets;

	@FindBy(xpath="//a[@title='Menu']")
	private  WebElement menu;

	@FindBy(xpath="//a[@title='Campaigns']")
	private WebElement campaigns;

	@FindBy(xpath = "//a[@title='Leads']")
	private WebElement leads;

	@FindBy(xpath = "//a[@title='Calendar']")
	private WebElement calendar;

	@FindBy(xpath = "//a[@title='Contacts']")
	private WebElement contacts;

	@FindBy(xpath = "//a[@title='Organizations']")
	private WebElement organizations;

	@FindBy(xpath = "//a[@title='Opportunities']")
	private	WebElement opportunities;

	@FindBy(xpath = "//a[@title='Documents']")
	private WebElement documents;

	@FindBy(xpath = "//a[@title='Stakeholders']")
	private WebElement stakeholders;

	@FindBy(xpath="//a[@title='Opportunities']")
	private WebElement Opportunities;

	@FindBy(xpath="//a[@title='Reports' and text()]")
	private WebElement reports;
	@FindBy(xpath="//b[contains(text(),'Settings')]")
	private WebElement settings;
	@FindBy(xpath = "//a[@title='Project Opps']")
	private WebElement projectOpps;

	@Override
	public HomePage navigateToMetaInfo(String url) {
		driver.navigate().to(url);
		json=driver.findElement(By.tagName("body")).getText();
		//System.out.println(text);
		driver.navigate().back();
		return this;
	}

	@Override
	public Pages convertJsonToPojo() {
		return null;
	}

//	public HomePage navigateToModule(String moduleName){
//		driver.navigate().to(String.format(IEndPoints.MODULE_URL, moduleName));
//		return this;
//	}


	public HomePage clickOnModuleInMenu(String moduleName){
		actions.click(driver.findElement(By.xpath("//a[@title='"+moduleName+"']")));
		return this;
	}

	public HomePage clickOnMenu(){
		report.info("click on menu");
		actions.waitOrPause();
		actions.click(menu);
		return this;
	}

	public HomePage verifyCampaignsIsPresent(){
		report.info("verify campaigns");
		Assert.assertEquals(campaigns.getText().trim(), (String)getJsonData(json, "[0]"));
		return this;
	}

	public HomePage verifyLeadIsPresent(){
		Assert.assertEquals(leads.getText().trim(), (String) getJsonData(json, "[2]"));
		return this;
	}

	public HomePage verifyCalendarIsPresent(){
		Assert.assertEquals(calendar.getText().trim(), (String)getJsonData(json, "[1]"));
		return this;
	}

	public HomePage verifyContactIsPresent(){
		Assert.assertEquals(contacts.getText().trim(), (String)getJsonData(json,"[4]"));
		return this;

	}

	public OrganizationPage clickOnOrganization(){
		report.info("click on organization");
		actions.click(organizations);
		return new OrganizationPage(driver);
	}
	public HomePage verifyOrganizationsIsPresent()
	{
		Assert.assertTrue(organizations.isDisplayed());
		return this;
	}

	public HomePage verifyOpportunitiesIsPresent()
	{
		Assert.assertTrue(opportunities.isDisplayed());
		return this;
	}

	public HomePage verifyDocumentsIsPresent()
	{
	Assert.assertEquals(documents.getText().trim(),(String) getJsonData(json,"[6]"));
	return this;
	}

	public HomePage verifyStakeholders()
	{
		Assert.assertEquals(stakeholders.getText().trim(),(String) getJsonData(json,"[12]"));
		return this;
	}

	public HomePage verifyModuleNames(String moduleName, String jsonPath){
		String module=(String)getJsonData(json,jsonPath);
		Assert.assertEquals(module, moduleName, "module name not matching");
		return this;
	}

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

	public HomePage verifyDashBoardWidgets(){
		report.info("verifying all the visible widgets in dashboard");
		actions.waitOrPause();
		actions.waitForAllElement(dashboardWidgets);
		List<Boolean> isWidgetVisible=dashboardWidgets.stream().map(webElement -> webElement.isDisplayed()).collect(Collectors.toList());
		Assert.assertTrue(isWidgetVisible.stream().allMatch(aBoolean -> aBoolean==true));
		return this;
	}
	
	
	public Dashboard clickOnDashBoard() {
		
		return new Dashboard(driver);
	}

	public OpportunityPage clickOnOppertunity(){
		actions.click(opportunities);
		return new OpportunityPage(driver);
	}
	
	public ReportPage clickOnReport(){
		actions.click(reports);
		return new ReportPage(driver);
	}

	public SettingsPage clickOnSettings(){
		actions.click(settings);
		return new SettingsPage(driver);
	}

	public ProjectOppPage clickOnProjectOpp(){
		report.info("click on project opp in menu");
		actions.click(projectOpps);
		return new ProjectOppPage(driver);
	}

	public CalendarPage clickOnCalendar(){
		report.info("click on calendar");
		actions.click(calendar);
		return new CalendarPage(driver);
	}

}
