package DAO;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {

 HashMap<Integer, Integer> yardWithZipCode = new HashMap<>();
	public  HashMap<Integer,Integer> readFromExcel(){

		try {
			FileInputStream file = new FileInputStream(new File("D:\\CopartExcel.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row;
			Cell cell;
			String yard = "", zipCode = "";
			// Iterate through each rows one by one
			for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				row = sheet.getRow(rowIndex);
				if (row != null) {
					String cellValueMay = null;
					for (int colIndex = 0; colIndex < 7; colIndex++) {
						if (colIndex == 0) {
							cell = row.getCell(colIndex);
							if (cell != null) {
								// Found column and there is value in the cell.
								 yard = cell.getStringCellValue();
							}
						}
						if (colIndex == 4) {
							cell = row.getCell(colIndex);
							if (cell != null) {
								// Found column and there is value in the cell.
								 zipCode = cell.getStringCellValue();
							}
						}
						
						yardWithZipCode.put(Integer.parseInt(yard),Integer.parseInt(zipCode));
					}
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return yardWithZipCode;
	}
	}
    