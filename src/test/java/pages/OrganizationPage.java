package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class OrganizationPage extends Pages{

    WebDriver driver;
    public OrganizationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id="Accounts_listView_basicAction_LBL_ADD_RECORD")
    private WebElement addOrganizationBtn;
    @FindAll({
    @FindBy(name="accountname"), @FindBy(xpath = "//span[@class='accountname']")})
    private WebElement organizationNameField;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//div[@class='recordBasicInfo']")
    private WebElement organizationHeader;
    @FindBy(xpath = "//strong[text()='Updates']")
    private WebElement updates;
    @FindBy(xpath = "//ul[@class='updates_timeline']/li")
    private List<WebElement> listOfUpdates;
    @FindBy(xpath = "//ul[@class='updates_timeline']/li/descendant::h5[text()]")
    private List<WebElement> listUpdatesData;
    @FindBy(id="Accounts_detailView_basicAction_LBL_EDIT")
    private WebElement editBtn;
    @FindBy(xpath = "//span[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//h4[text()='Activities']/ancestor::div[@class='summaryWidgetContainer']/descendant::a[text() and @Title]")
    private List<WebElement> ActivityNameList;
    @FindBy(name="website")
    private WebElement wesiteField;
    @FindAll({@FindBy(xpath = "//button[contains(text(),'Import')]"),
    @FindBy(id="Accounts_basicAction_LBL_IMPORT"), @FindBy(id="importButton")})
    private WebElement importBtn;
    @FindAll({
   @FindBy(xpath = "//span[contains(text(),'Select from My Computer')]")//, @FindBy(xpath="//input[@name='import_file']")
    })
    private WebElement selectFromComputerBtn;
    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextBtn;
    @FindBy(xpath = "//table[@class='table table-bordered']/descendant::tr[2]/descendant::td[last()-1]")
    private WebElement organizationCRMField;
    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public OrganizationPage clickOnAddOrgganizationBtn(){
        report.info("click on add organization button");
        actions.click(addOrganizationBtn);
        return this;
    }

    public OrganizationPage enterOrganizationName(String orgName){
        report.info("Organization Name: "+orgName);
        actions.type(organizationNameField, orgName);
        return this;
    }

    public OrganizationPage clickOnSave(){
        report.info("click on save button");
        actions.click(saveBtn);
        actions.waitOrPause();
        return this;
    }

    public OrganizationPage verifyOrganizationNameAndHeaderDisplayed(String orgName){
        report.info("verifing organization name and header");
        Assert.assertTrue(organizationHeader.isDisplayed(), "Organization header is not displayed");
        Assert.assertEquals(organizationNameField.getText().trim(), orgName);
        return this;
    }

    public OrganizationPage clickOnUpdates(){
        report.info("click on updates");
        actions.click(updates);
        return this;
    }

    public OrganizationPage verifyOrganizationUpdates(){
        report.info("verify the update list were displaying");
        Assert.assertTrue(listOfUpdates.size()>0);
        listOfUpdates.stream()
                .forEach(ele->{
                    Assert.assertTrue(ele.isDisplayed(),"update list were not displayed");
                });
        listUpdatesData.parallelStream()
                .forEach(ele->{
                    report.info("updates "+ele.getText());
                });
        return this;
    }

    public OrganizationPage clickOnEditBtn(){
        report.info("click on edit button");
        actions.click(editBtn);
        return this;
    }

    public OrganizationPage enterWebsite(String wesiteName){
        report.info("enter website name:"+wesiteName);
        actions.type(wesiteField, wesiteName);
        return this;
    }

    public OrganizationPage clickOnImportBtn(){
        actions.waitOrPause();
        report.info("click on import button");
        actions.click(importBtn);
        return this;
    }

    public OrganizationPage selectCsvFileFromComputer(String path){
        actions.waitOrPause();
        report.info("select csv: "+path);
       // actions.waitAndClick(selectFromComputerBtn);
       // actions.type(selectFromComputerBtn,path, Keys.ENTER);
        //actions.jsElementClick(selectFromComputerBtn);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.getElementById('import_file').click();");
        System.out.println("clicked on select file");
        actions.waitOrPause();
        StringSelection selection=new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        try {
            Robot rc=new Robot();
            rc.keyPress(KeyEvent.VK_V);
            rc.keyRelease(KeyEvent.VK_V);
            rc.keyPress(KeyEvent.VK_ENTER);
            rc.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        clickOnNextBtn();
        return this;
    }
    public OrganizationPage clickOnNextBtn(){
        report.info("click on next Btn");
        actions.click(nextBtn);
        return this;
    }

    public OrganizationPage selectOrganizationfieldMapping(String fieldName){
        report.info("select Organization field mapping");
        Actions act=new Actions(driver);
        act.click(organizationCRMField).sendKeys(fieldName, Keys.ENTER).perform();
        return this;
    }

    public OrganizationPage clickOnImport(){
        report.info("click on import");
        actions.click(importBtn);
        return this;
    }

    public OrganizationPage searchForOrganization(String name){
        report.info("search for organization");
        actions.click(organizationNameField);
        driver.switchTo().activeElement().sendKeys(name);
        report.info("click on search button");
        actions.click(searchBtn);
        report.info("click on searched organization");
        actions.waitOrPause();
        driver.findElement(By.xpath("//a[text()='"+name+"']")).click();
        return  this;
    }
    public OrganizationPage verifyActivity(String taskName, String eventName){
        ActivityNameList.stream().forEach(ele-> System.out.println(ele.getText()));
        Assert.assertEquals(ActivityNameList.get(0).getText().trim(),taskName);
        Assert.assertEquals(ActivityNameList.get(1).getText().trim(), eventName);
        return this;
    }

}
