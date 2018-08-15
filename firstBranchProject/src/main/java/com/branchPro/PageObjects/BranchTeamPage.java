package com.branchPro.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BranchTeamPage {
	private WebDriver driver;
	private static By teamPic = By.xpath("//img[contains(@src,\"offsite\")]");
	private static By allEmpsTab = By.xpath("//a[@rel=\"all\"]");
	private static By dataEmpsTab = By.xpath("//a[@rel=\"data\"]");
	private static By engineeringEmpsTab = By.xpath("//a[@rel=\"engineering\"]");
	private static By marketingEmpsTab = By.xpath("//a[@rel=\"marketing\"]");
	private static By operationsEmpsTab = By.xpath("//a[@rel=\"operations\"]");
	private static By partnerGrowthEmpsTab = By.xpath("//a[@rel=\"partner-growth\"]");
	private static By productEmpsTab = By.xpath("//a[@rel=\"product\"]");
	private static By recruitingEmpsTab = By.xpath("//a[@rel=\"recruiting\"]");
	
	private static By allEmpsNames = By.xpath("//div[contains(@style,\"inline-block\")]/div/div/div[@class=\"info-block\"]/h2");
	private static By allEmpsDep = By.xpath("//div[contains(@style,\"inline-block\")]/div/div/div[@class=\"info-block\"]/h4");

	
	private static By allEmpsSet = By.xpath("//div[@class=\"info-block\"]/h4");
	private static By dataEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By engineeringEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By marketingEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By operationsEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By partnerGrowthEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By productEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	private static By recruitingEmpsSet = By.xpath("//div[class=\"info-block\"]/h2");
	
	
	
	public BranchTeamPage(WebDriver driver) {
		this.driver=driver;		
	}
	
	public ExpectedCondition<WebElement> teamPic() {
		return ExpectedConditions.visibilityOfElementLocated(teamPic);
	}
	
	public WebElement allEmpsTab() {
		
		return driver.findElement(allEmpsTab);
	}
	
	public WebElement dataEmpsTab() {
		
		return driver.findElement(dataEmpsTab);
	}
	
	public WebElement engineeringEmpsTab() {
		
		return driver.findElement(engineeringEmpsTab);
	}
	
	public WebElement marketingEmpsTab() {
		
		return driver.findElement(marketingEmpsTab);
	}
	
	public WebElement operationsEmpsTab() {
		
		return driver.findElement(operationsEmpsTab);
	}
	
	public WebElement partnerGrowthEmpsTab() {
		
		return driver.findElement(partnerGrowthEmpsTab);
	}
	
	public WebElement productEmpsTab() {
		
		return driver.findElement(productEmpsTab);
	}
	
	public WebElement recruitingEmpsTab() {
		
		return driver.findElement(recruitingEmpsTab);
	}
	
	public List<WebElement> allEmpsDep() {
		
		return driver.findElements(allEmpsDep);
	}
	
	public List<WebElement> allEmpsNames() {
		
		return driver.findElements(allEmpsNames);
	}
	
	public List<WebElement> allEmpsSet() {
		
		return driver.findElements(allEmpsSet);
	}
	
	public List<WebElement> dataEmpsSet() {
		
		return driver.findElements(dataEmpsSet);
	}
	
	public List<WebElement> engineeringEmpsSet() {
		
		return driver.findElements(engineeringEmpsSet);
	}
	
	public List<WebElement> marketingEmpsSet() {
		
		return driver.findElements(marketingEmpsSet);
	}
	
	public List<WebElement> operationsEmpsSet() {
		
		return driver.findElements(operationsEmpsSet);
	}
	
	public List<WebElement> partnerGrowthEmpsSet() {
		
		return driver.findElements(partnerGrowthEmpsSet);
	}
	
	public List<WebElement> productEmpsSet() {
		
		return driver.findElements(productEmpsSet);
	}
	
	public List<WebElement> recruitingEmpsSet() {
		
		return driver.findElements(recruitingEmpsSet);
	}

}
