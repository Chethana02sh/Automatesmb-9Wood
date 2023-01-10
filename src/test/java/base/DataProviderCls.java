package base;

import lombok.Data;
import org.testng.annotations.DataProvider;
import utils.ExcelUtility;

public class DataProviderCls {

    @DataProvider(name = "ReportData")
    public Object[][] getReportData()  {
        Object[][]data=null;
        try {
           data= new ExcelUtility().readExcel("./src/test/resources/ReportTestData.xlsx", "Report");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name="CalendarAsPrimaryModuleInReport")
    public Object[][]  getCalendarChartReport(){
        try {
            return new ExcelUtility().readExcel("./src/test/resources/ReportTestData.xlsx", "CalendarAsPrimary");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "OpportunityData")
    public Object[][] getOpportunityData()  {
        Object[][]data=null;
        try {
            data= new ExcelUtility().readExcel("./src/test/resources/Opportunity.xlsx", "Opportunity");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "ProjectOppData")
    public Object[][] getProjectOppData()  {
        Object[][]data=null;
        try {
            data= new ExcelUtility().readExcel("./src/test/resources/ProjectOppTestData.xlsx", "ProjectOpp");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name="userManagement")
    public Object[][] getUserManagementData() throws Throwable {
        return new ExcelUtility().readExcel("./src/test/resources/userSettings.xlsx", "user");
    }
    @DataProvider(name="userManagementInfo")
    public Object[][] getUserManagementInfoData() throws Throwable {
        return new ExcelUtility().readExcel("./src/test/resources/userSettings.xlsx", "userInfo");
    }

    @DataProvider(name = "getTaskAndEventData")
    public Object[][] getCalendarTaskAndEvent() throws Throwable {
        return new ExcelUtility().readExcel("./src/test/resources/CalendarTestData.xlsx","Calendar_Task_Event");
    }
}
