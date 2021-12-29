package com.crm.autodesk.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;



/**
 *It Contains WbDeiver Specific reusable actions 
 * @author Punith
 *
 */

public class WebDriverUtility 
{
	/**
	 * To Maximize the Browser
	 * @param driver
	 */
	public void maximizeWindows(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	
	
	/**
	 * wait for Page to load before identifying any synchronized element in DOM[HTML-Document]
	 * @param driver
	 */
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	
	/**
	 *wait for page to load before identifying any assynchronized [java scripts actions] element DOM[HTML-Documnet]
	 *@param driver
	 */
	public void waitForPageToLoadForJSElement(WebDriver driver)
	{
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	}
	
	/**
	 * Used to wait for Element to be clicked in GUI & Check for Specific element for every 500 milli Seconds
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickAble(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	/**
 * Used to wait for Element to be clicked in GUI & Check for Specific element for every 500 milli Seconds
	 * @param driver
	 * @param element
	 * @param pollingTime in the form of Second
	 * @throws Throwable 
	 * @throws Throwable
	 */
	
	public void waitFoeElementWithCustomTimeOut(WebDriver driver,WebElement element,int pollingTime) throws Throwable
	{
		FluentWait wait=new FluentWait(driver);
		wait.pollingEvery(pollingTime,TimeUnit.SECONDS);
		wait.wait(20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * Used to Switch to Any Window based on Window Title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();

		while(it.hasNext())
		{
			String wID=it.next();
			driver.switchTo().window(wID);
			String CurrWindowTittle=driver.getTitle();
			if(CurrWindowTittle.contains(partialWindowTitle))
			{
				break;
			}
		}
	}
		
		/**
		 * used to Switch to Alert Window & Click on OK button
		 * @param driver
		 */

		public void switchToAlertWindowAndAccept(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
	
		/**
		 * used to Switch to Alert Window & Click on Cancel button
		 * @param driver
		 */

		public void switchToAlertWindowAndCancel(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}

		/**
		 * Used to Switch Frame Window based on index
		 * @param drive
		 * @param index
		 */
		public void switchToFrame(WebDriver driver,int index)
		{
			driver.switchTo().frame(index);
		}
		/**
		  Used to Switch Frame Window based on id or name,attribute
		 * @param drive
		 * @param id_name_attribute		 */
	
		public void switchToFrame(WebDriver driver,String id_name_attribute)
		{
			driver.switchTo().frame(id_name_attribute);
		}
	
	/**
	 * used to select the value fron the dropdown on index 
	 * @param driver
	 * @param index
	 */
	
		public void select(WebElement element,int index)
		{
			Select sel=new Select(element);
			sel.selectByIndex(index);		
		}
		/**
		 *used to select the value fron the dropdown based on value/option available in GUI 
		 * @param element
		 * @param value
		 * @return 
		 */
		public Object select(WebElement element,String text)
		{
			Select sel=new Select(element);
			sel.selectByVisibleText(text);;
			return sel;		
		}
		/**
		 * used to place mouse cursor on specified element 
		 * @param driver
		 * @param element
		 */
		public void mouseOverElement(WebDriver driver,WebElement element)
		{
			Actions act=new Actions(driver);
			act.moveToElement(element).perform();
		}
		/**
		 * used to right click on Specific element
		 * @param driver
		 * @param element
		 */
	public void rightClickOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**For Disabled Elements
	 * @param driver
	 * @param javaScript
	 */
	
	public void executeJavaScript(WebDriver driver,String javaScript)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeAsyncScript(javaScript, null);
	}
	
	/**
	 * For Disabled Elements & It will Wait Until its Enabled then it Clicks
	 * @param element
	 * @throws InterruptedException 
	 */
	public void waitAndClick(WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<20)
		{
			try
			{
				element.click();
				break;
			}
			catch(Throwable e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * Used to Take Screen Shot 
	 * @throws Throwable 
	 * @throws 
	 */
	public void takeScreenShot(WebDriver driver,String screenshotName) throws Throwable
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/"+screenshotName+".PNG");
		Files.copy(src,dest);

	}
	
	/**
	 * To Pass Enter Key appertain in Browser
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
}
