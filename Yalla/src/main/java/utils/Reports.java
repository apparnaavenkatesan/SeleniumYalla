package utils;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 
public class Reports {
	static ExtentHtmlReporter reporter;
	static ExtentReports extent;
	ExtentTest test;
	public String testcaseName, testcaseDec, author, category;
	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true); 
		extent   = new ExtentReports();
		extent.attachReporter(reporter);
	}
    @BeforeMethod
	public void report() throws IOException {
		test = extent.createTest(testcaseName, testcaseDec);
	    test.assignAuthor(author);
	    test.assignCategory(category);  
	}
    
    public void reportStep(String dec, String status) {
    	if(status.equalsIgnoreCase("pass")) {
    		test.pass(dec);
    	} else if(status.equalsIgnoreCase("fail")) {
    		test.fail(dec); 
    	}
    }
    @AfterSuite
    public void stopReport() {
    	extent.flush();
    }
}