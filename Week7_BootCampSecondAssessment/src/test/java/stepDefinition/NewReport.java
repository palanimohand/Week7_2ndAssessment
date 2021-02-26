package stepDefinition;

import static org.testng.Assert.assertEquals;
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
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.BaseClass;

public class NewReport extends BaseClass {
	
	@When("Click on Leads")
	public NewReport click_on_Leads() throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@class,'isEdit')]")));
		driver.findElement(By.xpath("//div[contains(@class,'x-unselectable folder')]//span[text()='Leads']")).click();
		Thread.sleep(4000);
		return this;
	}

	@When("Download the Lead Report Image on the Right side")
	public NewReport download_the_Lead_Report_Image_on_the_Right_side()
			throws MalformedURLException, IOException, InterruptedException {
		WebElement imgElement = driver.findElement(By.id("thePage:dummyForm:report_img"));
		Thread.sleep(2000);
		String src = imgElement.getAttribute("src");
		BufferedImage bufferedImage = ImageIO.read(new URL(src));
		File outputfile = new File("saved.png");
		ImageIO.write(bufferedImage, "png", outputfile);
		Thread.sleep(4000);
		return this;
	}

	@When("Click on Create")
	public NewReport click_on_Create() throws InterruptedException {
		driver.findElement(By.id("thePage:rtForm:createButton")).click();
		Thread.sleep(4000);
		return this;
	}

	@When("Select Range as All Time")
	public NewReport select_Range_as_All_Time() throws InterruptedException {
		driver.findElement(By.name("duration")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='All Time']")).click();
		return this;
	}

	@When("Select From date as todays date")
	public NewReport select_From_date_as_todays_date() throws InterruptedException, ParseException {
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
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-mp-month']/a[text()='"
							+ new SimpleDateFormat("MMM").format(c.getTime()) + "']"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@class='x-date-mp-ok']")).click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//div[contains(@class,'x-menu-floating')][2]//td[@class='x-date-active']//span[text()='"
							+ c.get(Calendar.DAY_OF_MONTH) + "']"))
					.click();
			Thread.sleep(2000);
		}
		return this;
	}

	@When("Select To as +{int} days From Today")
	public NewReport select_To_as_days_From_Today(Integer int1) {
		return this;

	}

	@Then("Verify Whether the Preview is in Tabular Format")
	public NewReport verify_Whether_the_Preview_is_in_Tabular_Format() {
		String text = driver.findElement(By.xpath("//table[@id='reportFormatMink']//button")).getText();
		assertEquals(text.trim(), "Tabular Format");
		return this;
	}

	@Then("Get the List of Billing State\\/Province")
	public NewReport get_the_List_of_Billing_State_Province() {
		List<WebElement> company = driver.findElements(
				By.xpath("//td[contains(@class,'x-grid3-col') and contains(@class,'x-grid3-td-COMPANY')]"));
		return this;
	}

	@Then("Get the Grand Total of Records Available")
	public NewReport get_the_Grand_Total_of_Records_Available() {
		String totalrecords = driver
				.findElement(
						By.xpath("//div[@id='gridViewScrollpreviewPanelGrid']//b[contains(text(),'Grand Totals')]"))
				.getText();
		return this;
	}

	@Then("Click on Save before Report")
	public NewReport click_on_Save_before_Report() {
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		return this;
	}

	@Then("Enter Report name as <Your name>")
	public NewReport enter_Report_name_as_Your_name() throws InterruptedException {
		Random rm = new Random();
		int random = rm.nextInt(1000);
		nameident = "Palanimohan_" + random;
		driver.findElement(By.id("saveReportDlg_reportNameField")).sendKeys(nameident);
		Thread.sleep(1000);
		return this;
	}

	@Then("Enter Report Unique name as<yourName_anyNumber>")
	public NewReport enter_Report_Unique_name_as_yourName_anyNumber() throws InterruptedException {
		// driver.findElement(By.id("saveReportDlg_DeveloperName")).sendKeys(nameident);
		Thread.sleep(1000);
		return this;
	}

	@Then("Enter Report Discussion as Report Updated by <yourName>")
	public NewReport enter_Report_Discussion_as_Report_Updated_by_yourName() throws InterruptedException {
		driver.findElement(By.name("reportDescription")).sendKeys("Report updated by Palanimohan");
		Thread.sleep(1000);
		return this;
	}

	@Then("Select Report Folder as Unfiled Public Reports")
	public NewReport select_Report_Folder_as_Unfiled_Public_Reports() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='saveReportDlg_folderFinder']//following-sibling::img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Unfiled Public Reports']")).click();
		Thread.sleep(1000);
		return this;
	}

	@Then("Click on Save from Report")
	public Reports click_on_Save_from_Report() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='x-window-footer x-panel-btns']//button[text()='Save']")).click();
		Thread.sleep(10000);
		return new Reports();
	}


}
