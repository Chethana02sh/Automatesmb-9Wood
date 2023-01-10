package test;

import base.BaseTest;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;

import java.util.Arrays;
import java.util.List;

public class ContactTest extends BaseTest {

    @Test(description = "")
    public void verifyFilter() {
        String listName = "Test List Create" + getRandom();
        try {
            //create
            List<String> columnList = Arrays.asList("Organization Name", "First Name", "Last Name");
            HomePage homePage = new HomePage(driver);
            homePage.clickOnMenu();
            homePage.clickOnContacts();
            ContactsPage contactsPage = new ContactsPage(driver);
            contactsPage.clickOnCreateFilterBtn();
            contactsPage.enterListNameAndColumnList(listName, columnList);
            contactsPage.clickOnSaveBtn();
            contactsPage.verifyColumnNamesInTableHeader(columnList);
            String countWhileCreate = contactsPage.getTotalRecordCount();

            //Edit
            contactsPage.clickOnFilterArrowDown(listName);
            contactsPage.clickOnEdit();
            listName = "Test List Edit";
            contactsPage.enterListNameAndColumnList(listName, null);
            contactsPage.clickOnSaveBtn();
            String countAfterEdit = contactsPage.getTotalRecordCount();
            Assert.assertEquals(countWhileCreate, countAfterEdit);
        } finally {
            ContactsPage contactsPage = new ContactsPage(driver);
            contactsPage.clickOnFilterArrowDown(listName);
            contactsPage.clickOnDelete();
        }
    }
}
