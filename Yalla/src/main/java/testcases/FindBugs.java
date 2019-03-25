package testcases;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class FindBugs {

	
	public static void main(String[] args) throws InterruptedException {      //Static  missin

		// launch the browser
		System.setProperty("webdriver.chrome.driver","/Users/apparnaa/Desktop/chromedriver");   // Case sensitivity of set property
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(op); //added to handle notification
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize(); //maximize
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //added wait

		// Mouse Over on Men
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("Men")).build().perform(); //missing build()

		// Click on Jackets
		
		driver.findElementByXPath("//a[text()='Jackets & Coats']").click(); //changed xpath


		// Find the count of Jackets
		String leftCount = driver.findElementByXPath("//span[@class='title-count']").getText().replaceAll("[^\\d.]", ""); //changed expression
		System.out.println(leftCount);


		// Click on Jackets and confirm the count is same
		driver.findElementByXPath("//label[text()='Jackets']").click(); //changed xpath

		// Wait for some time
		Thread.sleep(5000);

		// Check the count
		
		String rightCount = driver.findElementByXPath("//span[@class='title-count']").getText().replaceAll("[^\\d.]", ""); //changed expression, xpath
		System.out.println(rightCount);



		// If both count matches, say success
		if(leftCount.equals(rightCount)) {
			System.out.println("Success - The count matches on either case"); //added success
		}else {
			System.err.println("Fail - The count does not match");
		}

		// Click on Offers
		//driver.findElementByXPath("//h3[text()='Offers']").click(); //cant find offers

		// Find the costliest Jacket
		List<WebElement> productsPrice = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		List<String> onlyPrice = new ArrayList<String>();

		for (WebElement productPrice : productsPrice) { 
			onlyPrice.add(productPrice.getText().replaceAll("[^\\d.]", "")); //reg exp
		}

		// Sort them 
		Collections.sort(onlyPrice); //sorted
		String max = Collections.max(onlyPrice);

		// Find the top one
		System.out.println(max);
		
		driver.close();

		// Print Only Allen Solly Brand Minimum Price
		driver.findElementByXPath("//input[@value='Allen Solly']/following:sibling::div").click();

		// Find the costliest Jacket
		List<WebElement> allenSollyPrices = driver.findElementsByXPath("//span[@class='product-discountedPrice']");

		onlyPrice = new ArrayList<String>();

		for (WebElement productPrice : productsPrice) {
			onlyPrice.add(productPrice.getText().replaceAll("//D", ""));
		}

		// Get the minimum Price 
		String min = Collections.min(onlyPrice);

		// Find the lowest priced Allen Solly
		System.out.println(min);


	}

}