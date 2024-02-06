package com.wa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static final String TEST_DATA_SHEET_PATH="/Users/ranjotgill/eclipse-workspace/PomSeries/src/test/resources/testdata/opencarttestdata.xlsx";

	private static Workbook book;
	private static Sheet sheet;
	 
	
public static Object[][] getdata(String sheetname) {
	
	Object[][]data=null;
	
	try {
		FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
book=WorkbookFactory.create(ip);	
 sheet = book.getSheet(sheetname);
     try {
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(int i=0;i<sheet.getLastRowNum();i++) {
		for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
			data[i][j]=sheet.getRow(i+1).getCell(j).toString();
		}
	}
     
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return data;
	
}






}
