package com.yalla.selenium.api.base;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelDynamic {

public static Object[][] dynamicExcel(String excelFileName) throws IOException {
			
			XSSFWorkbook wb = new XSSFWorkbook("./exceldata/"+excelFileName+".xlsx");
			XSSFSheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			System.out.println("Row number = " + rowCount);
			int colCount = sheet.getRow(rowCount).getLastCellNum();
			System.out.println("Column Number = " + colCount);
			Object[][] data =new Object[rowCount][colCount];
			for(int i=1 ;i<=rowCount ; i++) {
				XSSFRow row = sheet.getRow(i);
			for (int j =0; j<colCount; j++) {
				XSSFCell cell = row.getCell(j);
					String stringCellValue = cell.getStringCellValue();
					data[i-1][j]=stringCellValue;
					System.out.println(stringCellValue);	
					
			}
			}
			return data;
			}

	}

	

