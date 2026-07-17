package Utility;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Implements TestNG listeners for customized reports/logging.
public class testReportListener implements ITestListener {
	private static final String FILE_PATH = "test-output/TestReport.xlsx";
	private static Workbook workbook = new XSSFWorkbook();
	private static Sheet sheet = workbook.createSheet("Test Results");
	private static int rowNum = 0;

	static {
		// Create Header Row
		Row headerRow = sheet.createRow(rowNum++);
		headerRow.createCell(0).setCellValue("Test Case Name");
		headerRow.createCell(1).setCellValue("Status");
		headerRow.createCell(2).setCellValue("Execution Time");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		writeResult(result, "PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		writeResult(result, "FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		writeResult(result, "SKIPPED");
	}

	@Override
	public void onFinish(ITestContext context) {
		try (FileOutputStream fileOut = new FileOutputStream(new File(FILE_PATH))) {
			workbook.write(fileOut);
			
			 fileOut.close(); workbook.close();
			 
			System.out.println("Test Report generated and the path is: " + FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeResult(ITestResult result, String status) {
		Row row = sheet.createRow(rowNum++);
		String testName = result.getMethod().getDescription();
		row.createCell(0).setCellValue(testName != null ? testName : result.getName());
		row.createCell(1).setCellValue(status);
		row.createCell(2).setCellValue(new Date().toString());
	}
}
