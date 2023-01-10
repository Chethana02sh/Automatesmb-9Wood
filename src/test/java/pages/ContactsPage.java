package pages;

import org.checkerframework.checker.signature.qual.FieldDescriptor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ContactsPage extends Pages{
    WebDriver driver;
    public ContactsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id="createFilter")
    private WebElement createFilterBtn;
    @FindBy(name="viewname")
    private WebElement listName;
    @FindBy(xpath = "//label[text()='Choose columns and order (Max 15)']/following-sibling::div/descendant::input[@type='text']")
    private WebElement chooseColumnsAndOrder;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//tr[@class='listViewContentHeader']/descendant::th/a")
    private List<WebElement> headerList;
    @FindBy(xpath = "//input[@class='listViewEntriesMainCheckBox' and @type='checkbox']")
    private WebElement mainCheckBox;
    @FindBy(id = "selectAllMsgDiv")
    private WebElement selectAllReportText;
    @FindBy(xpath = "//ul[@class='listmenu']/descendant::a[contains(text(),'Delete')]")
    private WebElement delete;
    @FindBy(xpath = "//ul[@class='listmenu']/descendant::a[contains(text(),'Duplicate')]")
    private WebElement duplicate;
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement okBtn;
    @FindBy(xpath = "//label[text()='Choose columns and order (Max 15)']/following-sibling::div/descendant::div[text()]")
    private List<WebElement> listOfColumnsInChooseColumns;
    @FindBy(xpath = "//ul[@class='listmenu']/descendant::a[contains(text(),'Edit')]")
    private WebElement edit;
    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public void clickOnCreateFilterBtn(){
        report.info("click on create filter");
        actions.click(createFilterBtn);
    }

    public void enterListNameAndColumnList(String listname, List<String> columnNames){
        report.info("Enter listname: "+listname);
        actions.waitForVisibilityOfElement(listName);
        actions.type(listName, listname);
        report.info("enter column list: "+columnNames);
        if(columnNames!=null) {
            for (String columnName : columnNames) {
                actions.click(chooseColumnsAndOrder);
                driver.switchTo().activeElement().sendKeys(columnName, Keys.TAB);
            }
        }
    }

    public void clickOnSaveBtn(){
        report.info("click on save button");
        actions.click(saveBtn);
    }

    public void verifyColumnNamesInTableHeader(List<String> columnNames){
        report.info("verifying column names: "+columnNames.toString());
        if(headerList.size()== columnNames.size()){
            for(int i=0;i< headerList.size();i++){
                boolean flag = headerList.get(i).getText().trim().equals(columnNames.get(i));
                Assert.assertTrue(flag, "Expected: "+headerList.get(i).getText()+" actual: "+columnNames.get(i));
            }
        }else{
            Assert.fail("list is not matching");
        }
    }

    public String getTotalRecordCount(){
        report.info("click on main check box");
        actions.waitOrPause();
        actions.click(mainCheckBox);
        actions.waitOrPause();
        String text= selectAllReportText.getText().trim();
        report.info("Get all the count: "+text);
        return  text;
    }

    public void clickOnFilterArrowDown(String filtername){
        report.info("click on filter: "+filtername);
        WebElement element = driver
                .findElement(By.xpath("//a[text()='"+filtername+"']/following-sibling::div[@class='pull-right']/descendant::span[last()]"));
        actions.click(element);
    }

    public void clickOnDelete(){
        report.info("click on delete button");
        actions.click(delete);
        report.info("click on ok button");
        actions.click(okBtn);
    }

    public void clickOnDuplicate(){
        report.info("click on duplicate");
        actions.click(duplicate);
    }

    public List<String> getAllColumnNamesInColumnsList(){
        return listOfColumnsInChooseColumns.stream()
                .map(ele->ele.getText().trim())
                .collect(Collectors.toList());
    }

    public void clickOnEdit() {
        actions.click(edit);

    }
}
