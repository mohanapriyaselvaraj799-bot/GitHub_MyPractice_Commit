package genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String toReadDatafromExcelFileUtility(String sheetname, int rowno, int cellno) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptNinzaCRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		String data = sh.getRow(rowno).getCell(cellno).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int toGetLastRowNum(String sheetname) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/TestScriptNinzaCRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		int lastrowNo = sh.getLastRowNum();
		return lastrowNo;
		/*
		 * for (int i = 1; i <= lastrowNo; i++) { String brand =
		 * sh.getRow(i).getCell(0).getStringCellValue(); String mobile =
		 * sh.getRow(i).getCell(1).getStringCellValue(); return mobile;
		 */
	}

}
