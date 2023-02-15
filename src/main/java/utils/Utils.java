package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import base.Base;
import reports.ExtentManager;

public class Utils extends Base {

	public HashMap<String, String> getTestData(String sheet, String tcid) throws IOException, FilloException {

		HashMap<String, String> data = new HashMap<String, String>();
		String testDataFilePath = loadConfig().getProperty("testDataPath");

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(testDataFilePath);
		String strQuery = "Select * from "+sheet+" where TCID='" + tcid + "'";
		Recordset recordset = connection.executeQuery(strQuery);

		while (recordset.next()) {
			ArrayList<String> columnNames = recordset.getFieldNames();
			for (String name : columnNames) {
				data.put(name, recordset.getField(name));
			}
		}

		recordset.close();
		connection.close();

		return data;

	}

}
