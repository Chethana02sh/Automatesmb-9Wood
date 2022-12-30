package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProjectOppPage extends Pages{
    WebDriver driver;
    @FindBy(id="Projects_listView_basicAction_LBL_ADD_RECORD")
    private WebElement addRecordBtn;
    @FindBy(xpath = "//span[@title='Select all records in this page']/input[@type='checkbox']")
    private  WebElement selectAllCheckbox;
    @FindAll({
    @FindBy(name="projectname"), @FindBy(xpath = "//span[@class='projectname']")})
    private WebElement projectNameField;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(css = "div[class*='record-header']")
    private WebElement recordHeader;
    @FindBy(id="Projects_listView_massAction_LBL_EDIT")
    private WebElement editBtn;
    @FindBy(id="projectname__chk")
    private WebElement projectNameCheckbox;
    @FindBy(xpath = "//a[contains(text(),'All')]")
    private WebElement projectOppListView;
    public ProjectOppPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public ProjectOppPage clickOnAddRecordBtn(){
        report.info("click on dadd Record button");
        actions.click(addRecordBtn);
        return this;
    }

    public ProjectOppPage enterProjectName(String projectName){
        report.info("Entering project name: "+projectName);
        actions.type(projectNameField, projectName);
        return this;
    }

    public ProjectOppPage clickOnSave(){
        report.info("click on save button");
        actions.click(saveBtn);
        return this;
    }

    public ProjectOppPage verifyProjectNameAndIconDisplayed(String expProjectName){
        report.info("verifying project name and Img header disaplayed");
        Assert.assertEquals(projectNameField.getText().trim(), expProjectName, "projectName is not matching");
        Assert.assertTrue(recordHeader.isDisplayed(), "header not displayed");
        return this;
    }

    public ProjectOppPage clickOnEditPencilBtn(){
        report.info("click on edit pencil btn");
        Assert.assertTrue(editBtn.isEnabled(), "edit button disabled");
        actions.click(editBtn);
        return this;
    }

    public ProjectOppPage clickOnMassEditCheckBox(){
        report.info("click on mass edit checkbox");
        actions.waitOrPause();
        Assert.assertTrue(selectAllCheckbox.isEnabled(), "checkbox is disanled");
        actions.click(selectAllCheckbox);
        return this;
    }

    public ProjectOppPage updateProjectName(String projectName){
        report.info("entering updated project name: "+projectName);
        actions.waitOrPause();
        actions.waitTillElementClickable(projectNameCheckbox);
        actions.click(projectNameCheckbox);
        actions.type(projectNameField, projectName);
        return this;
    }

    public ProjectOppPage clickOnProjectOppAll() {
        report.info("click on All link to get list view");
        actions.click(projectOppListView);
        return this;
    }
}
