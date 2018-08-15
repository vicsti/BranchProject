package com.branchPro.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BranchHomePage {
	private WebDriver driver;
	private static By teamLink = By.xpath("//a[@data-element-tag=\"team\"]");
	
	public BranchHomePage(WebDriver driver) {
		this.driver=driver;		
	}
	
	public WebElement teamLink() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		if (driver.findElements(teamLink).size() > 0) {
		while (true) {
		if (!driver.findElement(teamLink).isDisplayed()) {
			js.executeScript("window.scrollBy(0,250)", "");			
		} else {
			break;			
		}
		
	    }
		} else {
			return null;
		}
		return driver.findElement(teamLink);
	}
	

}
