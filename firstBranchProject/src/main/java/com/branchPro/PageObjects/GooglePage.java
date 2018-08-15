package com.branchPro.PageObjects;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
	private WebDriver driver;
	private static By googleSearchBox = By.xpath("//input[@name=\"q\"]");
	private static By searchResultPage = By.xpath("//div[@id=\"resultStats\"]");
	private static By branchPage = By.xpath("//span[@id=\"logo\"]");
	private static By href = By.tagName("a");
	
	public GooglePage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public WebElement search() {
	
		return driver.findElement(googleSearchBox);
	}
	
	public ExpectedCondition<WebElement> searchResultPage() {
		return ExpectedConditions.visibilityOfElementLocated(searchResultPage);
	}
	
	public WebElement branchLink() {
		WebElement link=null;
		Iterator<WebElement> it = driver.findElements(href).iterator();
		while(it.hasNext()) {
			link = it.next();
			if (link.getAttribute("href") == null)
				continue;
		  	if (link.getAttribute("href").contains("branch.io")) {
				break;
			}
		}
		return link;
	}
	
	public ExpectedCondition<WebElement> branchPage() {
		return ExpectedConditions.visibilityOfElementLocated(branchPage);		
	}

}
