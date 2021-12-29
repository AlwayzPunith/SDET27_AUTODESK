package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericUtility.ExcelUtility;
import com.crm.autodesk.genericUtility.FileUtility;
import com.crm.autodesk.genericUtility.JavaUtility;
import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CreateOrg1 {

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
		driver.manage().window().maximize();
		driver.get(URL);
		
		//Step 1: Login
		LoginPage lp=new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		
		
		//Step 2: Navigate To Organization Module
		HomePage hp=new HomePage(driver);
		hp.clickOnOraganizationsLnk();
		
		//Step 3:Click on  "Create Organization" Button
 		OrganizationsPage op=new OrganizationsPage(driver);
 		op.clickOnCreateOrg();
 		
 		//Step 4: Enter all the details & Create New Org
 		CreateOrganizationPage cp=new CreateOrganizationPage(driver);
 		cp.createOrg(orgName);
 		
 		//Step 5:Verify organization Name in header of The Msg
 		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
 		String actMsg=oip.getOrgInfo();
 		
			if(actMsg.contains(orgName))
		{
			System.out.println(orgName+" is Created Successfully.....PASS");
		}
		else
		{
			System.out.println(orgName+" is Not Created .....FAIL");
		}

	}

}
