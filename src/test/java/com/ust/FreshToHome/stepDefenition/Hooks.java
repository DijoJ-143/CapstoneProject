package com.ust.FreshToHome.stepDefenition;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ust.FreshToHome.base.ReusableFunction;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.*;
import java.util.Base64;


public class Hooks {

	public static WebDriver driver;
	public ReusableFunction reusable;
	
	@Before
	public void openBrowser(Scenario scenario) {
		String appUrl = "https://www.freshtohome.com/";
		driver = new ChromeDriver();
		driver.get(appUrl);
		driver.manage().window().maximize();
        reusable=new ReusableFunction(driver);
	}
	@After
	public void closeBrowser(Scenario scenario) throws IOException {
		String filePath = System.getProperty("user.dir") + "/testresults.xlsx";
        int n = 0;
        int m = 0;
        String sheetName = "Sheet10";
        boolean scenarioFailed = scenario.isFailed();
        reusable.excelWriteForResults(filePath, sheetName, scenario, scenarioFailed);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        String imageName;
        if (scenarioFailed) {
            n++;
            imageName = "FailedImage_" + n + ".png";
        } else {
            m++;
            imageName = "PassedImage_" + m + ".png";
        }
        scenario.attach(fileContent, "image/png", imageName);
        driver.close();    
        }

}
