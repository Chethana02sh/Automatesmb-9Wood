package test;

import base.BaseTest;

import base.DataProviderCls;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ReportPage;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class ReportTest extends BaseTest {

    @Test
    public void verifyDownloadIconDisplayedInReport(){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu();
        ReportPage reportPage=homePage .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.DETAILREPORT)
                .enterReportName("MY_REPORT_011"+Math.random())
                .selectReportFolder("Test Chethana")
                .selectPrimaryModule("Calendar")
                .clickOnNextBtn()
                .enterSelectColumnField(List.of("Calendar Subject","Calendar End Time","Calendar Start Date & Time"));
                reportPage.clickOnNextBtn();
                reportPage.selectAllConditions("(Calendar) Subject","Test_1234")
                .clickOnSaveAndGenerateReport()
                .enterCampareField("Test_1234");
        homePage.clickOnMenu()
                .clickOnReport()
                .searchReportName("MY_REPORT_011")
                .verifyDownloadIsDisplayed("MY_REPORT_011");

    }

    @Test
    public void verifyChartCount(){
        HomePage homepage=new HomePage(driver);
        homepage.clickOnMenu()
                .clickOnReport()
                .enterChartTypeAndClickOnFirstChart("Chart")
                .verifyNoChartDataOrCountNumberDisplayed();

    }

    @Test
    public void checkExistingCalendarChartReportAsPrimaryModule(){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Chart")
                .enterPrimaryModule("Calendar")
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }

    @Test(dataProvider = "ReportData", dataProviderClass = DataProviderCls.class)
    public void checkExistingCalendarChartReportAsSecondaryModule(Map<String, String> data){
        int randomNum=new Random().nextInt(100);
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.CHART_REPORT)
                .enterReportName(data.get("ReportName")+randomNum)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .selectRelatedModule(data.get("RelatedModule"))
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnNextBtn()
                .selectGroupByField(data.get("GroupField"))
                .clickOnSaveAndGenerateReport()
                .verifyChartContentDisplayed();
    }
}
