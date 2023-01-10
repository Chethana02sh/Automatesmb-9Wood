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

    @Test(description = "NINEWOOD-273")
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

    @Test(description = "VD-1242-4")
    public void verifyChartCount(){
        HomePage homepage=new HomePage(driver);
        homepage.clickOnMenu()
                .clickOnReport()
                .enterChartTypeAndClickOnFirstChart("Chart")
                .verifyNoChartDataOrCountNumberDisplayed();

    }

    public int getRandomNum(){
        return new Random().nextInt(1000);
    }
    @Test (description = "VD-1242-1", dataProvider = "CalendarAsPrimaryModuleInReport", dataProviderClass = DataProviderCls.class)
    public void checkExistingCalendarChartReportAsPrimaryModule(Map<String, String> data){
        String reportname=data.get("ReportName")+getRandomNum();
       HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.CHART_REPORT)
                .enterReportName(reportname)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnNextBtn()
                .selectGroupByField(data.get("GroupField"))
                .clickOnSaveAndGenerateReport()
                .verifyChartContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Chart")
                .enterPrimaryModule("Calendar")
                .enterReportName(reportname)
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }

    @Test(description = "VD-1242-3", dataProvider = "ReportData", dataProviderClass = DataProviderCls.class)
    public void checkExistingCalendarChartReportAsSecondaryModule(Map<String, String> data){
        String reportname=data.get("ReportName")+getRandomNum();
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.CHART_REPORT)
                .enterReportName(reportname)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .selectRelatedModule(data.get("RelatedModule"))
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnNextBtn()
                .selectGroupByField(data.get("GroupField"))
                .clickOnSaveAndGenerateReport()
                .verifyChartContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Chart")
                .enterPrimaryModule(data.get("PrimaryModule"))
                .enterReportName(reportname)
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }

    @Test(description = "VD-1242-1", dataProvider = "ReportData", dataProviderClass = DataProviderCls.class)
    public void CheckExistingChartReportWithAnyModuleOtherThanCalendar(Map<String, String> data){
        String reportname=data.get("ReportName")+getRandomNum();
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.CHART_REPORT)
                .enterReportName(reportname)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnNextBtn()
                .selectGroupByField(data.get("GroupField"))
                .clickOnSaveAndGenerateReport()
                .verifyChartContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Chart")
                .enterPrimaryModule(data.get("PrimaryModule"))
                .enterReportName(reportname)
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }

    @Test (description = "VD-1242-8", dataProvider = "CalendarAsPrimaryModuleInReport", dataProviderClass = DataProviderCls.class)
    public void checkExistingCalendarDetailReportAsPrimaryModule(Map<String, String> data){
        String reportname=data.get("ReportName")+getRandomNum();
        List<String> columns=List.of("Calendar Subject", "Calendar Assigned To");
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.DETAILREPORT)
                .enterReportName(reportname)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .clickOnNextBtn()
                .enterSelectColumnField(columns)
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnSaveAndGenerateReport()
                .verifyDetailReportContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Detail")
                .enterPrimaryModule("Calendar")
                .enterReportName(reportname)
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }

    @Test(description = "VD-1242-9", dataProvider = "ReportData", dataProviderClass = DataProviderCls.class)
    public void checkExistingCalendarDetailReportAsSecondaryModule(Map<String, String> data){
        String reportname=data.get("ReportName")+getRandomNum();
        List<String> column=List.of("Contacts First Name", "Contacts Last Name", "Contacts Title","Calendar Subject");
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnReport()
                .clickOnAddReport(ReportPage.ChartType.DETAILREPORT)
                .enterReportName(reportname)
                .selectReportFolder(data.get("ReportFolder"))
                .selectPrimaryModule(data.get("PrimaryModule"))
                .selectRelatedModule(data.get("RelatedModule"))
                .clickOnNextBtn()
                .enterSelectColumnField(column)
                .clickOnNextBtn()
                .enterAllCondtionFields(data.get("ConditionField"), data.get("CompareField"))
                .clickOnSaveAndGenerateReport()
                .verifyDetailReportContentDisplayed();
        homePage.clickOnMenu()
                .clickOnReport()
                .enterChartType("Detail")
                .enterPrimaryModule(data.get("PrimaryModule"))
                .enterReportName(reportname)
                .clickOnSearchBtn()
                .clickOnFirstReportNameInList()
                .verifyNoChartDataOrCountNumberDisplayed();
    }
}
