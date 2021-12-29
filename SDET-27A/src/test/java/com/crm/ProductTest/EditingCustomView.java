package com.crm.ProductTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class EditingCustomView {

	public static void main(String[] args) throws Throwable
	{
		//Crate Objects
		FileUtility fLib=new FileUtility();
		WebDriverUtility wLib=new WebDriverUtility();

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
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step3:Click On Product Link
		driver.findElement(By.linkText("Products")).click();

		//Step4:Choose Existing Filter From the Filter Option
		WebElement plist = driver.findElement(By.id("viewname"));
		wLib.select(plist, "New");


		//Step5:Click on Delete Link
		driver.findElement(By.linkText("Delete")).click();

		
		/*WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert al=driver.switchTo().alert();
		al.accept();*/
		wLib.switchToAlertWindowAndAccept(driver);

	
		//Validation
		driver.findElement(By.linkText("Products")).click();
		//wLib.select(plist, "New");
		boolean option = driver.findElement(By.xpath("(//option[contains(text(),'New')])")).isSelected();
		if(option==true)
		{
			System.out.println("Successfully Filter Deleted.......PASS");
		}
		else
		{
			System.out.println(" Filter is Not Deleted.......FAIL");
		}
		
		//Step6:LogOut
		WebElement mover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseOverElement(driver, mover);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}



}
