package com.crm.ContactTest;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactsInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.genericUtility.BaseClass;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

@Listeners(com.crm.autodesk.genericUtility.ListenerImplementation.class)
public class CreateContactTest extends BaseClass{
	

	@Test(groups = "smokeSuite")
	public void CreateContactTest() throws Throwable
	{
		
		//To Generate Random Number
		int randnum = jLib.getRandomNumber();
		String cname=eLib.getDataFromExcel("org", 1, 2)+randnum;
		
		//
		HomePage hp=new HomePage(driver);
		hp.clickOnContactsLnk();
		Assert.fail();
		//
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		//
		CreateContactPage cc=new CreateContactPage(driver);
		cc.createContact(cname);
		
		//
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String actMsg = cip.getContactInfo();
		
		Assert.assertTrue(actMsg.contains(cname));
		System.out.println("Contact Created Successfull ");
		
	}

}
