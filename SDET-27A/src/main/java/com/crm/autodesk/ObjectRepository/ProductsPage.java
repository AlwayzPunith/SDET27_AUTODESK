package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage {
	
	@FindBy(id = "viewname")
	private WebElement productFilterlist;
	
	@FindBy(linkText = "Create Filter")
	private WebElement createFilterLnk;
	
	@FindBy(linkText = "Edit")
	private WebElement editLnk;
	
	@FindBy(linkText = "Delete")
	private WebElement deleteLnk;

	public WebElement getProductFilterlist() {
		return productFilterlist;
	}

	public WebElement getCreateFilterLnk() {
		return createFilterLnk;
	}

	public WebElement getEditLnk() {
		return editLnk;
	}

	public WebElement getDeleteLnk() {
		return deleteLnk;
	}
	
	

}
