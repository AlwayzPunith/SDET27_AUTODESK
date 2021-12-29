package com.crm.OrgTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

public class CreateOrgWithIndustryandTypeTest extends BaseClass {

	@Test(groups = "regressionSuite")
	public void CreateOrgWithIndustryandTypeTest() throws Throwable

	{		
		
		SoftAssert sa=new SoftAssert();
		
		//To Generate Random Number
		int randNum=jLib.getRandomNumber();
		String cname=fLib.getPropertyKeyValue("contactName")+randNum;
		
		//To Read Data From Excel File
		String orgName = eLib.getDataFromExcel("org", 1, 2)+randNum;
		String indus = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		//	
		HomePage hp=new HomePage(driver);
		hp.clickOnOraganizationsLnk();
		
		//Click on "Create organization" Module
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrg();
		
		//Enter all details & Create new Organixation......To Select Industry Option.....To Select Press Type
		CreateOrganizationPage cp=new CreateOrganizationPage(driver);
		cp.createOragWithIndustry(orgName, indus, type);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actMsg = oip.getOrgInfo();	
		
		sa.assertTrue(actMsg.contains(orgName));
		System.out.println("Successfull");
		sa.assertAll();

	}

}

