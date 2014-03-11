package com.crophealer.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader implements SpreadSheetReader 
{
	
	private String fileName;
	//private FileInputStream stream;
	private InputStream stream;
	private HSSFWorkbook workBook;
	private Integer activeSheetNum;
	
		
	public Integer getActiveSheetNum() {
		return activeSheetNum;
	}



	public void setActiveSheetNum(Integer activeSheetNum) {
		this.activeSheetNum = activeSheetNum;
	}



	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}



	@Override
	public Integer getLastColumn() {
		return 21;
	}



	@Override
	public Integer getLastRow() {
		return 53;
	}



	@Override
	public Integer getDiseaseLatinColumn() {
		return 7;
	}



	@Override
	public Integer getPhaseOffset() {
		return 1;
	}



	@Override
	public Integer getPlantPartOffset() {
		// TODO Auto-generated method stub
		return 2;
	}



	@Override
	public Integer getSymptomOffset() {
		// TODO Auto-generated method stub
		return 3;
	}



	@Override
	public Integer getYieldLossOffset() {
		// TODO Auto-generated method stub
		return 4;
	}



	@Override
	public Integer getActivIngredientOffset() {
		// TODO Auto-generated method stub
		return 5;
	}



	@Override
	public Integer getHeaderRow() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	
	
	@Override
	public Integer getCountryRow() {
		// TODO Auto-generated method stub
		return 2;
	}



	@Override
	public Integer getLanguageRow() {
		// TODO Auto-generated method stub
		return 3;
	}


	public void loadWorkBook()
	{
		try 
		{
			//this.stream = new FileInputStream(new File(this.fileName));
			this.stream = DataLoader.class.getResourceAsStream(this.fileName);
			
			this.workBook = new HSSFWorkbook(this.stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public String getCellContent(Integer rowNum, Integer column)
	{
		String cellStr = "";
		try
		{
			HSSFSheet sheet = this.workBook.getSheetAt(this.getActiveSheetNum());
			Row row = sheet.getRow(rowNum);    
			Cell cell = row.getCell(column);
			cellStr = cell.getStringCellValue();
		}
		catch(Exception e)
		{}
		return cellStr;
	}
	
	
	
	public List<String> getRowAsArray(Integer rowNum)
	{
		List<String> rowArr = new ArrayList<String>();
		HSSFSheet sheet = this.workBook.getSheetAt(this.getActiveSheetNum());
		Row row = sheet.getRow(rowNum);    
		
		for (int i = 0; i < this.getLastColumn(); i++) {
			Cell c = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
			rowArr.add(c.getStringCellValue());
		}
		
		return rowArr;
	}
	
	
	
	public List<String> getColumnAsArray(Integer columnNum)
	{
		List<String> colArr = new ArrayList<String>();
		HSSFSheet sheet = this.workBook.getSheetAt(this.getActiveSheetNum());
		
		for (int i = 0; i <= sheet.getLastRowNum(); i++) 
		{
			Row row = sheet.getRow(i);
			if (row != null)
			{
				Cell c = row.getCell(columnNum, Row.CREATE_NULL_AS_BLANK);
				colArr.add(c.getStringCellValue());
			}
			else
			{
				colArr.add("");
			}
		}
		
		return colArr;
	}
	
	
	public List<String> getColumnAsArray(Integer columnNum, Integer fromRow)
	{
		List<String> colArr = new ArrayList<String>();
		List<String> fullArr = this.getColumnAsArray(columnNum);		
		
		for (int i = fromRow; i < fullArr.size(); i++) 
		{
			colArr.add(fullArr.get(i));
		}
		
		return colArr;
	}
	
	
	
	
	public List<String> getColumnAsArray(Integer columnNum, Integer fromRow, Integer toRow)
	{
		List<String> colArr = new ArrayList<String>();
		List<String> fullArr = this.getColumnAsArray(columnNum);		
		
		for (int i = fromRow; (i < fullArr.size() && i <= toRow ); i++) 
		{
			colArr.add(fullArr.get(i));
		}
		
		return colArr;
	}
	
	

	public void test()
	{
		try 
		{

			FileInputStream file = new FileInputStream(new File(this.fileName));

			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			while(rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();

				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while(cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();

					switch(cell.getCellType()) 
					{
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
					}
				}
				System.out.println("");
			}

			file.close();
			FileOutputStream out = new FileOutputStream(new File(this.fileName));
			workbook.write(out);
			out.close();

		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}






}
