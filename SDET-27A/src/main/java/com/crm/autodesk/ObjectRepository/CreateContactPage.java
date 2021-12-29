package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericUtility.WebDriverUtility;

public class CreateContactPage  extends WebDriverUtility{
	
	@FindBy(name="lastname")
	private WebElement contactlastnameEdt;
	
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement orgnameLookUpImg;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(id="search_txt")
	private WebElement orgSearchTextEdt;
	
	public WebElement getOrgSearchTextEdt() {
		return orgSearchTextEdt;
	}

	public WebElement getOrgSearchBtn() {
		return orgSearchBtn;
	}

	@FindBy(name="search")
	private WebElement orgSearchBtn;
	
	//Initialization
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getcontactlastnameEdt() {
		return contactlastnameEdt;
	}

	public WebElement getOrgnameLookUpImg() {
		return orgnameLookUpImg;
	}

	public WebElement getSaveBtn() 
	{
		return saveBtn;
	}

	//Business for create Contact
	public void createContact(String lastName)
	{
		contactlastnameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createContactWithOrg(WebDriver driver,String lastName,String orgName)
	{
		contactlastnameEdt.sendKeys(lastName);
		orgnameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		orgSearchTextEdt.sendKeys(orgName);
		orgSearchBtn.click();
		driver.findElement(By.linkText(orgName)).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
	}
}
