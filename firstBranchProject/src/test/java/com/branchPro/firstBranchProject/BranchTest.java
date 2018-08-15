package com.branchPro.firstBranchProject;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.branchPro.PageObjects.BranchHomePage;
import com.branchPro.PageObjects.BranchTeamPage;
import com.branchPro.PageObjects.GooglePage;
import com.branchPro.Utilities.CommonUtilities;
import com.branchPro.Utilities.CreateDriverObj;



public class BranchTest {
	
	public WebDriver driver; 
	private Properties pro;
	private static Logger log =LogManager.getLogger(BranchTest.class.getName());
	private WebDriverWait wait;
	
	@BeforeTest
	public void loadParams() throws IOException {
		pro = new Properties();
		String absoluteFilePath = System.getProperty("user.dir")+ File.separator +"suiteParams.properties";
		System.out.println("absoluteFilePath: "+ absoluteFilePath);
		FileInputStream fin = new FileInputStream(absoluteFilePath);
		pro.load(fin);
				
	}
	
	
	//Create a driver object, set implicit Wait and pagetimeOuts, delete cookies, 
	//hit the base url and navigate to branch website
	@BeforeMethod
	public void hitBaseUrl() {
		driver = CreateDriverObj.getDriver(pro);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		log.info("LAUNCH THE BROWSER AND OPEN BASE URL "+pro.getProperty("baseUrl"));
		driver.get(pro.getProperty("baseUrl")); 
		driver.manage().window().maximize();
		log.info("THE BROWSER IS LAUNCHED ");
		
		GooglePage google = new GooglePage(driver);
		google.search().sendKeys("branch", Keys.ENTER);
		
		wait = new WebDriverWait(driver, 10);
		wait.until(google.searchResultPage());
		
		google.branchLink().click();
		wait.until(google.branchPage());
		
		BranchHomePage branchHome = new BranchHomePage(driver);
		if (branchHome.teamLink() == null) {
			log.error("THE TEAM LINK NOT FOUND on BRANCH HOME PAGE");			
		} else {
			log.info("CLICK ON THE TEAM LINK");
			if (driver.findElement(By.id("CybotCookiebotDialogBodyButtonAccept")).isDisplayed())
				driver.findElement(By.id("CybotCookiebotDialogBodyButtonAccept")).click();				
			branchHome.teamLink().click();

	    }
	}
	
	
	//Verify that number of employees match between All tab and sum of other tabs.
	@Test
	public void Test1() {
		int allEmpCount=0;
		int dataEmpCount=0;
		int engineeringEmpCount=0;
		int marketingEmpCount=0;
		int operationsEmpCount=0;
		int partnerGrowthEmpCount=0;
		int recrutingEmpCount=0;
		int productEmpCount=0;
		int founders=3;
		Iterator<WebElement> it = null;
		WebElement we=null;
		
		BranchTeamPage branchTeamPage = new BranchTeamPage(driver);
		wait = new WebDriverWait(driver, 5);
		wait.until(branchTeamPage.teamPic());
		log.info("TEAM LINK APPEARED");
		
		//Click on all Emp Tab and get the EMP count
		log.info("CLICK ON ALL EMP TAB");
		branchTeamPage.allEmpsTab().click();
		
		allEmpCount = branchTeamPage.allEmpsSet().size();
		log.info("COUNT OF EMPs IN THE ALL TAB: "+allEmpCount);
		
		//Click on data Emp Tab and get the EMP count
		branchTeamPage.dataEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		dataEmpCount=CommonUtilities.textOccurance(it, "Data");
				
		//Click on Eng Emp Tab and get the EMP count
		branchTeamPage.engineeringEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		engineeringEmpCount=CommonUtilities.textOccurance(it, "Engineering");
		
		//Click on Marketing Emp Tab and get the EMP count
		branchTeamPage.marketingEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		marketingEmpCount=CommonUtilities.textOccurance(it, "Marketing");
		
		//Click on Operations Emp Tab and get the EMP count
		branchTeamPage.operationsEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		operationsEmpCount=CommonUtilities.textOccurance(it, "Operations");
		
		//Click on Partner Growth Emp Tab and get the EMP count
    	branchTeamPage.partnerGrowthEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		partnerGrowthEmpCount=CommonUtilities.textOccurance(it, "Partner Growth");
		
		//Click on Recruting Emp Tab and get the EMP count
		branchTeamPage.recruitingEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		recrutingEmpCount=CommonUtilities.textOccurance(it, "Recruiting");
		
		//Click on Product Emp Tab and get the EMP count
		branchTeamPage.productEmpsTab().click();
		it = branchTeamPage.allEmpsSet().iterator();
		productEmpCount=CommonUtilities.textOccurance(it, "Product");
	
		log.info("COUNT OF EMPs IN THE FOUNDER TAB: "+founders);
		log.info("COUNT OF EMPs IN THE PARTNER GROWTH TAB: "+partnerGrowthEmpCount);
		log.info("COUNT OF EMPs IN THE DATA TAB: "+dataEmpCount);
		log.info("COUNT OF EMPs IN THE ENGINEERING TAB: "+engineeringEmpCount);
		log.info("COUNT OF EMPs IN THE MARKETING TAB: "+marketingEmpCount);
		log.info("COUNT OF EMPs IN THE OPERATIONS TAB: "+operationsEmpCount);
		log.info("COUNT OF EMPs IN THE RECRUITING TAB: "+recrutingEmpCount);
		log.info("COUNT OF EMPs IN THE PRODUCT TAB: "+productEmpCount);
		
		assertEquals(founders+partnerGrowthEmpCount+dataEmpCount+engineeringEmpCount+marketingEmpCount+operationsEmpCount+recrutingEmpCount+productEmpCount, allEmpCount);
		}
	
	//Verify that employee names match between All tab and other tabs.
	@Test
	public void Test2() {
		List<WebElement> empAllNames=null;
		List<WebElement> empNames=null;
		List<String> empNamesList=null;
		List<String> empNamesTabList=null;
		
		BranchTeamPage branchTeamPage = new BranchTeamPage(driver);
		wait = new WebDriverWait(driver, 5);
		wait.until(branchTeamPage.teamPic());
		log.info("TEAM LINK APPEARED");
		
		//Click on all Emp Tab and get the emp name's list.
		log.info("CLICK ON ALL EMP TAB");
		branchTeamPage.allEmpsTab().click();
		empAllNames=branchTeamPage.allEmpsNames();
		empNamesList=CommonUtilities.returnEmpOrDepList(empAllNames);
		
		//Click on data Emp Tab and get the emp names's list.
		branchTeamPage.dataEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL DATA EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on data Engineering Tab and get the emp names's list.
		branchTeamPage.engineeringEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL ENGINEERING EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on data Marketing Tab and get the emp names's list.
		branchTeamPage.marketingEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL MARKETING EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on operations Tab and get the emp names's list.
		branchTeamPage.operationsEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL OPERATIONS EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on product growth Tab and get the emp names's list.
		branchTeamPage.partnerGrowthEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL PRODUCT GROWTH EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on recruiting Tab and get the emp names's list.
		branchTeamPage.recruitingEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL RECRUITING EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}
		
		//Click on Product Tab and get the emp names's list.
		branchTeamPage.productEmpsTab().click();
		empNames=branchTeamPage.allEmpsNames();
		empNamesTabList=CommonUtilities.returnEmpOrDepList(empNames);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkNamesInTabs(empNamesList, empNamesTabList)) {
			log.info("ALL PRODUCT EMP NAMES MATCH WITH THE NAMES IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF NAMES");
			Assert.assertTrue(false);
		}		
	}
	
	//Verify that employee departments are listed correctly between All tab and Department tabs.
	@Test
	public void Test3() {
		List<WebElement> empAllNames=null;
		List<WebElement> DepAllNames=null;
		List<WebElement> empNamesTabWise =null;
		List<WebElement> depNamesTabWise=null;
		HashMap<String, String> empNamesMap=null;
		HashMap<String, String> empNamesTabMap=null;
		
		BranchTeamPage branchTeamPage = new BranchTeamPage(driver);
		wait = new WebDriverWait(driver, 5);
		wait.until(branchTeamPage.teamPic());
		log.info("TEAM LINK APPEARED");
		
		//Click on all Emp Tab and get the emp name's list.
		log.info("CLICK ON ALL EMP TAB");
		branchTeamPage.allEmpsTab().click();
		empAllNames=branchTeamPage.allEmpsNames();
		DepAllNames=branchTeamPage.allEmpsDep();
		empNamesMap=CommonUtilities.returnEmpDepMap(empAllNames, DepAllNames);
		
		//Click on data Emp Tab and get the emp names's list.
		branchTeamPage.dataEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL DATA EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on data Engineering Tab and get the emp names's list.
		branchTeamPage.engineeringEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL ENGINEERING EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on data Marketing Tab and get the emp names's list.
		branchTeamPage.marketingEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL MARKETING EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on operations Tab and get the emp names's list.
		branchTeamPage.operationsEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL OPERATIONS EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on product growth Tab and get the emp names's list.
		branchTeamPage.partnerGrowthEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL PRODUCT GROWTH EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on recruiting Tab and get the emp names's list.
		branchTeamPage.recruitingEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL RECRUITING EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
		//Click on Product Tab and get the emp names's list.
		branchTeamPage.productEmpsTab().click();
		empNamesTabWise=branchTeamPage.allEmpsNames();
		depNamesTabWise=branchTeamPage.allEmpsDep();
		empNamesTabMap=CommonUtilities.returnEmpDepMap(empNamesTabWise, depNamesTabWise);
		//Check if the names in both the tabs match
		if (CommonUtilities.checkDepNamesForEmps(empNamesMap, empNamesTabMap)) {
			log.info("ALL PRODUCT EMP DEPARTMENTS MATCH WITH THE DEPARTMENT IN THE ALL TAB: ");
			Assert.assertTrue(true);			
		} else {
			log.info("THERE IS A MISSMATCH OF DEPARTMENT NAMES FOR EMP: ");
			Assert.assertTrue(false);
		}
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@AfterTest
	public void cleanUp() {
		//If any Cleanup tasks required, after running the test suite.
		
	}

}
