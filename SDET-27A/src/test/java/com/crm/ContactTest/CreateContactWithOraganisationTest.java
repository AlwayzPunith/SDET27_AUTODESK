package com.crm.ContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactsInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
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

public class CreateContactWithOraganisationTest extends BaseClass{
	
	@Test(groups = "regressionSuite")
	public void CreateContactWithOraganisationTest() throws Throwable
	{
		
		int randNum=jLib.getRandomNumber();
		String cname=fLib.getPropertyKeyValue("contactName")+randNum;
		
		//Read Excel Data 
		String orgName = eLib.getDataFromExcel("org", 1, 2)+randNum;
		
		//To Click on organization Link
		HomePage hp=new HomePage(driver);
		hp.clickOnOraganizationsLnk();
		
		//To create Organization
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOnCreateOrg();
		
		//To enter details of organization
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.createOrg(orgName);

		WebElement msg = driver.findElement(By.className("dvHeaderText"));
		//explicitly Wait
		wLib.waitForElementToBeClickAble(driver,msg);
		
		//Step5: NAvigate to Contact MOdule
		hp.clickOnContactsLnk();
		 
		 //Step6: Click on Create Contact Button
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//Step7: Enter the Details & Create New Contact
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContactWithOrg(driver, cname, orgName);
		
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String actMsg = cip.getContactInfo();
			
		//Validation
		Assert.assertTrue(actMsg.contains(cname));
		System.out.println("Contact Created Successfull ");
		
	}

}
