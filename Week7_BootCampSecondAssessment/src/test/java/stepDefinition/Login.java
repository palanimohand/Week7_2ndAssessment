package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import runner.BaseClass;

public class Login extends BaseClass {

	@When("Launch Salesforce application login.salesforce.com")
	public Login launch_Salesforce_application_https_login_salesforce_com() {
		driver.get("https://login.salesforce.com/");
		return this;
	}

	@Given("the user is able to enter username as (.*)")
	public Login the_user_is_able_to_enter_username(String userName) {
		driver.findElementById("username").sendKeys(userName);
		return this;
	}

	@Given("the user is able to enter password as (.*)")
	public Login the_user_is_able_to_enter_password(String passWord) {
		driver.findElementById("password").sendKeys(passWord);
		return this;
	}

	@Given("the user is able to click login")
	public Home the_user_is_able_to_click_login() {
		driver.findElementById("Login").click();
		return new Home();
	}

}
