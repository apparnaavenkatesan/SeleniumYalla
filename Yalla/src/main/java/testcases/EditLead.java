package testcases;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class EditLead extends Login  //Login extends sele base
{
	@Test(groups = "sanity", dependsOnGroups="smoke")
	//	@Test(dependsOnMethods = {"testcases.CreateLead.createLead"}, timeOut=10000, priority=2, alwaysRun=true)
	public void editLead() {
		System.out.println("EDIT LEAD ");
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the Name to Edit - ");
//		String name = sc.nextLine();
//		sc.close();
		String name = "TestFirstName";
		login();
		WebElement crmsfaLink = locateElement("link", "CRM/SFA");
		click(crmsfaLink);
		WebElement leadsTab = locateElement("link", "Leads");
		click(leadsTab);
		WebElement findLeadTab = locateElement("link","Find Leads");
		getBackgroundColor(findLeadTab);
		click(findLeadTab);
		rest(3000);
		WebElement findLeadFN = locateElement("xpath", "(//input[@name='firstName'])[3]");
		clearAndType(findLeadFN, name);
		WebElement btnFindLeads = locateElement("xpath","//button[text()='Find Leads']");
		click(btnFindLeads);
		rest(3000);
		WebElement firstResult = locateElement("xpath","(//table[@class='x-grid3-row-table']//a)[1]");
		click(firstResult);
		rest(3000);
		takeSnap();
		WebElement editBtn = locateElement("link","Edit");
		click(editBtn);
		WebElement updateNote = locateElement("id","updateLeadForm_importantNote");
		clearAndType(updateNote, "Edited Note!");
		getTypedText(updateNote);
		WebElement ownershipDD = locateElement("xpath","//select[@id='updateLeadForm_ownershipEnumId']");
		selectDropDownUsingText(ownershipDD,"LLC/LLP");
		WebElement updateBtn = locateElement("xpath", "(//input[@name='submitButton'])[1]");
		click(updateBtn);
		rest(2000);
		takeSnap();
		WebElement updatedNotes = locateElement("id", "viewLead_importantNote_sp"); 
		verifyExactText(updatedNotes,"Updated Note!");
		rest(3000);
		closeApp();
		
		 
		
	}

	
	
}
