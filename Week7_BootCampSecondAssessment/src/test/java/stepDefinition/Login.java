package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
