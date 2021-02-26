package stepDefinition;

import cucumber.api.java.en.When;
import runner.BaseClass;

public class Home extends BaseClass {
	
	@When("Click on App Launcher")
	public Home click_on_App_Launcher() throws InterruptedException {
		Thread.sleep(20000);
		driver.findElementByClassName("slds-icon-waffle").click();
		Thread.sleep(4000);
		driver.findElementByXPath("//button[@class='slds-button']").click();
		return this;
	}

	@When("Click on Service")
	public Service click_on_Service() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElementByXPath("//p[text()='Service']").click();
		return new Service();
	}

}
