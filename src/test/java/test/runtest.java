package test;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class runtest extends BaseTest{
	
	@Test(description = "TC_001_Testcasename")
	public void test() throws InterruptedException {
		new HomePage(driver)
		.clickOnProfile()
		.clickOnSignout()
		.clickOnDashBoard()
		.dashboarfInfo();;
	}
	
	@Test
	public void test1() throws InterruptedException {
		new HomePage(driver)
		.clickOnProfile()
		.clickOnSignout();
		Assert.fail();
	}
	
	@Test
	public void test2() throws InterruptedException {
		new HomePage(driver)
		.clickOnProfile()
		.clickOnSignout();
		throw new SkipException("skip");
	}

}
