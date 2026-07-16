package Utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class parameterization {

	/*
	// - --- Working commented because of viewers data 
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
*/

	/* public static String getData(String fileName,int row,int cell)
	            throws EncryptedDocumentException, IOException {

	        String path =System.getProperty("user.dir")+ "/src/test/resources/"+ fileName;

	        System.out.println("Excel Path : " + path);

	        FileInputStream file =new FileInputStream(path);

	        Workbook workbook =WorkbookFactory.create(file);

	        DataFormatter formatter =new DataFormatter();

	        String value =formatter.formatCellValue(workbook.getSheet("Users").getRow(row).getCell(cell));

	        workbook.close();
	        file.close();

	        return value;
	    }
*/
	
	public static String getData(
	        String fileName,
	        String sheetName,
	        int row,
	        int cell)
	        throws EncryptedDocumentException, IOException {

	    String path = System.getProperty("user.dir")
	            + "/src/test/resources/" + fileName;

	    FileInputStream file = new FileInputStream(path);

	    Workbook workbook = WorkbookFactory.create(file);

	    DataFormatter formatter = new DataFormatter();

	    String value = formatter.formatCellValue(
	            workbook.getSheet(sheetName)
	                    .getRow(row)
	                    .getCell(cell));

	    workbook.close();
	    file.close();

	    return value;
	}
	
	
	    public static int getRowCount(
	            String fileName,
	            String sheetname)
	            throws IOException {

	        String path =System.getProperty("user.dir")+ "/src/test/resources/" + fileName;

	        FileInputStream file = new FileInputStream(path);

	        Workbook workbook =WorkbookFactory.create(file);

	        int rowCount = workbook.getSheet(sheetname).getLastRowNum();

	        workbook.close();
	        file.close();

	        return rowCount;
	    }

		
}