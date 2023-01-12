package test;

import base.BaseTest;
import base.DataProviderCls;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.OpportunityPage;

import java.util.Map;

public class OpportunityTest extends BaseTest {

    //Running in mifp
    @Test(description = "NINEWOOD-279",dataProvider = "OpportunityData", dataProviderClass = DataProviderCls.class)
    public void verifyOpportunityPhoneNumber(Map<String, String> data) {
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

    //Running in mifp
    @Test(description = "VD-1218", dataProvider = "OpportunityData", dataProviderClass = DataProviderCls.class)
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
        OpportunityPage opportunityPage=new OpportunityPage(driver);
        opportunityPage.verifyContactInfoInSummary(data.get("ContactName"), data.get("phone"), data.get("email"));
    }

    @Test(description = "NINEWOOD-272",dataProvider = "OpportunityData", dataProviderClass = DataProviderCls.class)
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
