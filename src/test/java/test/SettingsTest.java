package test;

import base.BaseTest;
import lombok.Data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExcelUtility;

import java.util.Map;
import java.util.Random;

public class SettingsTest extends BaseTest {

    public int random(){
        return new Random().nextInt(1000);
    }

    @DataProvider
    public Object[][] getData() throws Throwable {
        return new ExcelUtility().readExcel("./src/test/resources/userSettings.xlsx", "user");
    }

    @Test(dataProvider = "getData")
    public void createBothAdminAndNonAdminTest(Map<String,String> map){
        String username=map.get("name")+random();
        HomePage homepage = new HomePage(driver);
        homepage.clickOnMenu()
                .clickOnSettings()
                .clickOnUserManagement()
                .clickOnUser()
                .clickOnAddUser()
                .enterUsersMandatoryInfo(username,//Todo: change the username to any other name which is not exist in list
                        map.get("email"),
                        map.get("lastname"),
                        map.get("password"),
                        map.get("confirmpassword"),
                        map.get("role"),
                        Boolean.parseBoolean(map.get("isAdmin")),//admin
                        System.getProperty("user.dir")+"/src/test/resources/images/user_img.jpg")
                .clickOnSave()
                .verifyUserInActiveUserList(username, map.get("admintext"));

    }
}
