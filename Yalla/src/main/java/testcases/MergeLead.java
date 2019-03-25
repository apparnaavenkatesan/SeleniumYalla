package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class MergeLead extends Login  //Login extends sele base
{
 
	@Test(groups = "regression")
//	@Ignore
//	@Test(enabled=false)
	public void MergeLeadTest() {
		System.out.println("MERGE LEAD ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter names to merge - ");
		String fn1 = sc.nextLine();
		String fn2 = sc.nextLine();
		sc.close();
		login();
		WebElement crmsfaLink = locateElement("link", "CRM/SFA");
		click(crmsfaLink);
		WebElement leadsTab = locateElement("link", "Leads");
		click(leadsTab);
		WebElement eleMergeBtn = locateElement("link", "Merge Leads");
		click(eleMergeBtn);
		WebElement fromBtn = locateElement("xpath", "(//img[@src='/images/fieldlookup.gif'])[1]");
		click(fromBtn);
		switchToWindow(1);
		WebElement firstName1 = locateElement("xpath","//input[@name='firstName']");
		clearAndType(firstName1, fn1);
		WebElement findLeadsBtn = locateElement("xpath","//button[text()='Find Leads']");
		click(findLeadsBtn);
		rest(3000);
		WebElement firstResult = locateElement("xpath","(//table[@class='x-grid3-row-table']//a)[1]");
		String no1 = firstResult.getText();
		click(firstResult);
		switchToWindow(0);
		WebElement toBtn = locateElement("xpath", "(//img[@src='/images/fieldlookup.gif'])[2]");
		click(toBtn);
		switchToWindow(1);
		WebElement firstname2 = locateElement("xpath","//input[@name='firstName']");
		clearAndType(firstname2, fn2);
		takeSnap();
		WebElement findLeadsBtnto = locateElement("xpath","//button[text()='Find Leads']");
		click(findLeadsBtnto);
		rest(3000);
		WebElement firstResultTo = locateElement("xpath","(//table[@class='x-grid3-row-table']//a)[1]");
		String no2 = firstResultTo.getText();
		click(firstResultTo);
		switchToWindow(0);
		WebElement mergebtn = locateElement("link","Merge");
		click(mergebtn);
		rest(3000);
		switchToAlert();
		acceptAlert(); 
		WebElement findLeadTab = locateElement("link","Find Leads");
		click(findLeadTab);
		rest(3000);
		WebElement findLeadFN = locateElement("xpath", "//input[@name='id']");
		clearAndType(findLeadFN, no1);
		WebElement btnFindLeads = locateElement("xpath","//button[text()='Find Leads']");
		click(btnFindLeads);
		rest(3000);
		WebElement firstFindResult = locateElement("xpath","//div[@class='x-paging-info']");
		verifyExactText(firstFindResult, "No records to display");
		rest(3000);
		takeSnap();
		closeApp();
		
		
		
		
		
		
	}

	
	
}


