package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class SettingsPage extends Pages {
    WebDriver driver;

    public SettingsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[text()='User Management']")
    private WebElement userManagement;
    @FindBy(xpath = "//a[text()='Users']")
    private WebElement users;
    @FindBy(id = "Users_listView_basicAction_LBL_ADD_RECORD")
    private WebElement addUserBtn;
    @FindBy(id = "Users_editView_fieldName_user_name")
    private WebElement userName;
    @FindBy(id = "Users_editView_fieldName_email1")
    private WebElement email;
    @FindBy(id = "Users_editView_fieldName_last_name")
    private WebElement lastName;
    @FindBy(id = "Users_editView_fieldName_user_password")
    private WebElement password;
    @FindBy(id = "Users_editView_fieldName_confirm_password")
    private WebElement confirmPassword;
    @FindBy(xpath = "//span[text()='Research Dept']")
    private WebElement role;
    @FindBy(name = "roleid")
    private WebElement roleDropDown;
    @FindBy(id = "Users_editView_fieldName_is_admin")
    private WebElement adminCheckbox;
    @FindBy(name = "imagename[]")
    private WebElement uploadImage;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//button[text()='Active Users']")
    private WebElement activeUsersBtn;
    @FindBy(name = "user_name")
    private WebElement userNameFilter;
    @FindBy(xpath = "//span[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//td[@id='Users_detailView_fieldValue_user_name']/span")
    private WebElement createdUsername;

    @FindBy(xpath = "//span[text()='Module Management']")
    private WebElement moduleManagement;
    @FindBy(xpath = "//a[text()='Module Layouts & Fields']")
    private WebElement moduleLayoutsAndFields;
    @FindBy(xpath = "//label[text()='Select Module']/following-sibling::div/descendant::span")
    private WebElement selectModuleDropdown;
    @FindBy(xpath = "//strong[text()='Custom Information']/ancestor::div[@id='block_86']/descendant::span[@class='fieldLabel']")
    private List<WebElement> customInformationFieldList;
    @FindBy(id = "Users_detailView_fieldValue_is_admin")
    private WebElement adminText;
    @FindBy(xpath = "(//ul[@role='listbox'])[last()]/li")
    private List<WebElement> selectModuleSuggestions;
    @FindBy(xpath = "//span[text()='Configuration']")
    private WebElement configuration;
    @FindBy(xpath = "//a[text()='Picklist Field Values']")
    private WebElement picklistFieldValues;


    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public SettingsPage clickOnUserManagement() {
        actions.click(userManagement);
        return this;
    }

    public SettingsPage clickOnUser() {
        actions.click(users);
        return this;
    }

    public SettingsPage clickOnAddUser() {
        actions.click(addUserBtn);
        return this;
    }

    public SettingsPage enterUsersMandatoryInfo(String username, String emailAddress, String lastname, String password1, String confirmpassword,
                                                String rolename, boolean isAdmin, String imagePath) {
        actions.type(userName, username);
        actions.type(email, emailAddress);
        actions.type(lastName, lastname);
        actions.type(password, password1);
        actions.type(confirmPassword, confirmpassword);
        actions.click(role);
        actions.selectDropDownByText(roleDropDown, rolename);
        if (isAdmin == true) {
            actions.click(adminCheckbox);
        }
        actions.scrollToElement(uploadImage);
        actions.type(uploadImage, imagePath);
//        Actions action=new Actions(driver);
//        action.click(uploadImage).sendKeys(imagePath, Keys.ENTER).build().perform();
        return this;
    }

    public SettingsPage clickOnSave() {
        actions.click(saveBtn);
        return this;
    }

    public SettingsPage clickOnActiveUsers() {
        actions.click(activeUsersBtn);
        return this;
    }

    public SettingsPage verifyUserInActiveUserList(String expectedUsername, String expectedAdminText) {
        String actualUsername = createdUsername.getText().trim();
        String actualAdminText = adminText.getText().trim();
        System.out.println(actualAdminText);
        Assert.assertEquals(actualAdminText, expectedAdminText);
        clickOnUser();
        actions.waitOrPause();
        clickOnActiveUsers();
        actions.type(userNameFilter, expectedUsername);
        actions.click(searchBtn);
        Assert.assertEquals(actualUsername, expectedUsername);

        return this;
    }


    public SettingsPage clickOnModuleLayoutAndFields() {
        report.info("click on module info");
        actions.click(moduleManagement);
        report.info("click on module layouts and field");
        actions.click(moduleLayoutsAndFields);
        return this;
    }

    public SettingsPage selectModule(String moduleName) {
        report.info("select module from dropdown: " + moduleName);
        actions.click(selectModuleDropdown);
        driver.switchTo().activeElement().sendKeys(moduleName);
        selectModuleSuggestions.parallelStream()
                .forEach(suggestion -> {
                    if (suggestion.getText().equals(moduleName)) {
                        actions.click(suggestion);
                    }
                });
        return this;
    }

    public SettingsPage verifyFieldIsPresentInCustomInfo(String fieldName) {
        boolean flag = false;
        report.info("verify research info displayed");
        for (WebElement ele : customInformationFieldList) {
            if (fieldName.equalsIgnoreCase(ele.getText().trim())) {
                report.info(fieldName + " is present in custom info");
                flag = true;
                Assert.assertTrue(flag, fieldName+" is not in custom info");
                break;
            }
        }
        if (!flag) {//search for field
        }
        return  this;
    }

    public SettingsPage clickOnConfiguration(){
        report.info("click on configuration");
        actions.click(configuration);
        return this;
    }

    public SettingsPage clickOnPicklistFeidValue(){
        report.info("click on picklist field values");
        actions.click(picklistFieldValues);
        return this;
    }
}
