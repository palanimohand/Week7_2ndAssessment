package stepDefinition;

import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.BaseClass;

public class Reports extends BaseClass{
	
	@When("Click on New Report-SalesForce Classic")
	public NewReport click_on_New_Report_SalesForce_Classic() throws InterruptedException {
		driver.findElementByXPath("//div[@title='New Report (Salesforce Classic)']//parent::a").click();
		Thread.sleep(10000);
		return new NewReport();
	}
	
	@Then("Verify Report has been created successfully")
	public Reports verify_Report_has_been_created_successfully() {
		driver.switchTo().defaultContent();
		List<WebElement> iframe = driver
				.findElements(By.xpath("//iframe[contains(@class,'isEdit reportsReportBuilder')]"));
		for (WebElement webElement : iframe) {
			if (webElement.isDisplayed()) {
				driver.switchTo().frame(webElement);
			}
		}
		String reportType = driver.findElement(By.xpath("//h1[@class='pageType']")).getText().trim();
		System.out.println(reportType);
		assertTrue(reportType.contains("Report Type: Leads"));
		String reportDescription = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText().trim();
		assertTrue(reportDescription.contains("Palanimohan"));
		return this;
	}

	@Then("Click on Run Report")
	public Reports click_on_Run_Report() throws InterruptedException {
		List<WebElement> runReport = driver.findElements(By.xpath("//button[text()='Run Report']"));
		System.out.println(runReport.size());
		for (WebElement webElement : runReport) {
			if (webElement.isDisplayed()) {
				webElement.click();
			}
		}
		Thread.sleep(3000);
		return this;
	}

	@Then("Get the total Number of Records")
	public Reports get_the_total_Number_of_Records() throws InterruptedException {
		List<WebElement> records = driver
				.findElements(By.xpath("//div[text()='Total Records']//following-sibling::div"));
		System.out.println(records.size());
		for (WebElement webElement : records) {
			if (webElement.isDisplayed()) {
				webElement.click();
			}
		}
		Thread.sleep(10000);
		return this;
	}

	@Then("Click on Edit")
	public Reports click_on_Edit() throws InterruptedException {
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
		return this;
	}

	@Then("Click on Close")
	public Reports click_on_Close() throws InterruptedException {
		driver.switchTo().defaultContent();
		List<WebElement> iframe = driver
				.findElements(By.xpath("//iframe[contains(@class,'isEdit reportsReportBuilder')]"));
		for (WebElement webElement : iframe) {
			if (webElement.isDisplayed()) {
				driver.switchTo().frame(webElement);
			}
		}
		click(By.xpath("//button[text()='Close']"));
		click(By.xpath("//button[text()='Close']"));
		Thread.sleep(10000);
		return this;
	}

	@Then("Get the text of Report Name")
	public Reports get_the_text_of_Report_Name() throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[contains(@class,'search-text-field')]")).sendKeys(nameident);
		Thread.sleep(3000);
		List<WebElement> names = driver.findElements(By.xpath("//th[@data-label='Report Name']"));
		for (WebElement webElement : names) {
			if (webElement.getText().trim().contains(nameident)) {
				System.out.println("Report Present");
			} else {
				System.out.println("Report Not Present");
			}

		}
		return this;
	}

	@Then("Verify the Report Name")
	public Reports verify_the_Report_Name() {
		return this;
	}

	@Then("Get the Date and Time When the Report is Created On")
	public Reports get_the_Date_and_Time_When_the_Report_is_Created_On() {
		List<WebElement> dates = driver.findElements(By.xpath("//td[@data-label='Created On']"));
		for (WebElement webElement : dates) {
			System.out.println(webElement.getText());
		}
		return this;
	}
}
