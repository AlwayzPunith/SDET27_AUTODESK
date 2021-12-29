package com.crm.autodesk.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Its Developed Using Apache POI Libraries
 * It is Used to Handle MicroSoft Excel Sheet  
 * @author Punith
 *
 */
public class ExcelUtility {
	/**
	 * It Reads the data Based on Below Arguments
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return data
	 * @throws Throwable 
	 * @throws Throwable
	 */

	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/TestScriptdata.xlsx");
		Workbook wb =WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		
	}
	/**
	 * used to get the last row number on specified Sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable 
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
		
	}
	
	public void setDataExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./data/TestScriptdata.xlsx");
		Workbook wb =WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(cellNum);
		cel.setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./data/TestScriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
		
}
