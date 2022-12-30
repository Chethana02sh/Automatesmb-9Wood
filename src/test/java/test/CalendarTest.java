package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

public class CalendarTest extends BaseTest {

    @Test
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

    @Test
    public void verifyTaskPriority(){
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMenu()
                .clickOnSettings()
                .clickOnConfiguration()
                .clickOnPicklistFeidValue();
        //page is blank (blocker)
    }
}
