package com.crm.ProductTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CreateCustomFilterWithAdvancedFilter {

	public static void main(String[] args) throws Throwable 
	{
		//create objects
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		ExcelUtility eLib=new ExcelUtility();
		
		//Step1:Navigate To CRM Application
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		
		
		WebDriver driver;
		if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("ie"))
		{
			driver=new InternetExplorerDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		//Step2:Login to Application
		wLib.waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		
		//Login 
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		
		//Step3:Click On Product Link
		HomePage hp=new HomePage(driver);
		hp.clickOnProductLnk();
				
		//Step4:Choose Existing Filter From the Filter Option
		WebElement plist = driver.findElement(By.id("viewname"));
		wLib.select(plist, "Product_list123");
		
		//Step5:Click on Edit Link
		driver.findElement(By.linkText("Edit")).click();
		
		//Step6:Edit Values
		driver.findElement(By.name("setStatus")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step7:Logout
		WebElement mover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseOverElement(driver, mover);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}

}
