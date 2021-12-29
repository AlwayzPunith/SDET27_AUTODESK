package Practice;

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

import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CreateOrgWithIndustryandType1 {

	public static void main(String[] args) throws Throwable {
		
		//Create Object 
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//To Generate Random Number
		int randNum=jLib.getRandomNumber();
		String URL=fLib.getPropertyKeyValue("url");
		String USERNAME=fLib.getPropertyKeyValue("username");
		String PASSWORD=fLib.getPropertyKeyValue("password");
		String BROWSER=fLib.getPropertyKeyValue("browser");
		String cname=fLib.getPropertyKeyValue("contactName")+randNum;
		
		//To Read Data From Excel File
		String orgName = eLib.getDataFromExcel("org", 1, 2)+randNum;
		String indus = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		
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
		
		//Implicit Wait
		wLib.waitForPageToLoad(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
	    //To Select Industry Option
		WebElement ind = (WebElement) driver.findElement(By.name("industry"));
		wLib.select(ind, indus);
				
		//To Select Press Type	
		WebElement typ = (WebElement) driver.findElement(By.name("accounttype"));
		wLib.select(typ, type);
	
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String msg = driver.findElement(By.className("dvHeaderText")).getText();
		String msgindtype = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		 String msgtype = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		 
		 if(msg.contains(orgName) && msgindtype.contains(indus) && msgtype.contains(type))
		 {
			 System.out.println(orgName+" is Created......PASS");
			 
		 }
		 else
		 {
			 System.out.println(orgName+"is Not Created......FAIL");
		 }
		
		
	}

}
