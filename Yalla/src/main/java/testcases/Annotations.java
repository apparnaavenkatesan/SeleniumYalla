package testcases;

import org.testng.annotations.Test;

import com.yalla.selenium.api.base.ExcelDynamic;
import com.yalla.selenium.api.base.SeleniumBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotations extends SeleniumBase{
 
	
	
	
	@Parameters({"url","username","password"})
	
	@BeforeMethod
	
	 public void beforeMethod(String url, String username, String password) {
	  	System.out.println("***** Before Method! ");
	  	startApp("chrome", url);	
		WebElement eleUserName = locateElement("id", "username");
		clearAndType(eleUserName, username);
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, password);
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin);
		WebElement crmsfaLink = locateElement("link", "CRM/SFA");
		click(crmsfaLink);
  }
	
	
//  public void beforeMethod() {
//	  	System.out.println("***** Before Method! ");
//	  	startApp("chrome", "http://leaftaps.com/opentaps");	
//		WebElement eleUserName = locateElement("id", "username");
//		clearAndType(eleUserName, "DemoSalesManager");
//		WebElement elePassword = locateElement("id", "password");
//		clearAndType(elePassword, "crmsfa");
//		WebElement eleLogin = locateElement("class", "decorativeSubmit");
//		click(eleLogin);
//		WebElement crmsfaLink = locateElement("link", "CRM/SFA");
//		click(crmsfaLink);
//  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("***** After Method! ");
	  closeApp();
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("***** Before Class Method! ");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("***** After Class Method! ");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("***** Before Test Method! ");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("***** After Test Method! ");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("***** Before Suite Method! ");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("***** After Suite Method! ");
  }

}
