package learnExcel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel { 

	public static void main(String[] args) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./exceldata/testSheet.xlsx");
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		System.out.println("Row number = " + rowCount);
		int colCount = sheet.getRow(rowCount).getLastCellNum();
		System.out.println("Column Number = " + colCount);
		
		for(int i=1 ;i<=rowCount ; i++) {
			XSSFRow row = sheet.getRow(i);
		for (int j =0; j<colCount; j++) {
			XSSFCell cell = row.getCell(j);
				String stringCellValue = cell.getStringCellValue();
				System.out.println(stringCellValue);	
		
			
		
				
		}
		wb.close();	
			
			
		}
			
		
		
		

	}

}
