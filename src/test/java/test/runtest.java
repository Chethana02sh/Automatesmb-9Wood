package test;

import api.IEndPoints;
import api.RequestUtil;
import base.FilePaths;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CampaignPage;
import pages.HomePage;
import utils.ExcelUtility;

import java.util.List;
import java.util.Map;

public class runtest extends BaseTest{
	
	@Test(description = "TC_001_Testcasename")
	public void test() throws Throwable {
		Map<String, String> data = ExcelUtility.getExcelData(FilePaths.EXECL_FILE,"campaign");//
		HomePage homePage = new HomePage(driver);
				homePage.navigateToMetaInfo(IEndPoints.ALL_MODULE_INFO)
						.clickOnMenu()
						.clickOnModuleInMenu((String) homePage.getJsonData(homePage.getJson(), "[0]"));
		CampaignPage campaignPage=new CampaignPage(driver);
		campaignPage.clickOnAddButton()
				.navigateToMetaInfo(String.format(IEndPoints.FIELD_META, homePage.getJsonData(homePage.getJson(), "[0]")))
				.verifyAllCampaignLabels()
				.verifyAndEnterCampaignField(data.get("CampainName"))
				.verifyAndSelectAssignedTo(data.get("AssignedTo"))
				.verifyAndEnterCampaistatus(data.get("CampaginStatus"))
				.verifyAndEnterCallToAction(data.get("CallToAction"))
				.verifyAndClickOnProductPlusBtn()
				.saveProduct(data.get("productName"),data.get("units"), data.get("stocks"));
				campaignPage.verifyAndSelectExpectedEndDate("6","December", "2022")//don't read data
				.verifyAndEnterTargetAudience(data.get("TargetAudience"))// start reading data by using data.get("columnHeader")-> yellow color
				.verifyAndEnterTargetSize(data.get("TargetSize"))
				.verifyAndEnterNumSet("10")
				.verifyAndEnterSponserField("Abc")
				.verifyAndEnterAudience("Architects")
				.verifyAndEnterResearchNotes("research1")
				.verifyAndEnterDescription("description of campaign")
				.verifyAndEnterBugetCost("5000")
				.verifyAndEnterActualCost("45000")
				.verifyAndEnterExpectedRevenue("5000")
				.verifyAndEnterExpectedSalesCount("100")
				.verifyAndEnterActualSalesCount("80")
				.verifyAndEnterExpectedResponseCount("50")
				.verifyAndEnterActualResponseCount("60")
				.verifyAndEnterExpectedROI("10")
				.verifyAndEnterActualROI("13")
				.verifyAll();
//				.verifyCampaignsIsPresent()
//				.verifyCalendarIsPresent()
//				.verifyLeadIsPresent()
//				.verifyContactIsPresent()
//				.verifyOrganizationsIsPresent()
//				.verifyOpportunitiesIsPresent()
//				.verifyDocumentsIsPresent()
//				.verifyStakeholders()
				;
	}
	
	@Test
	public void test1() throws InterruptedException {
		new HomePage(driver)
		.clickOnProfile()
		.clickOnSignout();
		Assert.fail();
	}
	
	@Test
	public void test2() throws InterruptedException {
		new HomePage(driver)
		.clickOnProfile()
		.clickOnSignout();
		throw new SkipException("skip");
	}



}
