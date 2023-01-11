package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.util.List;

public class CalendarPage extends Pages{
    WebDriver driver;
    public CalendarPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(xpath = "//div[@data-original-title='Click here to manage List columns']")
    private WebElement manageListColumns;
    @FindBy(xpath = "//h5[text()='Selected Fields']/following-sibling::div/descendant::li")
    private List<WebElement> selectedFieldsList;
    @FindBy(xpath = "//input[@placeholder='Search Fields']")
    private WebElement searchFields;
    @FindBy(xpath = "(//button[@aria-label='Close'])[last()]")
    private WebElement cancelBtn;

    @FindBy(id = "Calendar_listView_basicAction_LBL_ADD_TASK")
    private WebElement addTaskBtn;
    @FindAll({
    @FindBy(xpath = "//h4[text()='Task Details']/following-sibling::table/descendant::tr[3]/descendant::span[text()]"),
    @FindBy(xpath = "//h4[text()='Event Details']/following-sibling::table/descendant::tr[3]/descendant::span[text()]")})
    private WebElement moduleDropDown;
    @FindBy(xpath = "//i[contains(@id,'_editView_fieldName_parent_id_create')]")
    private WebElement createPlusBtn;
    @FindAll({@FindBy(id="account_id_display"),
    @FindBy(id="Accounts_editView_fieldName_accountname")})
    private WebElement organizationName;
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement okBtn;
    @FindBy(id="Contacts_editView_fieldName_firstname")
    private WebElement firstName;
    @FindBy(id="Contacts_editView_fieldName_lastname")
    private WebElement lastname;
    @FindBy(xpath="//i[contains(@id,'_editView_fieldName_contact_id_create')]")
    private WebElement contactPlusBtn;
    @FindAll({
    @FindBy(id = "Calendar_editView_fieldName_subject"), @FindBy(name="subject")})
    private WebElement subjectField;
    @FindBy(xpath = "//td[contains(text(),'Research Code')]/following-sibling::td/descendant::span")
    private WebElement researchDropdown;

    @FindBy(xpath = "//button[@name='saveButton']")
    private WebElement popupSaveBtn;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//span[@class='subject']")
    private WebElement subjectText;
    @FindBy(xpath="//span[text()='Research Code']/ancestor::td/following-sibling::td[2]/descendant::span")
    private WebElement researchCodeText;
    @FindBy(id="Calendar_detailView_basicAction_LBL_EDIT")
    private WebElement editBtn;
    @FindBy(xpath="//td[contains(@id,'detailView_fieldValue_subject')]")
    private WebElement detailViewSubjectName;
    @FindBy(xpath = "//td[contains(@id,'detailView_fieldValue_parent_id')]/descendant::a")
    private WebElement detailedViewRelatedTo;
    @FindBy(xpath = "//td[contains(@id,'detailView_fieldValue_contact_id')]/descendant::a")
    private WebElement detailedViewContactName;
    @FindBy(id="Calendar_listView_basicAction_LBL_ADD_EVENT")
    private WebElement addEventButton;
    @FindBy(xpath = "//td[contains(text(),'Status')]/following-sibling::td[1]/descendant::span")
    private WebElement statusDropDown;
    @FindBy(xpath = "//td[@id='Events_detailView_fieldValue_eventstatus']/span")
    private WebElement detailViewStatus;
    @FindBy(xpath = "//a[text()='All']")
    private WebElement all;
    @FindBy(name = "date_start")
    private WebElement startDate;
    @FindBy(xpath = "//div[@class='date-picker-wrapper'][2]/descendant::input[@value='Close']")
    private WebElement close;
    @FindBy(xpath = "//span[text()='Search']")
    private WebElement search;
    @FindBy(xpath = "//tr[@class='listViewContentHeader']/following-sibling::tr/th[last()]")
    private WebElement assignedTo;
    @FindBy(xpath = "//input[@class='listViewEntriesMainCheckBox' and @type='checkbox']")
    private WebElement mainCheckBox;
    @FindBy(id="selectAllMsgDiv")
    private WebElement selectedTextMsg;

    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public CalendarPage clickOnManageList(){
        report.info("click on manage list columns");
        actions.click(manageListColumns);
        return this;
    }

    public CalendarPage verifyFieldInSelectedListOrAvailableList(String fieldName){
        report.info("verify field in selected or available : "+fieldName);
        boolean isFieldPresent=selectedFieldsList.stream()
                .map(ele-> ele.getText())
                .anyMatch(txt-> txt.equalsIgnoreCase(fieldName));

        if(isFieldPresent) {
            Assert.assertTrue(isFieldPresent, fieldName + " is not present in selected list");
        }else{
            actions.type(searchFields, fieldName);
            //add assertion in available list
        }
        actions.waitOrPause();
        actions.mousehoverAndClick(cancelBtn);
        return this;
    }

    String subjectName;
    String researchName;
    public CalendarPage createNewTaskWithRecord(String subject, String researchName){
        this.subjectName=subject;
        this.researchName=researchName;
        report.info("click on add task");
        actions.click(addTaskBtn);
        report.info("enter subject: "+subject);
        actions.type(subjectField,subject);
        actions.scrollToElement(researchDropdown);
        report.info("select research: "+researchName);
        actions.waitAndClick(researchDropdown);
        driver.switchTo().activeElement().sendKeys(researchName, Keys.ENTER);
        report.info("click on save");
        actions.click(saveBtn);
        return  this;
    }

    public CalendarPage clickOnAddTask(){
        report.info("click on add task");
        actions.click(addTaskBtn);
        return this;
    }

    public CalendarPage enterAllMandatoryFields(String subject, String module, String orgName, String firstname, String lastName){
        actions.type(subjectField, subject);
        try {
            actions.click(moduleDropDown);
            driver.switchTo().activeElement().sendKeys(module, Keys.ENTER);
            enterSelectedModuleData(orgName);
            enterContactMandatoryData(orgName, firstname, lastName);
        }catch (Exception e){
            System.out.println("No field or module and contact creation");
        }
        return  this;
    }

    private CalendarPage enterContactMandatoryData(String orgName, String firstname, String lastName) {
        actions.waitOrPause();
        actions.click(contactPlusBtn);
        actions.type(organizationName, orgName);
        actions.waitOrPause();
//        WebElement ele = driver.findElement(By.xpath("//ul/li/div[text()='" + orgName + "']"));
//        actions.mousehoverAndClick(ele);
//        actions.click(okBtn);
        try {
            actions.pressArrowDownKey();
            actions.waitOrPause();
            actions.click(okBtn);
        } catch (AWTException e) {
            System.out.println("popup not displayed");
        }

       actions.type(firstName, firstname);
        actions.type(lastname, lastName);
        actions.click(popupSaveBtn);
        return  this;
    }

    private CalendarPage enterSelectedModuleData(String orgName) {
        actions.click(createPlusBtn);
        actions.type(organizationName, orgName);
        actions.click(popupSaveBtn);
        return this;
    }

    public CalendarPage clickOnSaveButton(){
        actions.click(saveBtn);
        return  this;
    }

    public CalendarPage verifySubjectAndResearchCode(){
        report.info("verifing subject and research code present in view page");
        Assert.assertEquals(subjectText.getText().trim(), subjectName);
        Assert.assertEquals(researchCodeText.getText().trim(), researchName);
        return this;
    }

    public CalendarPage verifyCreatedTaskAndRecordInList(String subjectName, String researchCode){
        actions.click(subjectField);
        driver.switchTo().activeElement().sendKeys(subjectName, Keys.ENTER);
        actions.waitOrPause();
      WebElement researchTextInList =  driver.findElement(By.xpath("//a[text()='"+subjectName+"']/ancestor::td[@data-name='subject']/following-sibling::td[@title='"+researchCode+"']/descendant::span[text()]"));
        Assert.assertEquals(researchCode, researchTextInList.getText().trim());
        return this;
    }

    public CalendarPage editExistingTaskInList(String updateResearchCode){
        WebElement ele=driver.findElement(By.xpath("//td[@data-name='subject']/descendant::a"));
        subjectName=ele.getText();
        report.info("clicked on task: "+subjectName);
        ele.click();
        report.info("click on edit button");
        actions.click(editBtn);
        actions.scrollToElement(researchDropdown);
        report.info("click on reseacrh code");
        actions.waitAndClick(researchDropdown);
        driver.switchTo().activeElement().sendKeys(updateResearchCode, Keys.ENTER);
        researchName=updateResearchCode;
        report.info("click on save");
        actions.click(saveBtn);
        return this;
    }

    public CalendarPage verifyEditedTaskInList(){
            actions.click(subjectField);
            driver.switchTo().activeElement().sendKeys(subjectName, Keys.ENTER);
            actions.waitOrPause();
            WebElement researchTextInList =  driver.findElement(By.xpath("//a[text()='"+subjectName+"']/ancestor::td[@data-name='subject']/following-sibling::td[@title='"+researchName+"']/descendant::span[text()]"));
            Assert.assertEquals(researchName, researchTextInList.getText().trim());
            return this;
        }

        public CalendarPage verifySubjectName(String subjectName){
        report.info("Verify subjcet name"+subjectName);
        Assert.assertEquals(detailViewSubjectName.getText().trim(), subjectName);
        return this;
        }
        public CalendarPage verifyRelatedTo(String relatedTo){
        report.info("Verify related to :"+relatedTo);
        Assert.assertEquals(detailedViewRelatedTo.getText().trim(), relatedTo);
        return  this;
        }
        public CalendarPage verifyContactName(String contactname){
        report.info("verify contact name: "+contactname);
        Assert.assertTrue(detailedViewContactName.getText().trim().startsWith(contactname));
        return  this;
        }

        public CalendarPage verifySavedCalenderTask(String subject, String relatedTo, String contact){
        verifySubjectName(subject);
        if(relatedTo!=null && contact!=null) {
            verifyContactName(contact);
            verifyRelatedTo(relatedTo);
        }
        return this;
        }



        public CalendarPage clickOnAddEventBtn(){
        report.info("click add  event button");
        actions.click(addEventButton);
        return this;
        }

        public CalendarPage selectStatus(String status){
        report.info("Select status: "+status);
        actions.click(statusDropDown);
        driver.switchTo().activeElement().sendKeys(status, Keys.ENTER);
        return  this;
        }

        public CalendarPage verifyStatusTxt(String status){
        report.info("verify statys text");
        Assert.assertEquals(detailViewStatus.getText().trim(), status);
        return  this;
        }

        public void clickOnAll(){
        report.info("click on all");
        actions.click(all);
        actions.waitOrPause();
        }

        public void selectStartDate(String startdate, String endDate, String month, String year){
        String xpath="//div[@class='date-picker-wrapper'][2]/descendant::th[text()='"+month+" "+year+"']/ancestor::table/tbody/tr/td/div[text()='"+startdate+"']";
        String xpath2="//div[@class='date-picker-wrapper'][2]/descendant::th[text()='"+month+" "+year+"']/ancestor::table/tbody/tr/td/div[text()='"+endDate+"']";
        report.info("selecting start date"+startdate+"/"+month+"/"+year);
        actions.click(startDate);
            WebElement start = driver.findElement(By
                    .xpath(xpath));
            start.click();
            report.info("selecting end date"+endDate+"/"+month+"/"+year);
            WebElement end = driver.findElement(By
                    .xpath(xpath2));
            end.click();
            actions.click(close);
        }

        public void clickOnSearch(){
        report.info("click on search");
        actions.click(search);
        actions.waitOrPause();
        }

    public void enterAssignedTo(String assignedto) {
       report.info("enter assigned to: "+assignedTo);
       actions.click(assignedTo);
       driver.switchTo().activeElement().sendKeys(assignedto, Keys.TAB);
    }

    public void clickOnSelectAllCheckBox(){
        report.info("click on all select checkbox");
        actions.click(mainCheckBox);
        actions.waitOrPause();
    }

    public void verifySelectedMessageCount(){
        report.info("verifying selected text greater than 0");
        String text = selectedTextMsg.getText().trim();
        int count=actions.getIntFromString(text);
        Assert.assertTrue(count>0);
    }
}

