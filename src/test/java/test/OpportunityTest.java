package test;

import base.BaseTest;
import base.DataProviderCls;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Map;

public class OpportunityTest extends BaseTest {

    @Test(dataProvider = "OppertunityData", dataProviderClass = DataProviderCls.class)
    public void verifyOppertunityPhoneNumber(Map<String, String> data) {
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnOppertunity()
                .clickOnAddOppertunityBtn()
                .enterOppertunityName(data.get("Opportunity"))
                .enterSalesStage(data.get("SalesStage"))
                .clickOnAddContactName()
                .enterContactInfo(data.get("ContactName"), data.get("email"), data.get("phone"))
                .clickOnCampaginSourceCreateBtn()
                .enterCampaginName(data.get("campaign name"))
                .clickOnSaveBtn()
                .clickOnPhoneNumberAndVerifyPopUp(data.get("phone"));
    }

    @Test(dataProvider = "OppertunityData", dataProviderClass = DataProviderCls.class)
    public void verifyContactNameIsDisplayingOnSummaryPageOfOpportunity(Map<String, String> data){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnOppertunity()
                .clickOnAddOppertunityBtn()
                .enterOppertunityName(data.get("Opportunity"))
                .enterSalesStage(data.get("SalesStage"))
                .clickOnAddContactName()
                .enterContactInfo(data.get("ContactName"), data.get("email"), data.get("phone"))
                .clickOnCampaginSourceCreateBtn()
                .enterCampaginName(data.get("campaign name"))
                .clickOnSaveBtn();

        //Todo: verifying name in contact name is pending bcoz of app issue
    }

    @Test(description = "NINEWOOD-272",dataProvider = "OppertunityData", dataProviderClass = DataProviderCls.class)
    public void verifyOpportunitiesInList(Map<String, String> data){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnOppertunity()
                .clickOnAddOppertunityBtn()
                .enterOppertunityName(data.get("Opportunity"))
                .selectAssignedTo(data.get("AssignedTo"))
                .enterSalesStage(data.get("SalesStage"))
                .clickOnAddContactName()
                .enterContactInfo(data.get("ContactName"), data.get("email"), data.get("phone"))
                .clickOnCampaginSourceCreateBtn()
                .enterCampaginName(data.get("campaign name"))
                .clickOnSaveBtn()
                ;
        //Pending : validating after creating new oppertunity with assigned To

        //verifing existing assigned to
        homePage.clickOnMenu()
                .clickOnOppertunity()
                .verifyAssignedToInList(data.get("AssignedTo"));

    }
}
