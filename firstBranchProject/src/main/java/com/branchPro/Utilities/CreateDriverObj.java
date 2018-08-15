package com.branchPro.Utilities;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Hello world!
 *
 */
public class CreateDriverObj 
{
    public static WebDriver getDriver( Properties pro )
    {
    	WebDriver driver;
    	int browserInt=0;
    	if (pro.getProperty("driver").contains("chrome"))
    		browserInt=1;
    	else if (pro.getProperty("driver").contains("firefox"))
    		browserInt=2;
    	else if (pro.getProperty("driver").contains("IE"))
    		browserInt=3;
    		
    		
    		
		switch(browserInt){
		
		case 1:
			System.setProperty("webdriver.gecko.driver", pro.getProperty("chromeDriverRootPath"));
			driver = new FirefoxDriver();
			break;
		case 2:
			System.setProperty("webdriver.chrome.driver", pro.getProperty("firefoxDriverRootPath"));
			driver = new ChromeDriver();
			break;
			
		case 3:
			System.setProperty("webdriver.ie.driver", pro.getProperty("IeDriverRootPath"));
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Choose a chrome browser as Default");
			System.setProperty("webdriver.chrome.driver", pro.getProperty("chromeDriverRootPath"));
			driver = new ChromeDriver();
			break;
			
			
			
		}
		return driver;
    }
}
