package com.crm.OrgTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.BaseClass;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CreateOrgTest extends BaseClass {

	@Test(groups = "smokeSuite")
	public void CreateOrgTest() throws Throwable 
	{
		//Generating Random Num
		int randNum = jLib.getRandomNumber();
		
		//Read Excel Data 
		String orgName = eLib.getDataFromExcel("Org", 1, 2)+randNum;
	
		//Navigate to Organization Module
		HomePage hp=new HomePage(driver);
		hp.clickOnOraganizationsLnk();
		
		//Click on "Create organization" Module
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrg();
		
		//Enter all details & Create new Organixation
		CreateOrganizationPage cp=new CreateOrganizationPage(driver);
		cp.createOrg(orgName);
		
		//Verify organization name in header of the msg
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actMsg=oip.getOrgInfo();
		
		
		Assert.assertTrue(actMsg.contains(orgName));
		System.out.println(orgName+"Organization Created Successfully");
		
	}

}
