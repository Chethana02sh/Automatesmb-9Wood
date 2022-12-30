package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrganizationTest extends BaseTest {

    @Test
    public void verifyUpdatesPrintInCSVFile(){
        SimpleDateFormat time=new SimpleDateFormat("dd_mmm_yy_hh_mm");
        String date = time.format(new Date());
        String orgName="Automation_Practice"+date;
        String path=System.getProperty("user.dir")+"/src/test/resources/Organizations_1_record.csv";
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnOrganization()
//                .clickOnAddOrgganizationBtn()
//                .enterOrganizationName(orgName)
//                .clickOnSave()
//                .verifyOrganizationNameAndHeaderDisplayed(orgName)
//                .clickOnUpdates()
//                .verifyOrganizationUpdates()
//                .clickOnEditBtn()
//                .enterOrganizationName("CRM_Org")
//                .enterWebsite("www.proposal.com")
//                .clickOnSave()
//                .verifyOrganizationNameAndHeaderDisplayed("CRM_Org")
//                .clickOnUpdates()
//                .verifyOrganizationUpdates()
                .clickOnImportBtn()
                .selectCsvFileFromComputer(path)
                .clickOnNextBtn()
                .selectOrganizationfieldMapping("Organization Name")
                .clickOnImport();
        //pending:: Import
    }
}
