package test;

import base.BaseTest;
import base.DataProviderCls;
import base.JavaUtils;
import org.testng.annotations.Test;
import pages.CalendarPage;
import pages.HomePage;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class CalendarTest extends BaseTest {

    @Test (description = "NINEWOOD-282")
    public void verifyResearchCoadeShowingInTaskListView(){
        int random=new Random().nextInt(1000);
        String subject="Research Test"+random;
        HomePage homePage=new HomePage(driver);
        //checking research field in settings
        homePage.clickOnMenu()
                .clickOnSettings()
                .clickOnModuleLayoutAndFields()
                .selectModule("Task")
                .verifyFieldIsPresentInCustomInfo("Research Code");
        //verifing in manage list and also creating new task
        homePage.clickOnMenu()
                .clickOnCalendar()
                .clickOnManageList()
                .verifyFieldInSelectedListOrAvailableList("Research Code")
                .createNewTaskWithRecord(subject, "Duplicate")
                .verifySubjectAndResearchCode();

        //verifing created task in calendar task list
        homePage.clickOnMenu()
                .clickOnCalendar()
                .verifyCreatedTaskAndRecordInList(subject,"Duplicate");

        //check for existing task
        homePage.clickOnMenu()
                .clickOnCalendar()
                .editExistingTaskInList("No Contact Attached")
                .verifySubjectAndResearchCode()
                .verifyEditedTaskInList();
    }

    @Test(description = "NINEWOOD-281")
    public void verifyTaskPriority() throws InterruptedException {
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnSettings()
                .clickOnConfiguration()
                .clickOnPicklistFeidValue();
        //page is blank (blocker)
    }

    @Test(dataProvider = "getTaskAndEventData", dataProviderClass = DataProviderCls.class)
    public void  checkActivityRelatedListInAllModules(Map<String, String> data){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnCalendar()
                .clickOnAddTask()
                .enterAllMandatoryFields(data.get("TaskName"),data.get("ModuleName"),data.get("ModuleData"),data.get("Firstname"),data.get("Lastname"))
                .selectStatus("Completed")
                .clickOnSaveButton()
                .verifySavedCalenderTask(data.get("TaskName"), data.get("ModuleData"), data.get("Firstname"))
                .clickOnAddEventBtn()
                .enterAllMandatoryFields(data.get("EventName"), data.get("ModuleName"), data.get("ModuleData"), data.get("Firstname"), data.get("Lastname"))
              //  .selectStatus(data.get("Status"))
                .clickOnSaveButton()
                .verifySavedCalenderTask(data.get("EventName"),null, null);
                //.verifyStatusTxt("Requested");

        if(data.get("ModuleName").equals("Organizations")) {
            homePage.clickOnMenu()
                    .clickOnOrganization()
                    .searchForOrganization(data.get("ModuleData"))
                    .verifyActivity(data.get("TaskName"),data.get("EventName"));
        }
    }

    @Test(description = "")
    public void verifyRecordExistInCurrentDate(){
        JavaUtils javaUtils=new JavaUtils();
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu();
        homePage.clickOnCalendar();
        CalendarPage calendarPage=new CalendarPage(driver);
        calendarPage.clickOnAll();
        calendarPage.selectStartDate("1",javaUtils.getDay(), javaUtils.getCurrentMonth(),javaUtils.getYear());
        calendarPage.enterAssignedTo("User Administrator");
        calendarPage.clickOnSearch();
        calendarPage.clickOnSelectAllCheckBox();
        calendarPage.verifySelectedMessageCount();
    }
}
