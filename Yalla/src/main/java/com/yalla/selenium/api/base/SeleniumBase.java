package com.yalla.selenium.api.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import com.yalla.selenium.api.design.Browser;
import com.yalla.selenium.api.design.Element;
 
 

public class SeleniumBase implements Browser, Element  {
	
	@DataProvider(name = "testDataProvider") //Dynamic
	public Object[][] fetchdata() throws IOException
	{
	Object[][] dynamicExcel = ExcelDynamic.dynamicExcel("testSheet");
	return dynamicExcel;

	}

	public RemoteWebDriver driver;
	WebDriverWait wait;
	int i=1;
	Alert alert;
	
	@Override
	public void click(WebElement ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println("The Element "+ele+" clicked");
		} catch (StaleElementReferenceException e) {
			System.err.println("The Element "+ele+" could not be clicked");
			throw new RuntimeException();
		}

	}
	
	
	@Override
	public void clickWithScreenshot(WebElement ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println("The Element "+ele+" clicked");
			File src = driver.getScreenshotAs(OutputType.FILE);
			File dest = new File("./snaps/snap"+i+".png");
			FileUtils.copyFile(src, dest);
			i++;
			
		} catch (StaleElementReferenceException e) {
			System.err.println("The Element "+ele+" could not be clicked");
			throw new RuntimeException();
		} catch (Exception e) {
			System.err.println("Cannot take Screenshots! ");
			throw new RuntimeException();
		}

	}

	@Override
	public void append(WebElement ele, String data) {
		try {
			ele.sendKeys(data);
		} catch (ElementNotInteractableException e) {
			System.err.println("Exception Caught! ");
		}catch (IllegalArgumentException e) {
			System.err.println("Exception Caught! ");
		}

	}

	@Override
	public void clear(WebElement ele) {
	  try {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		  ele.clear();
	} catch (InvalidElementStateException e) {
		System.err.println("Unable to locate Element!" + ele);
	}

	}
 
	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("The Data :"+data+" entered Successfully");
		} catch (ElementNotInteractableException e) {
			System.err.println("The Element "+ele+" is not Interactable");
			throw new RuntimeException();
		}

	}

	@Override
	public String getElementText(WebElement ele) { 
		 String text = ele.getText(); 
		 System.out.println("Element Text is: " + text);
		 return text;
	}

	public String getBackgroundColor(WebElement ele) {
		return ele.getCssValue("background-color");
	}

	@Override
	public String getTypedText(WebElement ele) {
		String typedtxt = ele.getText();
		System.out.println("Typed Text is :" + typedtxt );
		return typedtxt;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			click(ele);
			Select sel = new Select(ele);
			sel.selectByVisibleText(value);
			System.out.println("Dropdown value for webelement " + ele + " selected with value " + value);
		} catch (NoSuchElementException e) {
			System.err.println("The Element " + ele + "is not present !");
			throw new RuntimeException();
		}
		
		
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
	try {
		ele.click();
		 Select sel = new Select(ele);
		 sel.selectByIndex(index);
		 System.out.println("Dropdown value for webelement " + ele + " selected with index " + index);
	} catch (NoSuchElementException e) {
		System.err.println("The Element " + ele + "is not present !");
		throw new RuntimeException();
	}

	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		try {
			ele.click();
			 Select sel = new Select(ele);
			 sel.selectByValue(value);
			 System.out.println("Dropdown value for webelement " + ele + " selected with value " + value);
		} catch (NoSuchElementException e) {
			System.err.println("The Element " + ele + "is not present !");
			throw new RuntimeException();
		}

	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		String text = ele.getText();
		System.out.println("Expected Text: " + expectedText);
		System.out.println("Text from Element : " + text);
		if(text.compareToIgnoreCase(expectedText)!=0)
		{System.err.println("Test Mismatch! "); return false; }
		else
		{System.out.println("Text matching - Test Pass! "); return true; }
			
		
		
	}
	
	public boolean compareTexts(String a, String b)
	{
		if(a.compareToIgnoreCase(b)==0)
		{
			System.out.println("Matching Texts! ");
			return true;
		}
		else
			{System.out.println("Texts Not Matching! ");
			return false;
			}
		
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		String eleText = ele.getText();
		if(eleText.contains(expectedText))
		{
			System.out.println("Element contains the text! ");
			return true;
		}
		else
		{
			System.out.println("Element Does Not contain the text! ");
			return false;
		}
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
	String val = ele.getAttribute(attribute);
			if (val.equals(value))
				return true;
			else
				return false;
		
		
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		String val = ele.getAttribute(attribute);
		if (val.contains(value))
			System.out.println("Pass");
		else
			System.err.println("Fail");

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		boolean displayed = ele.isDisplayed();
		return displayed;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		boolean displayed = ele.isDisplayed();
		return displayed;
	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		return ele.isEnabled();
	}

	@Override
	public boolean verifySelected(WebElement ele) {
		boolean selected = ele.isSelected();
		return selected;
	}

	@Override
	public void startApp(String url) {
		driver.get(url);

	}

	@Override
	public void startApp(String browser, String url) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"/Users/apparnaa/Desktop/chromedriver");
				driver = new ChromeDriver();
			} else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						"./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.err.println("The Browser Could not be Launched. Hence Failed");
			throw new RuntimeException();
		}

	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElementById(value);
			case "name": return driver.findElementByName(value);
			case "class": return driver.findElementByClassName(value);
			case "link": return driver.findElementByLinkText(value);
			case "xpath": return driver.findElementByXPath(value);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("The Element with locator:"+locatorType+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		try {
			return driver.findElementById(value);
		} catch (NoSuchElementException e) {
			System.err.println("The Element is Not Found with ID: " + value);
			throw new RuntimeException();
		} finally {
			takeSnap();
		}
	
	}
	


	@Override
	public List<WebElement> locateElements(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElementsById(value);
			case "name": return driver.findElementsByName(value);
			case "class": return driver.findElementsByClassName(value);
			case "link": return driver.findElementsByLinkText(value);
			case "xpath": return driver.findElementsByXPath(value);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.err.println("The Element with locator:"+locatorType+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public void switchToAlert() {
try {
		alert = driver.switchTo().alert();
} catch (NoAlertPresentException e) {
	System.err.println("No Alert Present! ");
}
	}

	@Override
	public void acceptAlert() {
		try {
			switchToAlert();
		alert.accept();
	} catch (NoAlertPresentException e) {
		System.err.println("No Alert Present! ");
	}

	}

	@Override
	public void dismissAlert() {
		try {
			switchToAlert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.err.println("No Alert Present! ");
		}

	}

	@Override
	public String getAlertText() {
		try {
		switchToAlert();
		String text = alert.getText();
		System.out.println("Alert Text - " + text);
		return text;
		}
		catch(NoAlertPresentException e) {
			System.err.println("No Alert Present! ");
			return null;
		}
	}
	

	@Override
	public void typeAlert(String data) {
		try {
			switchToAlert();
			alert.sendKeys(data);
			System.out.println("Data " + data + "typed in AlertBox!" );
			}
			catch(NoAlertPresentException e) {
				System.err.println("No Alert Present! ");
			}

	}

	@Override
	public void switchToWindow(int index) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowlst = new ArrayList<>(windowHandles);
		driver.switchTo().window(windowlst.get(index));
			

	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> windowlst = new ArrayList<>(allWindows);
			for (String eachWindow : windowlst) {
				if (eachWindow.equalsIgnoreCase(title)) {
					driver.switchTo().window(eachWindow);
					System.out.println("Switched to window: " + eachWindow);
				}

				else
					continue;
			}
		} catch (NoSuchWindowException e) {
			System.err.println("Window with the title: " + title + "is not available");
		} 

	}

	@Override
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			System.out.println("Switched to frame successfully");
		} catch (NoSuchFrameException e) {
			System.err.println("No Frame Available! ");
		}

	}

	@Override
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			System.out.println("Switched to frame successfully");
		} catch (NoSuchFrameException e) {
			System.err.println("No Frame Available! ");
		}
		catch (StaleElementReferenceException e) {
			System.err.println("No Frame Available! "); }

	}


	@Override
	public void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			System.out.println("Switched to frame successfully");
		} catch (NoSuchFrameException e) {
			System.err.println("No Frame Available! ");
		}
	}

	@Override
	public void defaultContent() {
		try {
			driver.switchTo().parentFrame();
			System.out.println("Switched to Parent Frame!");
		} catch (Exception e) {
			System.err.println("No Frame Available!");
		}

	}

	@Override
	public boolean verifyUrl(String url) {
		if (driver.getCurrentUrl().contentEquals(url))
		{
			System.out.println("URL Matches!");
			return true; 
		}
		else
		{	
		System.err.println("URL does not match! ");	
		return false;
		}
	}

	@Override
	public boolean verifyTitle(String title) {
		if (driver.getTitle().contentEquals(title))
		{
			System.out.println("Title Matches!");
			return true; 
		}
		else
		{	
		System.err.println("Title does not match! ");	
		return false;
		}
	}

	@Override
	public void takeSnap() {
		try {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/snap"+i+".png");
		FileUtils.copyFile(src, dest);
		i++;
		} catch (IOException e) {
			System.err.println("Snapshot cannot be taken! ");;
		}

	}

	@Override
	public void closeApp() {
		driver.close();
	}

	@Override
	public void quit() {
		driver.quit();

	}

	@Override
	public void dropdownGetOptions(WebElement ele) {
		try {
			Select sel = new Select(ele);
			List<WebElement> ddOptions = sel.getOptions();
			System.out.println("Printing all values in the DropDown : ");
			for(WebElement eachele : ddOptions)
			{
				System.out.println(eachele.getText());
			}
		} catch (Exception e) {
			System.err.println("DropDown Error! ");
			throw new RuntimeException();
		}
	}


	@Override
	public boolean rest(int num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			System.err.println("Cannot stop Thread! ");
		}
		return false;
	}
}
