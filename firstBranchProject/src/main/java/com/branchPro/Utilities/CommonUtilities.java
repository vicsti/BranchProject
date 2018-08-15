package com.branchPro.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;


public class CommonUtilities {
	private static int count;
	private static WebElement we;
	
	public static int textOccurance(Iterator<WebElement> it, String findText) {
		count=0;
		while(it.hasNext()) {
			we = it.next();
			if (we.getText() == null)
				continue;
			if (we.getText().contains(findText))
				count++;
		}
		
		return count;
		
	}
	
	public static List<String> returnEmpOrDepList(List<WebElement> empOrDepList) {
		List<String> empList = new ArrayList<String>();
		
	    for (int i=0; i<empOrDepList.size(); i++) {
	    	empList.add(empOrDepList.get(i).getText());   
	      }
		
	 return empList;		
	}
	
	public static boolean checkNamesInTabs(List<String> empNamesList, List<String> empNamesTabList) {
		boolean flag= false;
		if (empNamesList.containsAll(empNamesTabList)) {
			flag = true;
		} else {
			flag = false;
		}
		
		return flag;
	}
	
	public static boolean checkDepNamesForEmps(HashMap<String, String> empNamesMap, HashMap<String, String> empNamesTabMap) {
		boolean flag= true;
		Iterator<String> it = empNamesTabMap.keySet().iterator();
		
		
		
		while(it.hasNext()) {
			String key=it.next();
			//System.out.println("HASH1: "+ empNamesMap.get(key).toString());
			//System.out.println("HASH2: "+ empNamesTabMap.get(key).toString());
			
			if (empNamesMap.get(key).toString().equalsIgnoreCase(empNamesTabMap.get(key).toString())) {
				continue;
			} else {
				flag=false;
			}
		}
		
		return flag;
	}
	
	public static HashMap<String, String> returnEmpDepMap(List<WebElement> empList, List<WebElement>  depList) {
		HashMap<String, String> empDepMap = new HashMap<String, String>();
		for (int i=0; i<empList.size(); i++) {
			empDepMap.put(empList.get(i).getText().trim(), depList.get(i).getText().trim());    
	      }
		
		return empDepMap;
	}

}
