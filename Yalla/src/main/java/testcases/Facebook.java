package testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Facebook {
	
	int i =1;
	
	public void takeSnap(ChromeDriver driver) {
		try {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/Snap"+i+".png");
		FileUtils.copyFile(src, dest);
		i++;
		} catch (IOException e) {
			System.err.println("Snapshot cannot be taken! ");;
		}

	}	
	
		@Test
public void facebookTest() throws InterruptedException
{
		
	System.setProperty("webdriver.chrome.driver", "/Users/apparnaa/Desktop/chromedriver");
	ChromeOptions op = new ChromeOptions();
	op.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver(op);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get("https://www.facebook.com/");
	driver.manage().window().maximize();
	driver.findElementById("email").sendKeys("apparnaa_v@yahoo.co.in");
	driver.findElementById("pass").sendKeys("Hello@123");
	driver.findElementById("loginbutton").click();
	driver.findElementByXPath("//input[@placeholder='Search']").sendKeys("TestLeaf");
	Thread.sleep(3000);
	driver.findElementByXPath("//button[@data-testid='facebar_search_button']").click();
	WebElement testLeafResult = driver.findElementByXPath("//span[text()='Places']/following::div//a[@data-testid='browse-result-link']");
	if(testLeafResult.isDisplayed())
	{System.out.println("Test Leaf is Displayed! ");}
	else
		{ System.out.println("Test Leaf is Not Displayed! ");
		  driver.quit();
		}
	Thread.sleep(3000);
	WebElement likeBtn = driver.findElementByXPath("(//button[@type='submit'])[6]");
	String likeText = likeBtn.getText();
	takeSnap(driver);
	System.out.println("Text in Button: " + likeText);
	if(likeText.equalsIgnoreCase("like"))
	{
		likeBtn.click();
		System.out.println("Button Cliked! - Page Liked! ");
		Thread.sleep(3000);
	}
	else if(likeText.equalsIgnoreCase("liked"))
	{
		System.out.println("Page already Liked! ");
		
	}
	WebElement testLeafLink = driver.findElementByXPath("(//div[text()='TestLeaf'])[1]");
	testLeafLink.click();
	if(driver.getTitle().contains("Testleaf"))
			{System.out.println("Correct Page Landing! ");}
	else
		{System.out.println("Incorrect Page Landing! ");}
	String noOfLikes = driver.findElementByXPath("//div[@id='pages_side_column']//div[contains(text(),'people like this')]").getText();
	System.out.println("No of People who like this page : " + noOfLikes.replaceAll("[^\\d.]", ""));
	takeSnap(driver);
	driver.close();

		
}
	
}
