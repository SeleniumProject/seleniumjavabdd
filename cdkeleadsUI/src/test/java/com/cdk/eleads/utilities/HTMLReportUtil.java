package com.cdk.eleads.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class HTMLReportUtil {

	static String html;

	public static String testFailTakeScreenshot(String imagePath) throws IOException {

		File src = ((TakesScreenshot) GlobalUtil.getDriver()).getScreenshotAs(OutputType.FILE);

		// File des = new File(System.getProperty("user.dir") +
		// "/FailedScreenshots/" + className + ".jpeg");
		File des = new File(imagePath);

		FileUtils.copyFile(src, des);

		// System.out.println(des.getAbsolutePath());
		// System.out.println(des.getCanonicalFile());
		System.out.println(des);

		return des.getAbsolutePath();

		// return des.toString();
	}
	
	public static String testFailMobileTakeScreenshot(String imagePath) throws IOException {

		File src = ((TakesScreenshot) GlobalUtil.getMDriver()).getScreenshotAs(OutputType.FILE);

		// File des = new File(System.getProperty("user.dir") +
		// "/FailedScreenshots/" + className + ".jpeg");
		File des = new File(imagePath);

		FileUtils.copyFile(src, des);

		// System.out.println(des.getAbsolutePath());
		// System.out.println(des.getCanonicalFile());
		System.out.println(des);

		return des.getAbsolutePath();

		// return des.toString();
	}

	public static String failStringRedColor(String stepName) {
		html = "<span style='color:red'><b>" + stepName + "</b></span>";
		return html;
	}

	public static String passStringGreenColor(String stepName) {
		html = "<span style='color:#008000'><b>" + stepName + " - PASSED" + "</b></span>";
		return html;
	}

}
