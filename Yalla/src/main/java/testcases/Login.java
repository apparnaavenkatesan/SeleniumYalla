package testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.yalla.selenium.api.base.SeleniumBase;

public class Login extends SeleniumBase
{
	 
	public void login() {
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement eleUserName = locateElement("id", "username");
		clearAndType(eleUserName, "DemoSalesManager");
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, "crmsfa");
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin);
	}
	
}







