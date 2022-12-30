package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
    @FindBy(id = "Calendar_editView_fieldName_subject"), @FindBy(name="subject")})
    private WebElement subjectField;
    @FindBy(xpath = "//td[contains(text(),'Research Code')]/following-sibling::td/descendant::span")
    private WebElement researchDropdown;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//span[@class='subject']")
    private WebElement subjectText;
    @FindBy(xpath="//span[text()='Research Code']/ancestor::td/following-sibling::td[2]/descendant::span")
    private WebElement researchCodeText;
    @FindBy(id="Calendar_detailView_basicAction_LBL_EDIT")
    private WebElement editBtn;

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
    }

