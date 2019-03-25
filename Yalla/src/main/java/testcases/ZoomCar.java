package testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class ZoomCar extends SeleniumBase{

	@Test
	public void zoomCarApp()
	{
	
	startApp("chrome","https://www.zoomcar.com/chennai/");
	WebElement startJourneyLink = locateElement("link", "Start your wonderful journey");
	click(startJourneyLink);
	WebElement pickUpLoc = locateElement("xpath", "(//div[@class='component-popular-locations']//div[@class='items'])[1]");
	clickWithScreenshot(pickUpLoc);
	WebElement nxtBtn1 = locateElement("xpath", "//button[text()='Next']");
	click(nxtBtn1);
	WebElement nextDate = locateElement("xpath", "(//div[@class='day picked ']/following::div)[1]");
	clickWithScreenshot(nextDate);
	click(nxtBtn1);
	WebElement pickUpLabelDate = locateElement("xpath","(//div[@class='label time-label'])[1]");
	String pickUpLabelText = getElementText(pickUpLabelDate);
	WebElement dropLabelDate = locateElement("xpath","(//div[@class='label time-label'])[2]");
	String dropLabelText = getElementText(dropLabelDate);
	boolean cmpResult = compareTexts(pickUpLabelText.substring(0, 15),dropLabelText.substring(0, 15));
	if(cmpResult==true)
	{
		WebElement doneBtn = locateElement("xpath", "//button[text()='Done']");
		click(doneBtn);
		rest(3000);
	}
	else if(cmpResult==false)
	{
		System.out.println("Dates Not Matching! ");
		closeApp();
	}
	WebElement sortHighToLow = locateElement("xpath", "//div[text()=' Price: High to Low ']");
	click(sortHighToLow);
	rest(2000);
	takeSnap();
	List<WebElement> bookNowBtns = locateElements("xpath","//button[text()='BOOK NOW']");
	int countResult = bookNowBtns.size();
	System.out.println("Number of Results : " + countResult);
	WebElement firstResult = locateElement("xpath", "//div[@class='car-item']//h3");
	getElementText(firstResult);
	click(bookNowBtns.get(0));
	rest(2000);
	takeSnap();
	closeApp();
	
	
	}

}
