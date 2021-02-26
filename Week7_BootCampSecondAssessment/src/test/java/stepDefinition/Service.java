package stepDefinition;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.When;
import runner.BaseClass;

public class Service extends BaseClass {
	
	@When("Click on Reports")
	public Reports click_on_Reports() throws InterruptedException {
		Thread.sleep(10000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement reports = driver.findElementByXPath("//span[text()='Reports' and @class='slds-truncate']");
		executor.executeScript("arguments[0].click();", reports);
		Thread.sleep(4000);
		return new Reports();
	}

}
