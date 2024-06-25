package com.ust.FreshToHome.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ust.FreshToHome.utilities.FileIO;

import io.cucumber.java.Scenario;

//import fileUtilities.FileIO;

/**
 * This class is used to invoke the browser and perform various operations on
 * the webpage.
 */
public class ReusableFunction {
	private static WebDriver driver;
	private WebDriverWait wait;
	public static Properties properties;
	public static String browser_choice;

	public ReusableFunction(WebDriver driver) {
		this.driver = driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		properties = FileIO.getProperties();
	}

	
	/**
	 * Waits for the element to be displayed on the page.
	 * 
	 * @param element The WebElement to wait for.
	 */
	public void waitForElementToDisplay(WebElement element) {
		try {
			// Waiting for the visibility of the element
			WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(1000));
		//WebDriver	wait1 = new WebDriverWait(driver, Duration.ofSeconds(1000));
			wait1.until(ExpectedConditions.visibilityOf(element));
		} catch (StaleElementReferenceException e) {
			// If the element is no longer attached to the DOM
			System.err.println("Stale element reference exception occurred: " + e.getMessage());
		} catch (WebDriverException e) {
			// If a WebDriverException occurs
			System.err.println("WebDriver exception occurred: " + e.getMessage());
		}
	}
	
	

	public void waitForElementToBeClicable(WebElement element) {
		try {
			// Waiting for the visibility of the element
			waitForElementToBeClicable(element);
		} catch (StaleElementReferenceException e) {
			// If the element is no longer attached to the DOM
			System.err.println("Stale element reference exception occurred: " + e.getMessage());
		} catch (WebDriverException e) {
			// If a WebDriverException occurs
			System.err.println("WebDriver exception occurred: " + e.getMessage());
		}
	}

	/**
	 * Sets text to an input field.
	 * 
	 * @param element The WebElement representing the input field.
	 * @param text    The text to set.
	 */
	public void setTextToInputField(WebElement element, String text) {
		try {
			waitForElementToDisplay(element);
			element.clear(); // Clearing any existing text
			element.sendKeys(text, Keys.ENTER);
			// Entering new text
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		}
	}
	
	public void manageWindowHandles(WebDriver webDriver) {
		 for (String windowHandle : webDriver.getWindowHandles()) {
	            webDriver.switchTo().window(windowHandle);
	        }
	}
	
	
	public void setTextToInputFieldwithoutEnter(WebElement element, String text) {
		try {
			waitForElementToDisplay(element);
			element.clear(); // Clearing any existing text
			element.sendKeys(text);
			// Entering new text
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		}
	}

	/**
	 * Clicks on an element.
	 * 
	 * @param element The WebElement to click on.
	 */
	public void clickOnElement(WebElement element) {
		try {
			waitForElementToDisplay(element);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
		}
	}

	/**
	 * Gets text from an element.
	 * 
	 * @param element The WebElement to get text from.
	 * @return The text of the element.
	 */
	public String getText(WebElement element) {
		try {
			waitForElementToDisplay(element);
			return element.getText();
		} catch (NoSuchElementException e) {
			System.err.println("Element not found: " + e.getMessage());
			return "";
		}
	}

	/**
	 * Reads data from an Excel file.
	 * 
	 * @param path      The path to the Excel file.
	 * @param sheetname The name of the sheet to read.
	 * @return A 2D array containing the data from the Excel sheet.
	 * @throws IOException If an I/O error occurs.
	 */
	public String[][] readExcelFile(String path, String sheetname) {
		try {
			FileInputStream input = new FileInputStream(System.getProperty("user.dir") + properties.getProperty(path));
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheet(sheetname);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			DataFormatter dFormatter = new DataFormatter();
			String[][] data = new String[rowCount][columnCount];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					data[i][j] = dFormatter.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}
			return data;
		} catch (IOException e) {
			System.err.println("An error occurred while reading the Excel file: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Gets the value of the specified attribute of the element.
	 * 
	 * @param element The WebElement.
	 * @param name    The name of the attribute.
	 * @return The value of the attribute.
	 */
	public String getAttribute(WebElement element, String name) {
		try {
			waitForElementToDisplay(element);
			return element.getAttribute(name);
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Scrolls the page to bring the element into view.
	 * 
	 * @param element The WebElement to scroll to.
	 */
	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}

	/**
	 * Pauses the execution for 2 seconds.
	 */

	/**
	 * Pauses the execution for the specified number of seconds.
	 * 
	 * @param seconds The number of seconds to pause.
	 */
	public void delaySeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.err.println("Delay interrupted: " + e.getMessage());
			//Thread.currentThread().interrupt();
		}
	}

	/**
	 * Waits for an alert message to be present.
	 */
	public void delayForAlertMessage() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.err.println("An error occurred while waiting for alert message: " + e.getMessage());
		}
	}


	public void clearText(WebElement element) {
		waitForElementToDisplay(element);
		element.click();

		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.BACK_SPACE);

	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Writes the test results to an Excel file.
	 *
	 * @param filePath      The file path of the Excel file
	 * @param sheetName     The name of the sheet in the Excel file
	 * @param scenario      The scenario object representing the test case
	 * @param scenarioFailed    A boolean indicating whether the scenario failed or not
	 */
public void excelWriteForResults(String filePath, String sheetName, Scenario scenario, boolean scenarioFailed) {
	Workbook workbook = null;
	Sheet sheet = null;

	try {
		File file = new File(filePath);
		if (!file.exists()) {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet(sheetName);
			System.out.println("Created new Excel file and sheet.");
		} else {
			FileInputStream inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
				System.out.println("Created new sheet.");
			}
		}

		if (sheet != null) {
			int rowCount = sheet.getLastRowNum();
			if (rowCount == 0) {
				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("Scenario Name");
				headerRow.createCell(1).setCellValue("Results");
			}

			Row row = sheet.createRow(rowCount + 1);
			row.createCell(0).setCellValue(scenario.getName());
			String result = (scenarioFailed ? "Failed" : "Passed");
			row.createCell(1).setCellValue(result);

			CellStyle style = workbook.createCellStyle();
			if (result.equals("Failed")) {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			} else {
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			}
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Cell cell = row.getCell(1);
			if (cell == null) {
				cell = row.createCell(1);
			}
			cell.setCellValue(result);
			cell.setCellStyle(style);

			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} else {
			System.out.println("Sheet is null, cannot write test results to Excel");
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
} 

/**
 * Takes a screenshot and saves it with the provided file path.
 * 
 * @param filepath The path where the screenshot will be saved.
 */
public static void takeScreenshots(String filepath) {
	TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
	File srcFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(srcFile, new File(filepath));
	} catch (IOException e) {
		System.err.println("An error occurred while taking screenshot: " + e.getMessage());
	}
}

/**
 * Takes a screenshot and saves it with a timestamped file name in the
 * Screenshotss directory.
 */
public static void takeScreenshotcucmber() {
	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
	String fileName = "_cucumberFailed_" + timestamp + "_screenshot.png";
	String filePath = System.getProperty("user.dir") + "/cucumberFailed/" + fileName;
	takeScreenshots(filePath);
}
/********* Report fail Test **********/
public static void reportFail(String message) {
	Assert.fail("Testcase Failed: " + message);
}


}