package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility{//Step 1: Create a seperate calss for web page
	
	//Step 2: iDentify all webelemnts and Declare them as Private
	@FindBy (linkText = "Organizations")
	private WebElement organizationlnk;
	
	@FindBy (linkText = "Contacts")
	private WebElement contactslnk;
	
	@FindBy (linkText = "Opportunities")
	private WebElement opportunitieslnk;
	
	@FindBy (linkText = "Products")
	private WebElement productslnk;
	
	@FindBy (linkText = "Documents")
	private WebElement documentslnk;
	
	@FindBy (linkText = "Email")
	private WebElement emaillnk;
	
	@FindBy (linkText = "Trouble Tickets")
	private WebElement troubleticketslnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutlnk;
	
	//Step 3:Initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Step 4:Utilization

	public WebElement getOrganizationlnk() {
		return organizationlnk;
	}

	public WebElement getContactslnk() {
		return contactslnk;
	}

	public WebElement getOpportunitieslnk() {
		return opportunitieslnk;
	}

	public WebElement getProductslnk() {
		return productslnk;
	}

	public WebElement getDocumentslnk() {
		return documentslnk;
	}

	public WebElement getEmaillnk() {
		return emaillnk;
	}

	public WebElement getTroubleticketslnk() {
		return troubleticketslnk;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutlnk() {
		return signOutlnk;
	}
	
	
	//Business Library to Click on Organizations
	public void clickOnOraganizationsLnk()
	{
		organizationlnk.click();
	}

	//Business Library to Click on Contacts
	public void clickOnContactsLnk()
	{
		contactslnk.click();
	}
	
	//Business Library to Click on Contacts
		public void clickOnProductLnk()
		{
			productslnk.click();
		}
		
	//Business Library for logout
	public void logout(WebDriver driver)
	{
		mouseOverElement(driver, adminImg);
		signOutlnk.click();
	}
	
}	
	
	
	

