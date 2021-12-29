package Practice;

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

import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CrrateContactWithOraganisation1 {

	public static void main(String[] args) throws Throwable {
		//Create Objects
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		
		//To Generate Random Number
		int randNum = jLib.getRandomNumber();
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String cname=fLib.getPropertyKeyValue("contactName")+randNum;
		
		
		//Read Excel Data 
		String orgName = eLib.getDataFromExcel("org", 1, 2)+randNum;
		
		
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
		
		//Implicit wait
		wLib.waitForPageToLoad(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement msg = driver.findElement(By.className("dvHeaderText"));
		//explicitly Wait
		wLib.waitForElementToBeClickAble(driver,msg);
		
		//Step5: NAvigate to Contact MOdule
		driver.findElement(By.linkText("Contacts")).click();
		 
		 //Step6: Click on Create Contact Button
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step7: Enter the Details & Create New Contact
		driver.findElement(By.name("lastname")).sendKeys(cname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//To Switch To Child Window
		wLib.switchToWindow(driver, "Accounts");
		//To Search org name in Child Window
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//To Switch Back to Parent Window
		wLib.switchToWindow(driver, "Contacts");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  String smsg = driver.findElement(By.className("dvHeaderText")).getText();
		
		if(smsg.contains(cname))
		{
			System.out.println(cname+"is Successfully Created....PASS");
		}
		else
		{
			System.out.println(cname+"is Not  Created....FAIL");
		}
		//To Log Out
		wLib.mouseOverElement(driver, driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
