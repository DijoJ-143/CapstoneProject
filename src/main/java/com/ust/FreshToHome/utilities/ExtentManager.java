package com.ust.FreshToHome.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentManager {
	public static ExtentReports extent;
	public static ExtentSparkReporter htmlReporter;
	
	public static ExtentReports createInstance() throws IOException {
		
		String repname="TestReport-"+ getTimeStamp()+".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/testsng/"+repname);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/resources/extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User1", "Remya CB");
		extent.setSystemInfo("User2", "Dijo J");
		return extent;
	}
	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
}