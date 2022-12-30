package test;

import api.IEndPoints;
import api.RequestUtil;
import base.FilePaths;
import org.testng.annotations.Test;
import utils.ExcelUtility;

import java.util.List;
import java.util.Map;

public class APItest {
    @Test
    public void getModuleInfo() throws Throwable {
      //  RequestUtil.getRequest(IEndPoints.ALL_MODULE).then().log().all();
        Map<String, String> list = ExcelUtility.getExcelData(FilePaths.EXECL_FILE,"campaign");
        System.out.println(list.size());
        System.out.println(list);
    }
}
