package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
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
    @FindBy(name="first_name")
    private WebElement firstName;
    @FindBy(name = "imagename[]")
    private WebElement uploadImage;
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveBtn;
    @FindBy(xpath = "//td[@id='Users_detailView_fieldValue_user_name']/span")
    private WebElement usernameTxt;
    @FindBy(xpath = "//td[@id='Users_detailView_fieldValue_first_name']/span")
    private WebElement firstNameTxt;
    @FindBy(xpath = "//td[@id='Users_detailView_fieldValue_email1']/span")
    private WebElement emailTxt;
    @FindBy(xpath = "//td[@id='Users_detailView_fieldValue_is_admin']/span")
    private WebElement isAdminTxt;
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
    @FindBy(xpath = "//a[text()='Main Menu']")
    private WebElement mainMenu;
    @FindAll({
    @FindBy(xpath = "(//div[@data-appname='MARKETING']/descendant::div[@title='Campaigns'])[last()]"),
        @FindBy(xpath = "(//a[@title='Campaigns'])[last()]")})
    private WebElement campaigns;
    @FindBy(id="appnavigator")
    private WebElement hambergerMenu;
    @FindBy(xpath = "(//span[contains(text(),'MARKETING')])[last()]")
    private WebElement marketingInMenuList;
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

    public SettingsPage enterUsersMandtoryInfoWithFirstName(String username,String firstname, String emailAddress, String lastname, String password1, String confirmpassword,
                                                            String rolename, boolean isAdmin, String imagePath){
        report.info("creating user with below Information");
        report.info(String.format("username: %s\n first name: %s \n " +
                "email: %s \n last name: %s \n password: %s \n rolename:%s \n image path: %s", username, firstname, emailAddress, lastname
        , password1, rolename, imagePath));
        actions.type(firstName, firstname);
        enterUsersMandatoryInfo(username, emailAddress, lastname, password1, confirmpassword, rolename, isAdmin, imagePath);
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

    public SettingsPage clickOnMainMenu(){
        report.info("click on main menu");
        actions.click(mainMenu);
        return  this;
    }

    public SettingsPage verifyCampaignDisplayedInMainMenu(){
        actions.waitOrPause();
        report.info("verifing camnpagin displayed in main menu");
        Assert.assertTrue(campaigns.isDisplayed(),"Campagins not dispalyed");
        return  this;
    }

    public SettingsPage verifyCampaignDispayedInMenuList(){
        report.info("verifing campagin is displayed in menu list");
        actions.click(hambergerMenu);
        Actions act=new Actions(driver);
        act.moveToElement(marketingInMenuList).moveToElement(campaigns).perform();
        Assert.assertTrue(campaigns.isDisplayed(), "Campaigns not displayed");
        return  this;
    }

    public SettingsPage verifyUserAfterSaving(String username, String firstname,String isAdmin){
        Assert.assertEquals(usernameTxt.getText().trim(), username);
        Assert.assertEquals(firstNameTxt.getText().trim(), firstname);
        Assert.assertEquals(isAdminTxt.getText().trim(), isAdmin);
        return this;
    }
}
