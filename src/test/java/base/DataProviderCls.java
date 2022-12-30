package base;

import lombok.Data;
import org.testng.annotations.DataProvider;
import utils.ExcelUtility;

import java.util.Map;

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

    @DataProvider(name = "OppertunityData")
    public Object[][] getOppertunityData()  {
        Object[][]data=null;
        try {
            data= new ExcelUtility().readExcel("./src/test/resources/Opportunity.xlsx", "Oppertunity");
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
}
