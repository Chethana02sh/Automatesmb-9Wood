package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import base.FilePaths;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	public static Map<String, String> getExcelData(String path,String sheetname) throws Throwable {
		Map<String, String> map=new HashedMap<>();
		List<Map<String, String>> list=new ArrayList<>();
		FileInputStream file=new FileInputStream(path);
		Workbook workbook=WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetname);
		int lastRow=sheet.getLastRowNum();
		for(int i=0;i<lastRow;i++){
			int lastCell=sheet.getRow(i).getLastCellNum();
			for(int j=0;j<lastCell;j++){
				String key=sheet.getRow(0).getCell(j).getStringCellValue();
				String value=sheet.getRow(i+1).getCell(j).getStringCellValue();
				map.put(key,value);

			}

		}
		return map;

	}

	public Object[][] readExcel(String path, String sheetname) throws Throwable {
		DataFormatter formatter=new DataFormatter();
		FileInputStream fileInputStream=new FileInputStream(path);
		XSSFWorkbook wb= new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = wb.getSheet(sheetname);
		int lastRow=sheet.getPhysicalNumberOfRows();
		Object[][] objArr=new Object[lastRow-1][1];
		for(int i=0;i<lastRow-1;i++){
			int lastCell = sheet.getRow(i).getPhysicalNumberOfCells();
			Map<String, String> map=new HashMap<String, String>();
			for(int j=0;j<lastCell;j++) {

				map.put(sheet.getRow(0).getCell(j).getStringCellValue(),
						formatter.formatCellValue(sheet.getRow(i+1).getCell(j)));
			}
			objArr[i][0]=map;
		}
		return objArr;

	}



	

}
