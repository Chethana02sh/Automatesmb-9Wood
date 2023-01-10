package test;

import base.BaseTest;
import base.DataProviderCls;
import base.FilePaths;
import lombok.Data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ReportPage;
import utils.ExcelUtility;
import utils.FileManager;

import java.util.Map;
import java.util.Random;

public class SettingsTest extends BaseTest {

    public int random(){
        return new Random().nextInt(1000);
    }



    @Test (description = "NINEWOOD-261",dataProvider = "userManagement")
    public void createBothAdminAndNonAdminTest(Map<String,String> map){
        String username=map.get("name")+random();
        HomePage homepage = new HomePage(driver);
        homepage.clickOnMenu()
                .clickOnSettings()
                .clickOnUserManagement()
                .clickOnUser()
                .clickOnAddUser()
                .enterUsersMandatoryInfo(username,//Todo: change the username to any other name which is not exist in list
                        map.get("email"),
                        map.get("lastname"),
                        map.get("password"),
                        map.get("confirmpassword"),
                        map.get("role"),
                        Boolean.parseBoolean(map.get("isAdmin")),//admin
                        System.getProperty("user.dir")+"/src/test/resources/images/user_img.jpg")
                .clickOnSave()
                .verifyUserInActiveUserList(username, map.get("admintext"));

    }

    @Test(description = "NINEWOOD-270  Campaign Module No Longer Showing")
    public void verifyCampaignsDisplayedInMenu(){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnSettings()
                .clickOnConfiguration()
                .clickOnMainMenu()
                .verifyCampaignDisplayedInMainMenu()
                .verifyCampaignDispayedInMenuList();
    }

    @Test(description = "VD-1209", dataProvider = "userManagementInfo", dataProviderClass = DataProviderCls.class)
    public void verifyUsernameAndReportTest(Map<String , String> map){
        String username=map.get("name")+random();
        String reportname=map.get("ReportName")+random();
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnSettings()
                .clickOnUserManagement()
                .clickOnUser()
                .clickOnAddUser()
                .enterUsersMandtoryInfoWithFirstName(
                       username,
                        map.get("FirstName"),
                        map.get("email"),
                        map.get("lastname"),
                        map.get("password"),
                        map.get("confirmpassword"),
                        map.get("role"),
                        Boolean.parseBoolean(map.get("isAdmin")),//admin
                        System.getProperty("user.dir")+"/src/test/resources/images/user_img.jpg")
                .clickOnSave()
                .verifyUserAfterSaving(username, map.get("FirstName"), map.get("admintext"));
        LoginPage loginPage=new LoginPage(driver);
        loginPage.logout();
        loginPage.login(username, map.get("password"));
        loginPage.clickOnGetStarted();
        homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.CHART_REPORT)
                .enterReportName(reportname)
                .selectReportFolder(map.get("ReportFolder"))
                .selectPrimaryModule(map.get("PrimaryModule"))
                .selectRelatedModule(map.get("RelatedModule"))
                .clickOnNextBtn()
                .enterAllCondtionFields(map.get("ConditionField"), map.get("CompareField"))
                .clickOnNextBtn()
                .selectGroupByField(map.get("GroupField"))
                .clickOnSaveAndGenerateReport()
                .verifyChartContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .VerifyReportNameAndOwnerName(reportname, map.get("FirstName"));


    }
}
