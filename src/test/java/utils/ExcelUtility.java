package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import base.FilePaths;

public class ExcelUtility {
	
	public static String getExcelData(String sheetname, int rownum, int cellnum) {
		String cellData=null;
		try {
			FileInputStream file=new FileInputStream(FilePaths.EXECL_FILE);
			Workbook workbbook=WorkbookFactory.create(file);
			cellData=workbbook.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
			} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return cellData;
	}
	
	public static void setExceData(String sheetname, int rownum, int cellnum, String cellvalue) {
		try {
			FileInputStream file=new FileInputStream(FilePaths.EXECL_FILE);
			Workbook workbbook=WorkbookFactory.create(file);
			workbbook.getSheet(sheetname).createRow(rownum).createCell(cellnum).setCellValue(cellvalue);
			} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

}
