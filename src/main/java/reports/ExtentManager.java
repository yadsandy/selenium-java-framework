package reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static String extentpath = System.getProperty("user.dir") + "/target";
	public static final String EXTENT_REPORTS = "ExtentReports";
	public static String extentHtmlFile = EXTENT_REPORTS + ".html";
	public static String extentScreenShot = "screenshot.png";

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extentpath = extentpath + "//" + EXTENT_REPORTS ;
			File file = new File(extentpath);
			if (!file.exists()) {
				boolean bool = file.mkdir();
				if (bool) {
					System.out.println("Directory created successfully");
				} else {
					System.out.println("Sorry couldn't create specified directory");
				}
			}
			extentHtmlFile = file.getAbsolutePath() + "//" + extentHtmlFile;
			extentScreenShot = file.getAbsolutePath() + "//" + extentScreenShot;
			ExtentSparkReporter html = new ExtentSparkReporter(extentHtmlFile);

			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}

	public static void endReport() {
		extent.flush();
	}
}
