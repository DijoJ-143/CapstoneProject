package com.ust.FreshToHome.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ust.FreshToHome.base.ReusableFunction;

public class ExtentReportsListener implements ITestListener {
	static WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext context) {
		try {
			extent = ExtentManager.createInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test case passed");
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		String folderName = result.getInstanceName();
		String testName = result.getName();
		String filepath = System.getProperty("user.dir") + "/testOutput/screenshots/testng/" + folderName + "/"
				+ testName + "/" + testName + "_passed.png";
		try {
			ReusableFunction.takeScreenshots(filepath);
			test.log(Status.PASS, (Markup) test.addScreenCaptureFromPath(filepath));
//			test.log(Status.PASS,  test.addScreenCaptureFromPath(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test case Failed");
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String folderName = result.getInstanceName();
		String testName = result.getName();
		String filepath = System.getProperty("user.dir") + "/testOutput/screenshots/testng/" + folderName + "//"
				+ testName + "/" + testName + "_failed.png";
		try {
			ReusableFunction.takeScreenshots(filepath);
			test.log(Status.PASS, (Markup) test.addScreenCaptureFromPath(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test case sKIPPED");
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER));
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}