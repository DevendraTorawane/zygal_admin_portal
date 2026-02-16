package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class parameterization {

	//Likely for reading test data from external sources like Excel.

	public static String getData(String sheetname, int row, int cell) throws EncryptedDocumentException, IOException
	{

		String path=System.getProperty("user.dir")+ "/src/test/resources/data.xlsx";
		//"D:\\ZygalAutomation\\zygal_admin_portal\\src\\test\\resources\\data.xlsx"
		System.out.println("File Path is ==================="+path);
		FileInputStream file = new FileInputStream(path);

		String value = WorkbookFactory.create(file).getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
System.out.println(value);
return value;

	}


	public static int getRowCount(String sheetname) throws IOException {

	    String path = System.getProperty("user.dir") +
	            "\\src\\test\\resources\\data.xlsx";

	    FileInputStream file = new FileInputStream(path);

	    return WorkbookFactory.create(file).getSheet(sheetname).getLastRowNum();
	}





}