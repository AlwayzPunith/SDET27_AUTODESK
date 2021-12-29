package com.crm.autodesk.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	
	//@Parameters("browser")
	@BeforeClass(groups = {"smokeSuite","regressionSuite"})
	public void launchBrowser() throws Throwable //public void launchBrowser(String BROWSER)
	{
		
		String URL=fLib.getPropertyKeyValue("url");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else 
		{
			System.out.println("invalid Browser");
		}
		
		sdriver=driver;
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		System.out.println("=============Launch Browser=========");
		
	}
	
	@BeforeMethod(groups = {"smokeSuite","regressionSuite"})
	public void loginToApp() throws Throwable
	{
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		System.out.println("Login.........SuccesFull");

	}
	
	@AfterMethod(groups = {"smokeSuite","regressionSuite"})
	public void logoutApp()
	{
		HomePage hp=new HomePage(driver);
		hp.logout(driver);
		System.out.println("Logout ......SuccessFull");
	}
	
	@AfterClass(groups = {"smokeSuite","regressionSuite"})
	public void closeBrowser()
	{
		driver.quit();
		System.out.println("=========Close Browser=========");
	}
	
	
	

}
