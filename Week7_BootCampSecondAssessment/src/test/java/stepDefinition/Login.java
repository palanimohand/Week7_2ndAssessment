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

	@Given("the user is able to enter username as (.*)")
	public void the_user_is_able_to_enter_username(String userName) {
		driver.findElementById("username").sendKeys(userName);
	}

	@Given("the user is able to enter password as (.*)")
	public void the_user_is_able_to_enter_password(String passWord) {
		driver.findElementById("password").sendKeys(passWord);
	}

	@Given("the user is able to click login")
	public void the_user_is_able_to_click_login() {
		driver.findElementById("Login").click();
	}

	@When("Launch Salesforce application login.salesforce.com")
	public void launch_Salesforce_application_https_login_salesforce_com() {
		driver.get("https://login.salesforce.com/");
	}

	@When("Click on App Launcher")
	public void click_on_App_Launcher() throws InterruptedException {
		Thread.sleep(20000);
		driver.findElementByClassName("slds-icon-waffle").click();
		Thread.sleep(4000);
		driver.findElementByXPath("//button[@class='slds-button']").click();
	}

	@When("Click on Service")
	public void click_on_Service() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElementByXPath("//p[text()='Sales']").click();
	}

	@When("Click on Reports")
	public void click_on_Reports() throws InterruptedException {
		Thread.sleep(10000);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement reports = driver.findElementByXPath("//span[text()='Reports' and @class='slds-truncate']");
		executor.executeScript("arguments[0].click();", reports);
		Thread.sleep(4000);
	}

	@When("Click on New Report-SalesForce Classic")
	public void click_on_New_Report_SalesForce_Classic() throws InterruptedException {
		driver.findElementByXPath("//div[@title='New Report (Salesforce Classic)']//parent::a").click();
		Thread.sleep(10000);
	}

	@When("Click on Leads")
	public void click_on_Leads() throws InterruptedException {
		driver.switchTo()
				.frame(driver.findElement(By.xpath("//iframe[contains(@class,'isEdit')]")));
		driver.findElement(By.xpath("//div[contains(@class,'x-unselectable folder')]//span[text()='Leads']")).click();
		Thread.sleep(4000);
	}

	@When("Download the Lead Report Image on the Right side")
	public void download_the_Lead_Report_Image_on_the_Right_side()
			throws MalformedURLException, IOException, InterruptedException {
		WebElement imgElement = driver.findElement(By.id("thePage:dummyForm:report_img"));
		String src = imgElement.getAttribute("src");
		BufferedImage bufferedImage = ImageIO.read(new URL(src));
		File outputfile = new File("saved.png");
		ImageIO.write(bufferedImage, "png", outputfile);
		Thread.sleep(4000);
	}

	@When("Click on Create")
	public void click_on_Create() throws InterruptedException {
		driver.findElement(By.id("thePage:rtForm:createButton")).click();
		Thread.sleep(4000);
	}

	@When("Select Range as All Time")
	public void select_Range_as_All_Time() throws InterruptedException {
		driver.findElement(By.name("duration")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='All Time']")).click();
	}

	@When("Select From date as todays date")
	public void select_From_date_as_todays_date() throws InterruptedException, ParseException {
		driver.findElement(By.xpath("//input[@name='startDate']//following-sibling::img")).click();
		Thread.sleep(5000);
		String date = driver.findElement(By.xpath("//td[@title='Today']")).getText();
		String monthNyear = driver.findElement(By.xpath("//td[@class='x-date-middle']//button")).getText();
		String year = monthNyear.replaceAll("[^\\d.]", "").trim();
		String month = monthNyear.replaceAll("\\d", "").trim();
		SimpleDateFormat dates = new SimpleDateFormat("dd MMMM yyyy");
		String format = date + " " + month + " " + year;
		Calendar c = Calendar.getInstance();
		c.setTime(dates.parse(format));
		c.add(Calendar.DAY_OF_MONTH, 5);
		driver.findElement(By.xpath("//button[text()='Today']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='endDate']//following-sibling::img")).click();
		Thread.sleep(8000);
		String tomonthNyear = driver
				.findElement(
						By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-middle']//button"))
				.getText();
		String toyear = tomonthNyear.replaceAll("[^\\d.]", "").trim();
		String tomonth = tomonthNyear.replaceAll("\\d", "").trim();
		if ((toyear.trim().equals(Integer.toString(c.get(Calendar.YEAR)).trim()))
				&& (tomonth.trim().equals(new SimpleDateFormat("MMMM").format(c.getTime()).trim()))) {
			// driver.findElement(By.xpath("div[contains(@class,'x-menu-floating')][2]//span[text()='"+c.get(Calendar.DAY_OF_MONTH)+"']//ancestor::a")).click();
			List<WebElement> findElements = driver
					.findElements(By.xpath("//span[text()='" + c.get(Calendar.DAY_OF_MONTH) + "']"));
			for (WebElement iterable_element : findElements) {
				if (iterable_element.isDisplayed()) {
					iterable_element.click();
					Thread.sleep(4000);
				}
			}
		} else {
			driver.findElement(
					By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-middle']//button"))
					.click();
			driver.findElement(
					By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-mp-month']/a[text()='"
							+ new SimpleDateFormat("MMM").format(c.getTime()) + "']"))
					.click();
			driver.findElement(By.xpath("//button[@class='x-date-mp-ok']")).click();
			driver.findElement(
					By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-active']//span[text()='"
							+ c.get(Calendar.DAY_OF_MONTH) + "']"))
					.click();
		}
	}

	@When("Select To as +{int} days From Today")
	public void select_To_as_days_From_Today(Integer int1) {

	}

	@Then("Verify Whether the Preview is in Tabular Format")
	public void verify_Whether_the_Preview_is_in_Tabular_Format() {
		String text = driver.findElement(By.xpath("//table[@id='reportFormatMink']//button")).getText();
		assertEquals(text.trim(), "Tabular Format");
	}

	@Then("Get the List of Billing State\\/Province")
	public void get_the_List_of_Billing_State_Province() {
		List<WebElement> company = driver.findElements(
				By.xpath("//td[contains(@class,'x-grid3-col') and contains(@class,'x-grid3-td-COMPANY')]"));
	}

	@Then("Get the Grand Total of Records Available")
	public void get_the_Grand_Total_of_Records_Available() {
		String totalrecords = driver
				.findElement(
						By.xpath("//div[@id='gridViewScrollpreviewPanelGrid']//b[contains(text(),'Grand Totals')]"))
				.getText();
	}

	@Then("Click on Save before Report")
	public void click_on_Save_before_Report() {
		driver.findElement(By.xpath("//button[text()='Save']")).click();
	}

	@Then("Enter Report name as <Your name>")
	public void enter_Report_name_as_Your_name() throws InterruptedException {
		driver.findElement(By.id("saveReportDlg_reportNameField")).sendKeys("Palanimohan");
		Thread.sleep(1000);
	}

	@Then("Enter Report Unique name as<yourName_anyNumber>")
	public void enter_Report_Unique_name_as_yourName_anyNumber() throws InterruptedException {
		Random rm = new Random();
		int random = rm.nextInt(1000);
		driver.findElement(By.id("saveReportDlg_DeveloperName")).sendKeys("_" + random);
		Thread.sleep(1000);
	}

	@Then("Enter Report Discussion as Report Updated by <yourName>")
	public void enter_Report_Discussion_as_Report_Updated_by_yourName() throws InterruptedException {
		driver.findElement(By.name("reportDescription")).sendKeys("Report updated by Palanimohan");
		Thread.sleep(1000);
	}

	@Then("Select Report Folder as Unfiled Public Reports")
	public void select_Report_Folder_as_Unfiled_Public_Reports() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='saveReportDlg_folderFinder']//following-sibling::img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Unfiled Public Reports']")).click();
		Thread.sleep(1000);
	}

	@Then("Click on Save from Report")
	public void click_on_Save_from_Report() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='x-window-footer x-panel-btns']//button[text()='Save']")).click();
		Thread.sleep(10000);
	}

	@Then("Verify Report has been created successfully")
	public void verify_Report_has_been_created_successfully() {
		driver.switchTo().defaultContent();
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe[contains(@class,'isEdit reportsReportBuilder')]"));
		for (WebElement webElement : iframe) {
			if(webElement.isDisplayed()) {
				driver.switchTo().frame(webElement);
			}
		}
		String reportType = driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim();
		System.out.println(reportType);
		assertTrue(reportType.contains("Report Type: Leads"));
		String reportDescription = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText().trim();
		assertTrue(reportDescription.contains("Palanimohan"));
	}

	@Then("Click on Run Report")
	public void click_on_Run_Report() throws InterruptedException {
		List<WebElement> runReport = driver.findElements(By.xpath("//button[text()='Run Report']"));
		System.out.println(runReport.size());
		for (WebElement webElement : runReport) {
			if (webElement.isDisplayed()) {
				webElement.click();
			}
		}
		Thread.sleep(3000);
	}

	@Then("Get the total Number of Records")
	public void get_the_total_Number_of_Records() throws InterruptedException {
		List<WebElement> records = driver
				.findElements(By.xpath("//div[text()='Total Records']//following-sibling::div"));
		System.out.println(records.size());
		for (WebElement webElement : records) {
			if (webElement.isDisplayed()) {
				webElement.click();
			}
		}
		Thread.sleep(10000);
	}

	@Then("Click on Edit")
	public void click_on_Edit() throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame((driver.findElement(By.xpath("//iframe[@class='isView reportsReportBuilder']"))));
		List<WebElement> edit = driver.findElements(By.xpath("//button[text()='Edit']"));
		System.out.println(edit.size());
		for (WebElement webElement : edit) {
			if (webElement.isDisplayed()) {
				webElement.click();
			}
		}
		Thread.sleep(10000);
	}

	@Then("Click on Close")
	public void click_on_Close() throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@class,'reportsReportBuilder')]")));
		WebElement closeButton = driver.findElement(By.xpath("//button[text()='Close']//parent::div"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",closeButton);
		Thread.sleep(5000);
	}

	@Then("Get the text of Report Name")
	public void get_the_text_of_Report_Name() {
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[contains(@class,'search-text-field')]")).sendKeys("Palanimohan");
		List<WebElement> names = driver.findElements(By.xpath("//th[@data-label='Report Name']"));
		if (names.contains("Palanimohan")) {
			System.out.println("Report Present");
		}
	}

	@Then("Verify the Report Name")
	public void verify_the_Report_Name() {
	}

	@Then("Get the Date and Time When the Report is Created On")
	public void get_the_Date_and_Time_When_the_Report_is_Created_On() {
		List<WebElement> dates = driver.findElements(By.xpath("//td[@data-label='Created On']"));
		for (WebElement webElement : dates) {
			System.out.println(webElement.getText());
		}
	}

}
