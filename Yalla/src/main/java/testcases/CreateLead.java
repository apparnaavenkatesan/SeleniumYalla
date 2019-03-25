package testcases;

import java.io.IOException;

import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.ExcelDynamic;
import com.yalla.selenium.api.base.SeleniumBase;

public class CreateLead extends Annotations  //Login extends sele base
{
	

	
	@Test(groups = "smoke", dataProvider="testDataProvider")
	
	//	@Test(invocationCount=2, priority=3) 

	public void createLeadtest(String companyName, String firstname, String lastname)  {
		System.out.println("CREATE LEAD ");
		WebElement createLead = locateElement("link", "Create Lead");
		click(createLead);
		WebElement compName = locateElement("xpath", "//input[@id='createLeadForm_companyName']");
		clearAndType(compName, companyName);
		WebElement frstName = locateElement("xpath", "//input[@id='createLeadForm_firstName']");
		clearAndType(frstName, firstname);
		WebElement lstName = locateElement("xpath", "//input[@id='createLeadForm_lastName']");
		clearAndType(lstName, lastname);
		 
		
		
		
//		WebElement compName = locateElement("xpath", "//input[@id='createLeadForm_companyName']");
//		clearAndType(compName, "TestCompanyABC");
//		WebElement frstName = locateElement("xpath", "//input[@id='createLeadForm_firstName']");
//		clearAndType(frstName, "TestFirstName");
//		WebElement lstName = locateElement("xpath", "//input[@id='createLeadForm_lastName']");
//		clearAndType(lstName, "TestLastName");
		WebElement sourceDD = locateElement("xpath", "//select[@id='createLeadForm_dataSourceId']");
		dropdownGetOptions(sourceDD);
		selectDropDownUsingText(sourceDD, "Self Generated");
		WebElement submitBtn = locateElement("xpath", "//input[@class='smallSubmit']");
		WebElement marketDD = locateElement("id", "createLeadForm_marketingCampaignId");
		selectDropDownUsingIndex(marketDD, 3);
		WebElement titleText = locateElement("id", "createLeadForm_generalProfTitle");
		clickWithScreenshot(titleText);
		clearAndType(titleText, "TextEntered");
		click(submitBtn);
		WebElement findLeadTab = locateElement("link","Find Leads");
		click(findLeadTab);
		rest(3000);
		WebElement findLeadFN = locateElement("xpath", "(//input[@name='firstName'])[3]");
		clearAndType(findLeadFN, firstname);
		WebElement btnFindLeads = locateElement("xpath","//button[text()='Find Leads']");
		click(btnFindLeads);
		rest(3000);
		WebElement firstFindResult = locateElement("xpath","//div[@class='x-paging-info']");
		boolean resultboolean = verifyExactText(firstFindResult, "No records to display");
		System.out.println("Result Boolean = " + resultboolean);
		if (resultboolean == false)
			System.out.println("Test Pass - Lead Created! ");
		
		else
			System.out.println("Lead Not Created! ");
		rest(3000);
		takeSnap();
		}
	
	
//	@DataProvider(name = "testDataProvider") //Hardcoded values 
//	public Object[][] fetchdata()
//	{
//	Object[][] data = new Object[2][3]; 
//	data[0][0]="AutomationCompany";
//	data[0][1]="AutomationFirstName";
//	data[0][2]="AutomationLastName";
//	data[1][0]="TestCompany123";
//	data[1][1]="TestFirstName123";
//	data[1][2]="LastFirstName123";
//	return data;
//	}
	
	
	
	
	
}
