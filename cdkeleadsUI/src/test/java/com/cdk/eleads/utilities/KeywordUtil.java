package com.cdk.eleads.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cdk.eleads.step_definitions.RunCukesTest;
import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author TX
 *
 */
public class KeywordUtil extends GlobalUtil {
	public static String cucumberTagName;
	private static final int DEFAULT_WAIT_SECONDS = 60;
	protected static final int FAIL = 0;
	static WebElement webElement;
	protected static String url = "";
	private static String userDir = "user.dir";
	private static String text = "";
	public static final String VALUE = "value";
	public static String lastAction = "";

	static String result_FolderName = System.getProperty("user.dir") + "\\ExecutionReports\\ExecutionReports";

	public static void onExecutionFinish() {

		// Salmon raj Commented Send Email Code
		// TODO Auto-generated method stub
		// Send Mail functionality if
		LogUtil.infoLog("TestProcessEnd", "Test process has ended");

		if (GlobalUtil.getCommonSettings().getEmailOutput().equalsIgnoreCase("Y")) {
			LogUtil.infoLog("TestEmailProcessing",
					"Email Flag Set To: " + GlobalUtil.getCommonSettings().getEmailOutput());
			try {
				// sendMail.sendEmailToClient(
				// "Hi All, \n\nPlease find the attached Execution Report.\n\n\nThanks &
				// Regards\nTesting Xperts",
				// true, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LogUtil.infoLog("TestEmailProcessing",
					"Email Flag Set To: " + GlobalUtil.getCommonSettings().getEmailOutput());
		}

		String htmlReportFile = System.getProperty("user.dir") + "\\" + ConfigReader.getValue("HtmlReportFullPath");
		System.out.println("cucumber path is" + htmlReportFile);
		File f = new File(htmlReportFile);
		if (f.exists()) {

			try {
				Runtime.getRuntime().exec(
						"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"" + htmlReportFile + "\"");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// if (cucumberTagName.equals("Mobile") || cucumberTagName.equals("Web")) {
		String htmlExtentReportFile = System.getProperty("user.dir") + "\\" + ConfigReader.getValue("extentReportPath");
		File extentReport = new File(htmlExtentReportFile);
		if (extentReport.exists()) {

			try {
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \""
						+ htmlExtentReportFile + "\"");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] takeScreenshot(String screenshotFilePath) {
		try {
			byte[] screenshot = ((TakesScreenshot) GlobalUtil.getDriver()).getScreenshotAs(OutputType.BYTES);
			FileOutputStream fileOuputStream = new FileOutputStream(screenshotFilePath);
			fileOuputStream.write(screenshot);
			fileOuputStream.close();
			return screenshot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean scrollingToElementofAPage(By locator, String logStep) throws InterruptedException {
		Thread.sleep(5000);
		WebElement element = GlobalUtil.getDriver().findElement(locator);
		((JavascriptExecutor) GlobalUtil.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		// highLightElement(driver, element);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return true;
	}

	public static byte[] takeMobileScreenshot(String screenshotFilePath) {
		try {
			byte[] screenshot = ((TakesScreenshot) GlobalUtil.getMDriver()).getScreenshotAs(OutputType.BYTES);
			FileOutputStream fileOuputStream = new FileOutputStream(screenshotFilePath);
			fileOuputStream.write(screenshot);
			fileOuputStream.close();
			return screenshot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentDateTime() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println(strDate);
		return strDate;
	}

	/**
	 * @param locator
	 * @return
	 */
	public static void navigateToUrl(String url) {
		try {
			Thread.sleep(2500);
			KeywordUtil.lastAction = "Navigate to: " + url;
			LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
			getDriver().navigate().to(url);
			// String Title = getDriver().getTitle();
			// System.out.println(Title);
			// if(Title.contains("Robot Check"))
			// {
			// getDriver().navigate().to(url);
			// }

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// private static Object getTitle() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	public static String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	public static String getTitle() {
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(getDriver().getTitle()));

		return getDriver().getTitle();
	}

	public static WebElement waitForClickable(By locator) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static String clickInstallIntegrationsApp(By locator, String logStep) throws InterruptedException {
		WebElement elm = waitForClickable(locator);
		KeywordUtil.lastAction = "Click: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		if (elm.getText().contains("Install")) {
			elm.click();
		} else {
			elm.click();
		}
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
		return elm.getText();
	}

	/**
	 * @param locator
	 * @return
	 */
	public static WebElement waitForPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
			// wait.ignoring(ElementNotVisibleException.class);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean waitForInVisibile(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static WebElement waitForVisibleIgnoreStaleElement(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * @param locator
	 * @param seconds
	 * @param poolingMil
	 * @return
	 * @throws Exception
	 */
	public static WebElement findWithFluintWait(By locator, int seconds, int poolingMil) throws Exception {
		// Because if implicit wait is set then fluint wait will not work

		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		WebElement element = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(seconds, TimeUnit.SECONDS)
					.pollingEvery(poolingMil, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(WebDriverException.class);
			element = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});

		} catch (Exception t) {
			throw new Exception("Timeout reached when searching for element! Time: " + seconds + " seconds " + "\n"
					+ t.getMessage());
		} finally {
			// getDriver().manage().timeouts().implicitlyWait(Utility.getIntValue("implicitlyWait"),
			// TimeUnit.SECONDS);
		}

		return element;
	}// End FindWithWait()

	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static WebElement findWithFluintWait(By locator) throws Exception {
		getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Because if implict wait is set then fluint wait will not work
		KeywordUtil.lastAction = "Find Element: " + locator.toString();
		// getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement element = null;

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
					.withTimeout(DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotVisibleException.class);

			element = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});

		} catch (Exception t) {
			throw new Exception("Timeout reached when searching for element! Time: " + DEFAULT_WAIT_SECONDS
					+ " seconds " + "\n" + t.getMessage());
		} finally {
			// getDriver().manage().timeouts().implicitlyWait(Utility.getIntValue("implicitlyWait"),
			// TimeUnit.SECONDS);
		}

		return element;
	}// End FindWithWait()

	public static WebElement getWebElement(By locator) throws Exception {
		KeywordUtil.lastAction = "Find Element: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		return findWithFluintWait(locator);
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/*
	 * Web driver common functions
	 * ===========================================================
	 */

	public static void selectBasedOnIndex(By locator, int index, String logstatus) throws InterruptedException {
		WebElement element = waitForClickable(locator);
		KeywordUtil.lastAction = "Click: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		
		List<WebElement> options = element.findElements(locator);
		options.get(index).click();
	}
	
	/**
	 * @param locator
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean click(By locator, String logStep) throws InterruptedException {
		
		Thread.sleep(2500);
		WebElement elm = waitForClickable(locator);
		KeywordUtil.lastAction = "Click: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);

		if (elm == null) {
			return false;
		} else {
			// ((JavascriptExecutor)
			// GlobalUtil.getDriver()).executeScript("arguments[0].scrollIntoView();", elm);
			elm.click();
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
			Thread.sleep(1000);
			return true;
		}
	}

	public static boolean clickCart(By locator, String logStep) throws InterruptedException {

		KeywordUtil.lastAction = "Click: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement elm = waitForClickable(locator);
		if (elm == null) {
			return false;
		} else {

			((JavascriptExecutor) GlobalUtil.getDriver()).executeScript("arguments[0].scrollIntoView();", elm);
			// ((JavascriptExecutor)
			// GlobalUtil.getDriver()).executeScript("window.scrollBy(0,1000)");
			elm.click();
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		}
	}

	public static boolean acceptAlert() {

		Alert alert = GlobalUtil.getDriver().switchTo().alert();
		alert.accept();
		return true;

	}

	public static boolean switchToWindow() {

		ArrayList<String> tabs2 = new ArrayList<String>(GlobalUtil.getDriver().getWindowHandles());
		GlobalUtil.getDriver().switchTo().window(tabs2.get(1));
		return true;

	}

	/**
	 * @param linkText
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean clickLink(String linkText, String logStep) throws InterruptedException {
		KeywordUtil.lastAction = "Click Link: " + linkText;
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement elm = waitForClickable(By.linkText(linkText));
		if (elm == null) {
			return false;
		} else {
			elm.click();
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		}
	}

	/**
	 * @param locator
	 * @return
	 * @throws InterruptedException
	 */
	public static String getElementText(By locator) throws InterruptedException {
		KeywordUtil.lastAction = "Get Element text: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement elm = waitForClickable(locator);
		return elm.getText().trim();
	}

	public static String getImageTitle(By locator) {
		WebElement elm = waitForVisible(locator);
		return elm.getAttribute("title");

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isWebElementVisible(By locator, String logStep) {
		try {
			KeywordUtil.lastAction = "Check Element visible: " + locator.toString();
			LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
			WebElement elm = waitForVisible(locator);
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return elm.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public static void defaultContent() throws InterruptedException {
		Thread.sleep(2000);
		getDriver().switchTo().defaultContent();
	}
	
	
	public static void getWindowsHandles() {
		 Set<String> allWindowHandles = getDriver().getWindowHandles();
		 
		 for(String handle : allWindowHandles)
		 {
		 System.out.println("Window handle - > " + handle);
		 getDriver().switchTo().window(handle);
		 }
	}
	public static void clickOnComplete(By locator, String value) throws InterruptedException {
		Thread.sleep(2500);
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		List<WebElement> complete = getDriver().findElements(By.xpath("//*[@id='gvScheduled']/tbody/tr/td/a/span[text()='Complete']"));
		for (int j = 0; j < 10; j++) {
			System.out.println("----------------------------------------------------------------------------------------");
			List<WebElement> al = getDriver().findElements(By.xpath("//*[@id='gvScheduled_Task_"+j+"']"));
		      System.out.println("--------------------------------"+al.toString()+"-----------------------------------");
			for (int i = 0; i < al.size(); i++) {
				
				if (al.get(i).getText().contains(value)) {
					System.out.println(al.get(i).getText()+ "--------------------- Complete Button--------------------");
					complete.get(i).click();
					
					break;
				}
			}
		}
	
		
	}
	/**
	 * To Validate the text present among the list of element
	 * 
	 * @param locator
	 * @param logStep
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean isAppModeInstall(By locator, By locator1, String appName, String value, String logStep)
			throws InterruptedException {
		WebElement elm = waitForClickable(locator);
		KeywordUtil.lastAction = "Click: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		List<WebElement> h5 = getDriver().findElements(locator);
		List<WebElement> btnInstall = getDriver().findElements(locator1);
		for (int i = 0; i < h5.size(); i++) {
			if (h5.get(i).getText().trim().contains(appName)) {
				System.out.println("App Name :" + appName);
				System.out.println("button :" + btnInstall.get(i).getText().contains(value));
				if (btnInstall.get(i).getText().contains(value)) {
					RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
					return true;
				}
			}
		}
		if (elm == null) {
		} else {
			elm.click();
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
		}
		return true;
	}

	public static boolean isWebElementEnable(By locator, String logStep) {
		KeywordUtil.lastAction = "Check Element visible: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement elm = waitForVisible(locator);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return elm.isEnabled();
	}

	/**
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getListElements(By locator, String logStep) {
		KeywordUtil.lastAction = "Get List of Elements: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);

		try {
			findWithFluintWait(locator, 60, 300);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return getDriver().findElements(locator);

	}

	public static boolean isWebElementPresent(By locator, String logStep) {
		KeywordUtil.lastAction = "Check Element present: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		List<WebElement> elements = getDriver().findElements(locator);
		if (elements.isEmpty()) {
			return false;
		}
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return true;
	}

	public static boolean hoverOnElement(By by) throws InterruptedException {

		WebElement element = getDriver().findElement(by);
		// highLightElement(driver,element );
		// Thread.sleep(5000);

		Actions act = new Actions(getDriver());
		act.moveToElement(element).build().perform();

		Thread.sleep(3000);

		return true;

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isWebElementNotPresent(By locator) {
		KeywordUtil.lastAction = "Check Element not present: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		List<WebElement> elements = (new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		if (elements.isEmpty()) {
			return true;
		}
		return false;
	}

	
	public static void sendDataInHtml(String value) {
		getDriver().findElement(By.tagName("body")).sendKeys(value);
	}
	public static void sendKeysWithCommand(By locator, Keys command) {
		getDriver().findElement(locator).sendKeys(command);
	}
	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException 
	 */
	public static boolean inputText(By locator, String data, String logStep) throws InterruptedException {
		Thread.sleep(1500);
		WebElement elm = waitForVisible(locator);

		KeywordUtil.lastAction = "Input Text: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		
		if (elm == null) {
			return false;
		} else {
            elm.clear();
			elm.sendKeys(data);
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
			return true;
		}
	}

	public static boolean Login(By locator, By locator1, By locator2, String email, String password, String logstep)
			throws InterruptedException {

		inputText(locator, email, logstep);
		inputText(locator1, password, logstep);
		click(locator2, logstep);
		return true;
	}

	public static void pressTabKey(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(Keys.TAB);
	}

	public static void pressEnter(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(Keys.ENTER);
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean inputTextJS(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Input Text: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].value = arguments[1]", element, data);
		if (element.getText().equalsIgnoreCase(data)) {
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		} else
			return false;
	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isRadioSelected(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Radio Selected: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return element.isSelected();
	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isRadioNotSelected(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Radio Not Selected: " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		boolean check = isRadioSelected(locator, logStep);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return (!check);
	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean clearInput(By locator) {
		WebElement element = waitForVisible(locator);
		element.clear();
		element = waitForVisible(locator);
		return element.getAttribute(VALUE).isEmpty();
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyCssProperty(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify CSS : " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);

		String[] property = data.split(":", 2);
		String expProp = property[0];
		String expValue = property[1];
		boolean flag = false;
		String prop = (waitForPresent(locator)).getCssValue(expProp);
		if (prop.trim().equals(expValue.trim())) {
			flag = true;
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return flag;
		} else {
			return flag;
		}
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyInputText(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Input Expected Text: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		String actual = element.getAttribute(VALUE);
		LogUtil.infoLog(KeywordUtil.class, "Actual:" + actual);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return actual.equalsIgnoreCase(data);

	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyInputTextJS(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Input Expected Text: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);

		String message = String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText());
		LogUtil.infoLog(KeywordUtil.class, message);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return data.equalsIgnoreCase(element.getText());
	}

	/**
	 * <h1>Log results</h1>
	 * <p>
	 * This function will write results to the log file.
	 * </p>
	 * 
	 * @param locator
	 * @param data
	 * @return
	 */
	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyText(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Expected Text: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		String message = String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText());
		LogUtil.infoLog(KeywordUtil.class, message);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return element.getText().equalsIgnoreCase(data);

	}

	public static boolean verifyTextContains(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Text Contains: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		String message = new String(
				String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText()));
		LogUtil.infoLog(KeywordUtil.class, message);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return element.getText().toUpperCase().contains(data.toUpperCase());

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean verifyDisplayAndEnable(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Element Displayed and Enable : " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		WebElement element = waitForVisible(locator);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
		return element.isDisplayed() && element.isEnabled();
	}

	public static void clickElementUsingActions(By locator, String logStep) {
		System.out.println("Element to be "+ locator);
		WebElement element = waitForVisible(locator);

		KeywordUtil.lastAction = "Click : " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		Actions actions = new Actions(getDriver());
		System.out.println("element after ");
		actions.moveToElement(element).click().build().perform();

	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean clickJS(By locator, String logStep) {
		WebElement element = waitForVisible(locator);

		KeywordUtil.lastAction = "Click : " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		Object obj = ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return obj == null;
	}

	public void switchToChildWindow() {
		String MainWindow = getDriver().getWindowHandle();
		getDriver().switchTo().window(MainWindow);

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/*
	 * Handling selects ===========================================================
	 */

	/**
	 * @param locator
	 * @param index
	 * @return
	 */
	public static boolean selectByIndex(By locator, int index, String logStep) {
		KeywordUtil.lastAction = "Select dropdown by index : " + index + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		Select sel = new Select(getDriver().findElement(locator));
		sel.selectByIndex(index);

		// Check whether element is selected or not
		sel = new Select(getDriver().findElement(locator));
		if (sel.getFirstSelectedOption().isDisplayed()) {
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param locator
	 * @param value
	 * @return
	 */
	public static boolean selectByValue(By locator, String value, String logStep) {
		KeywordUtil.lastAction = "Select dropdown by value : " + value + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		Select sel = new Select(getDriver().findElement(locator));
		sel.selectByValue(value);

		// Check whether element is selected or not
		sel = new Select(getDriver().findElement(locator));
		if (sel.getFirstSelectedOption().isDisplayed()) {
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		} else {
			return false;
		}
	}

	public static void clickElementFromList(By locator, String value, String logStep) throws Exception {
		Actions act = new Actions(getDriver());
		Thread.sleep(6500);
		WebElement element = findWithFluintWait(locator);

		boolean flag = false;
		KeywordUtil.lastAction = "Select value by text from the list : " + value + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);

		List<WebElement> options = element.findElements(locator);
		System.out.println("Count of Elements : " + options.size());
		for (WebElement webElement : options) {
			System.out.println("Get list of Dash Board Elements : " + webElement.getText());
			if (webElement.getText().trim().contains(value)) {
				System.out.println("LIST OF ELEMENTS FROM MY APPS PAGE : " + webElement.getText());
				act.moveToElement(webElement).build().perform();
				act.click().build().perform();
				RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
				break;
			}
		}
	}

	public static void switchToFrame(int index) throws Exception {
		Thread.sleep(3000);
		List<WebElement> frameCount = getDriver().findElements(By.tagName("iframe"));
		System.out.println(frameCount.size() + " Frame Counts");
		getDriver().switchTo().frame(index);
	}

	public static String getAttributeHtml(By locator) {
		System.out.println("Get the HTML Content");
		WebElement options = getDriver().findElement(locator);
		String html = options.getAttribute("innerHTML");
		System.out.println("Content From Frame : " + html);
		return html;
	}

	public static void dragAndDrop() throws Exception {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		js.executeScript("window.scrollBy(0,1000)");
		Actions act = new Actions(getDriver());
		System.out.println("Finding Drag Element");
		WebElement source = getDriver()
				.findElement(By.xpath("//*[@class='gjs-fonts gjs-f-text gjs-block gjs-one-bg gjs-four-color-h']"));
		System.out.println("Finding Drop Element");
		// switchToFrame(By.xpath("//*[@allowfullscreen='allowfullscreen']"));
		// WebElement drop =
		// getDriver().findElement(By.xpath("html/body/div[4]/preceding::div[@id='wrapper']"));
		// ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		// drag);
		// Point location = drop.getLocation();
		// int x = location.getX();
		// int y = location.getY();
		// System.out.println("Coordinates of an element is : " + x + " and " + y);
		// Actions act = new Actions(driver); act.clickAndHold(scroll).moveByOffset( x,
		// 200).build().perform();
		// act.moveToElement(drag).build().perform();
		//
		// System.out.println("Holding Drag Element");
		// act.clickAndHold(drag).build().perform();
		act.clickAndHold(source).build().perform();
		switchToFrame(0);
		WebElement target = getDriver().findElement(By.xpath("html/body/div[4]/preceding::div[@id='wrapper']"));
		act.moveToElement(target).release().build().perform();

		// target.sendKeys(Keys.ENTER);
		// ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		// target);
		Thread.sleep(1000);
		getDriver().switchTo().defaultContent();

		// ---------------------------------------------
		// this works in Firefox but not in Chrome
		// (new Actions(getDriver())).dragAndDrop(source, target).release().perform();

	}

	public static boolean selectHTMLContainerPanelFromList(By locator, String value, String logStep) throws Exception {
		Actions act = new Actions(getDriver());
		Thread.sleep(6500);
		WebElement element = findWithFluintWait(locator);

		boolean flag = false;
		KeywordUtil.lastAction = "Select value by text from the list : " + value + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);

		List<WebElement> options = element.findElements(locator);
		System.out.println("Count of Elements : " + options.size());
		for (WebElement webElement : options) {
			System.out.println("get List of Containers in HTML: " + webElement.getText());
			if (webElement.getText().trim().contains(value)) {
				// System.out.println("LIST OF ELEMENTS FROM MY APPS PAGE : " +
				// webElement.getText());
				act.moveToElement(webElement).build().perform();
				act.click().build().perform();
				RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
				flag = true;
				break;
			}
		}
		return flag;
	}

	
	public static void selectValueFromDropDown(By locator, String value, String logStep) throws Exception {
		WebElement element = findWithFluintWait(locator);
		try {
			Thread.sleep(2000);
			KeywordUtil.lastAction = "Select dropdown by text : " + value + " - " + locator.toString();
			LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
            List<WebElement> li = element.findElements(By.tagName("option"));
            for (WebElement webElement : li) {
            	System.out.println("Drop Down Value : "+ webElement.getText());
				if (webElement.getText().contains(value)) {
					webElement.click();
					break;
				}
			}
               
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param locator
	 * @param value
	 * @return
	 */
	public static boolean selectByVisibleText(By locator, String value, String logStep) {
		try {
			Thread.sleep(2000);
			KeywordUtil.lastAction = "Select dropdown by text : " + value + " - " + locator.toString();
			LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
			Select sel = new Select(getDriver().findElement(locator));
			sel.selectByVisibleText(value);
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// Check whether element is selected or not
		// sel = new Select(getDriver().findElement(locator));
		// if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(value)) {
		// return true;
		// } else {
		// return false;
		// }
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	public static boolean verifyAllValuesOfDropDown(By locator, String data, String logStep) throws Throwable {
		KeywordUtil.lastAction = "Verify Dropdown all values: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		boolean flag = false;
		WebElement element = findWithFluintWait(locator);
		List<WebElement> options = element.findElements(By.tagName("option"));
		String[] allElements = data.split(",");
		String actual;
		for (int i = 0; i < allElements.length; i++) {
			LogUtil.infoLog(KeywordUtil.class, options.get(i).getText());
			LogUtil.infoLog(KeywordUtil.class, allElements[i].trim());

			actual = options.get(i).getText().trim();
			if (actual.equalsIgnoreCase(allElements[i].trim())) {
				RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyDropdownSelectedValue(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Dropdown selected option: " + data + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		Select sel = new Select(waitForVisible(locator));
		String defSelectedVal = sel.getFirstSelectedOption().getText();
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
		return defSelectedVal.trim().equals(data.trim());
	}

	/**
	 * @param locator
	 * @param size
	 * @return
	 */
	public static boolean verifyElementSize(By locator, int size, String logStep) {
		KeywordUtil.lastAction = "Verify Element size: " + size + " - " + locator.toString();
		LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
		List<WebElement> elements = getDriver().findElements(locator);
		if (elements.size() == size) {
			LogUtil.infoLog(KeywordUtil.class, "Element is Present " + size + "times");
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			return true;
		} else {
			LogUtil.infoLog(KeywordUtil.class, "Element is not Present with required size");
			LogUtil.infoLog(KeywordUtil.class, "Expected size:" + size + " but actual size: " + elements.size());
			return false;
		}
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean writeInInputCharByChar(By locator, String data, String logStep) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		String[] b = data.split("");
		for (int i = 0; i < b.length; i++) {
			element.sendKeys(b[i]);
			Thread.sleep(250);
		}
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return true;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	// /**
	// * @param check
	// * @param className
	// * @param logstep
	// * @throws Exception
	// */
	// public static void executeStep(Boolean check, String logstep) throws
	// Exception {
	// if (check) {
	// logStepPass(logstep);
	// } else {
	// logStepFail(logstep);
	// throw new Exception("Step failed - " + logstep);
	// }
	// }

	/**
	 * @param check
	 * @param className
	 * @param logstep
	 * @throws Exception
	 *             //
	 */
	// public static void verifyStep(Boolean check, String logstep) throws
	// TestStepFailedException {
	// if (check) {
	// logStepPass(logstep);
	// } else {
	// logStepFail(logstep);
	// throw new TestStepFailedException("Varification failed-->" + logstep );
	// }
	// }

	// Get Tag name and locator value of Element
	public static String getElementInfo(By locator) throws Exception {
		return " Locator: " + locator.toString();
	}

	public static String getElementInfo(WebElement element) throws Exception {
		String webElementInfo = "";
		webElementInfo = webElementInfo + "Tag Name: " + element.getTagName() + ", Locator: ["
				+ element.toString().substring(element.toString().indexOf("->") + 2);
		return webElementInfo;

	}

	/**
	 * @param time
	 * @throws InterruptedException
	 */
	public static void delay(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	/**
	 * @param locator
	 * @return
	 */
	public boolean verifyCurrentDateInput(By locator, String logStep) {
		boolean flag = false;
		WebElement element = waitForVisible(locator);
		String actual = element.getAttribute(VALUE).trim();
		DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String expected = dtFormat.format(date).trim();
		if (actual.trim().contains(expected)) {
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			flag = true;

		}
		return flag;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean uploadFilesUsingSendKeys(By locator, String data, String logStep)
			throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(System.getProperty(userDir) + "\\src\\test\\resources\\uploadFiles\\" + data);
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

		return true;
	}

	// /**
	// * @param data
	// * @param page
	// * @param fileName
	// * @return
	// * @throws IOException
	// */
	// public static boolean verifyPDFData(String data, int page, String
	// fileName) throws IOException {
	// FileInputStream fis = null;
	// String dwnFile = null;
	// try {
	//
	// String dirName = System.getProperty(userDir) +
	// "\\src\\test\\resources\\downloadFile\\";
	// File dir = new File(dirName);
	// File[] path1 = dir.listFiles();
	//
	// for (int k = 0; k < path1.length; k++) {
	// dwnFile = path1[k].toString();
	// if (dwnFile.contains(fileName)) {
	// break;
	// }
	//
	// continue;
	// }
	// File file = new File(dwnFile);
	// fis = new FileInputStream(file.getAbsolutePath());
	// PdfReader text = new PdfReader(fis);
	// String expected = PdfTextExtractor.getTextFromPage(text, page);
	//
	// String[] b = data.split(",");
	// fis.close();
	// for (int i = 0; i < b.length; i++) {
	// if (expected.contains(b[i]))
	// continue;
	// }
	// } catch (Exception e) {
	// LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
	// }
	// return true;
	// }

	/**
	 * @return
	 */
	public boolean delDirectory() {
		File delDestination = new File(System.getProperty(userDir) + "\\src\\test\\resources\\downloadFile");
		if (delDestination.exists()) {
			File[] files = delDestination.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					delDirectory();
				} else {
					files[i].delete();
				}
			}
		}
		return delDestination.delete();
	}

	// public static void hoverElement(By locator) throws InterruptedException{
	// KeywordUtil.lastAction="Hover Element: "+locator.toString();
	// LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
	//
	// WebElement element = waitForClickable(locator);
	// Point p =element.getLocation();
	// Actions builder = new Actions(getDriver());
	// builder.moveToElement(element, p.getX(), p.getY()).build().perform();
	// pause(1000);
	//
	// }
	public static boolean doubleClick(By locator, String logStep) {
		boolean result = false;
		try {
			KeywordUtil.lastAction = "Double click: " + locator.toString();
			LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
			WebElement element = getDriver().findElement(locator);
			Actions action = new Actions(getDriver()).doubleClick(element);
			action.build().perform();
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));

			result = true;

		} catch (StaleElementReferenceException e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		} catch (NoSuchElementException e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		} catch (Exception e) {
			LogUtil.infoLog("DoubleClick",
					locator.toString() + " - Element is not attached to the page document " + e.getStackTrace());
			result = false;
		}
		return result;
	}

	public static boolean switchToFrame(String frameName) {

		try {
			getDriver().switchTo().frame(frameName);
			return true;

		} catch (Exception e) {
			LogUtil.infoLog("switchToFrame", frameName + " TO FRAME FAILED" + e.getStackTrace());
			return false;
		}
	}

	public static String createZipFile() throws IOException {
		result_FolderName = result_FolderName.replace("\\", "/");
		String outputFile = result_FolderName + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(result_FolderName, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(result_FolderName + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

}// End class

class TestStepFailedException extends Exception {
	TestStepFailedException(String s) {
		super(s);
	}

}
