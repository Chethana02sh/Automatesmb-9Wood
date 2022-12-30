package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class OpportunityPage extends  Pages{
    private WebDriver driver;
    public OpportunityPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id="Potentials_listView_basicAction_LBL_ADD_RECORD")
    private WebElement addOppertunityBtn;

    @FindBy(xpath="//div[@class='popupcontents']")
    private WebElement popupContent;

    @FindBy(name="potentialname")
    private WebElement oppertunityName;

    @FindBy(xpath = "//td[contains(text(),'Sales Stage')]/following-sibling::td/descendant::a")
    private WebElement salesStage;

    @FindBy(xpath="//td[contains(text(),'Sales Stage')]/following-sibling::td/descendant::select[@data-fieldname='sales_stage']")
    private WebElement salesStageDropDown;

    @FindBy(xpath="//input[@data-fieldname='campaignid']/../span[@title='Select']")
    private WebElement campaginSourceSearchBtn;

    @FindBy(xpath="//input[@data-fieldname='campaignid']/../span[@title='Create']")
    private WebElement campaginSourceCreateBtn;

    @FindBy(name="campaignname")
    private WebElement campaginName;

    @FindBy(xpath = "//tr[@class='listViewContentHeader']/following-sibling::tr/descendant::th[4]")
    private WebElement assignedTo;

    @FindBy(xpath = "//span[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//td[@data-name='assigned_user_id']/descendant::span[text() and @class='value']")
    private List<WebElement> assignedToList;
    @FindBy(xpath = "//td[contains(text(),'Assigned To')]/following-sibling::td[1]/descendant::span")
    private WebElement assignedToDropdown;
    @FindBy(xpath="//button[text()='Save' or @name='saveButton']")
    private WebElement saveBtn;

    @FindBy(xpath="//td[contains(text(),'Contact Name')]/following-sibling::td/descendant::span[@title='Create']")
    private WebElement contactname;

    @FindBy(name="lastname")
    private WebElement lastname;

    @FindBy(name="email")
    private WebElement primaryMail;

    @FindBy(name="phone")
    private WebElement phoneNumber;

    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public OpportunityPage enterOppertunityName(String name){
        actions.type(oppertunityName, name);
        return this;
    }

    public OpportunityPage enterSalesStage(String stage){
        actions.click(salesStage);
        actions.selectDropDownByText(salesStageDropDown, stage);
        return this;
    }

    public OpportunityPage clickOnAddContactName(){
        actions.click(contactname);
        return  this;
    }

    public OpportunityPage enterContactInfo(String lastName, String email, String phonenumber){
        actions.waitOrPause();
        actions.type(lastname, lastName);
        actions.type(primaryMail, email);
        actions.type(phoneNumber,phonenumber,Keys.ENTER );

//        actions.waitForVisibilityOfElement(saveBtn);
//        actions.waitOrPause();
//        actions.click(saveBtn);
        return this;
    }

    public OpportunityPage clickOnCampaginSourceCreateBtn(){
        actions.waitOrPause();
        actions.scrollToElement(campaginSourceCreateBtn);
        actions.click(campaginSourceCreateBtn);
        return this;
    }

    public OpportunityPage enterCampaginName(String name){
        actions.type(campaginName, name, Keys.ENTER);
        return this;
    }

    public OpportunityPage clickOnSaveBtn(){
        actions.click(saveBtn);
        return this;
    }

    public OpportunityPage clickOnAddOppertunityBtn(){
        actions.click(addOppertunityBtn);
        return this;
    }

    public OpportunityPage clickOnPhoneNumberAndVerifyPopUp(String phonenumber){
        actions.clickOnElement("//div[@title='Office Phone']/a[text()='"+phonenumber+"']");

        Assert.assertTrue(popupContent.isDisplayed(), "alert not displayed");
        return  this;
    }

    public OpportunityPage verifyAssignedToInList(String text){
        report.info("click on assigned To text field");
        actions.click(assignedTo);
        report.info("Enter the assigned name: "+text);
        driver.switchTo().activeElement().sendKeys(text, Keys.TAB);
        report.info("click on search button");
        actions.jsElementClick(searchBtn);
        actions.waitOrPause();
        report.info("verifing assigned To list");
        Assert.assertTrue(!assignedToList.isEmpty(), "Assigned to list is empty");
        assignedToList.stream().filter(ele->ele.getText().length()!=0)
                .forEach(ele->{
                    Assert.assertEquals(ele.getText().trim(), text, "assigned to name is not matching");
                });
        return this;
    }

    public OpportunityPage selectAssignedTo(String text){
        report.info("click on assigned to dropdown");
        actions.click(assignedToDropdown);
        report.info("enter assigned to name : "+text);
        driver.switchTo().activeElement().sendKeys(text, Keys.ENTER);
        return  this;
    }

}
