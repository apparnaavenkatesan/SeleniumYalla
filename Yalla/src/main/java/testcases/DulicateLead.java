package testcases;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class DulicateLead extends Annotations  //Login extends sele base
{
	@Test(priority=1)
	public void duplicateLead() {
		System.out.println("DUPLICATE LEAD ");
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the Name to modify - ");
//		String name = sc.nextLine();
//		System.out.println("Enter Update Message - ");
//		String msg = sc.nextLine();
		String name = "TestFirstName"; 
		String msg = "DuplicateMsgHello!";
//		sc.close();
//		WebElement crmsfaLink = locateElement("link", "CRM/SFA");
//		click(crmsfaLink);
//		WebElement leadsTab = locateElement("link", "Leads");
//		click(leadsTab);
		WebElement findLeadTab = locateElement("link","Find Leads");
		click(findLeadTab);
		rest(3000);
		WebElement findLeadFN = locateElement("xpath", "(//input[@name='firstName'])[3]");
		clearAndType(findLeadFN, name);
		WebElement btnFindLeads = locateElement("xpath","//button[text()='Find Leads']");
		click(btnFindLeads);
		rest(3000);
		List<WebElement> rows1 = locateElements("xpath","//div[@class='x-grid3-cell-inner x-grid3-col-partyId']");
		int size1 = rows1.size();
		System.out.println("Row Count before Duplicate - " + size1);
		WebElement firstResult = locateElement("xpath","(//table[@class='x-grid3-row-table']//a)[1]");
		click(firstResult);
		rest(3000);
		takeSnap();
		WebElement dupBtn = locateElement("link","Duplicate Lead");
		click(dupBtn);
		WebElement updateNote = locateElement("xpath","//textarea[@id='createLeadForm_importantNote']");
		clearAndType(updateNote, msg);
		WebElement ownershipDD = locateElement("xpath","//select[@id='createLeadForm_ownershipEnumId']");
		selectDropDownUsingText(ownershipDD,"S-Corporation");
		WebElement createLeadBtn = locateElement("xpath", "//input[@name='submitButton' and @value='Create Lead']");
		click(createLeadBtn);
		rest(2000);
		WebElement findLeadTab1 = locateElement("link","Find Leads");
		click(findLeadTab1);
		rest(3000);
		WebElement findLeadFN1 = locateElement("xpath", "(//input[@name='firstName'])[3]");
		clearAndType(findLeadFN1, name);
		WebElement btnFindLeads1 = locateElement("xpath","//button[text()='Find Leads']");
		click(btnFindLeads1);
		rest(3000);
		takeSnap();
		  List<WebElement> rows2 = locateElements("xpath","//div[@class='x-grid3-cell-inner x-grid3-col-partyId']");
		  int size2 = rows2.size();
		  System.out.println("Row Count after Duplicate - " + size2);
		 if(size2>size1)
		System.out.println("Duplicate Success! - Pass");
		 else
		 System.err.println("Duplicate Fail! ");
		 
		
	}

	
	
}
