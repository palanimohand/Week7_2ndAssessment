package runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.testng.AbstractTestNGCucumberTests;

public class BaseClass extends AbstractTestNGCucumberTests {

	public static WebDriverWait wait;
	public JavascriptExecutor executor;
	public static ArrayList<String> allWindows;
	public static Actions ac;
	public Set<String> windowHandles;
	public WebElement element;
	public List<WebElement> elements;
	public static String text;
	public static String sheetName;
	public String browser;
	public static RemoteWebDriver driver;
	public static String nameident;

	public void launchBrowser(String browser) {

		try {
			if (browser.equalsIgnoreCase("Chrome")) {

				// Create prefs map to store all preferences
				Map<String, Object> prefs = new HashMap<String, Object>();

				// Put this into prefs map to switch off browser notification
				prefs.put("profile.default_content_setting_values.notifications", 2);

				// Create chrome options to set this prefs
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);

				System.setProperty("webdriver.chrome.driver",
						"/Users/palanimohan/Documents/Career/qualityEngineer/Softwares/drivers/chromedriver");

				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {

				System.setProperty("webdriver.gecko.driver",
						"/Users/palanimohan/Documents/Career/qualityEngineer/Softwares/drivers/geckodriver");
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Safari")) {

				System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
				driver = new SafariDriver();

			}

		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	public void get(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void type(By ele, String text) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			locateElement(ele).clear();
			locateElement(ele).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getText(By ele) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			text = locateElement(ele).getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	public String getText(WebElement ele) {
		try {

			wait.until(ExpectedConditions.visibilityOf(ele));
			;
			text = ele.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}

	public void click(By ele) {
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(ele));
			locateElement(ele).click();
		} catch (ElementClickInterceptedException e) {
			//e.printStackTrace();
		} catch (ElementNotInteractableException e) {
			//e.printStackTrace();
		}

	}

	public void javaScriptClick(By ele) {
		try {
			// wait.until(ExpectedConditions.elementToBeClickable(ele));
			executor.executeScript("arguments[0].click();", locateElement(ele));
		} catch (ElementNotInteractableException e) {
			// e.printStackTrace();
			click(ele);
		}
	}

	public void actionClick(By ele) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ac.click(locateElement(ele)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionDoubleClick(By ele) {
		try {
			// wait.until(ExpectedConditions.elementToBeClickable(ele));
			ac.doubleClick(locateElement(ele)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchToNextNewWindow() throws InterruptedException {
		try {
			windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				while (!allWindows.contains(handle)) {
					allWindows.add(handle);
					driver.switchTo().window(handle);
					break;
				}
			}
		} catch (NoSuchWindowException e) {
			Thread.sleep(3000);
			switchToNextFrame();
		}
	}

	public void switchToNextFrame() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("iframe"))));
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			System.out.println(frames.size());
			for (WebElement webElement : frames) {
				while (webElement.isEnabled()) {
					driver.switchTo().frame(webElement);
					break;
				}
			}
		} catch (NoSuchFrameException e) {
			Thread.sleep(3000);
			switchToNextFrame();
		}
	}

	public void moveToElement(By ele) {
		try {
			ac.moveToElement(locateElement(ele)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectByVisibleText(By ele, String text) {
		try {
			Select sc = new Select(locateElement(ele));
			sc.selectByVisibleText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebElement locateElement(By ele) {
		try {
			element = driver.findElement(ele);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		} catch (InvalidElementStateException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return element;
	}

	public WebElement locateElementWAElement(WebElement ele, By ele1) {
		try {
			element = ele.findElement(ele1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public List<WebElement> locateElements(By ele) {
		try {
			elements = driver.findElements(ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}

	public void swithToFrameWithWait(By locator) {
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
