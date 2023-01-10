package test;

import base.BaseTest;
import base.DataProviderCls;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Map;

public class ProjectOppTest extends BaseTest {

    @Test(description = "NINEWOOD-260",dataProvider = "ProjectOppData", dataProviderClass = DataProviderCls.class)
    public void verifyMassUpdateProjectOpp(Map<String, String> data){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnProjectOpp()
                .clickOnAddRecordBtn()
                .enterProjectName(data.get("Project1"))
                .clickOnSave()
                .verifyProjectNameAndIconDisplayed(data.get("Project1"))
                .clickOnAddRecordBtn()
                .enterProjectName(data.get("Project2"))
                .clickOnSave()
                .verifyProjectNameAndIconDisplayed(data.get("Project2"))
                .clickOnProjectOppAll()
                .clickOnMassEditCheckBox()
                .clickOnEditPencilBtn()
                .updateProjectName(data.get("UpdatedProjectName"))
                .clickOnSave();//it's till mass edit alert pop up message
        //Pending:: Email confirmation


    }
}
