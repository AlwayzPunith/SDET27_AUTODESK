package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContactLookUpImg;
	
	@FindAll({@FindBy(name="search_text"),@FindBy(xpath = "//input[@class='txtBox']")})
	private WebElement searchtextEdt;
	
	@FindBy(name="submit")
	private WebElement searchNowBtn;
	
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateContactLookUpImg() {
		return createContactLookUpImg;
	}

	public WebElement getSearchtextEdt() {
		return searchtextEdt;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	
	//Business Library
	public void clickOnCreateContactImg()
	{
		createContactLookUpImg.click();
	}
	
}

