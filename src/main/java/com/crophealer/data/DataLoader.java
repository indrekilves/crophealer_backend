package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.Country;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;

public class DataLoader 
{
	private String fileName;
	private SpreadSheetReader ssReader;
	

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	
	
	public void loadFromFile()
	{
		switch (this.getFileType()) 
		{
		case "XLS":
			this.loadFromExcel();
			break;
		case "ODS":
			this.loadFromODS();
			break;
		default:
			break;
		}
	}


	
	private void loadFromExcel() 
	{
		this.ssReader = new ExcelReader();
		this.ssReader.setFileName(this.fileName);
		this.ssReader.loadWorkBook();
		
		// load capsulated data:
		this.loadCountries();
		this.loadLanguages();
		// AIs
		// Resellers
		// Producers
		// phases
	}
	
	private void loadCountries()
	{
		ssReader.setActiveSheetNum(0);
		List<String> row = ssReader.getRowAsArray(ssReader.getCountryRow());
		for (String s : row)
		{
			if (!s.isEmpty())
			{
				Country c = new Country();
				c.setName(s);
				c.persist();
			}
		}
	}
	
	
	private void loadLanguages()
	{
		ssReader.setActiveSheetNum(0);
		List<String> row = ssReader.getRowAsArray(ssReader.getLanguageRow());
		
		
		for (int i = 0; i < row.size(); i++) 
		{			
			if (!row.get(i).isEmpty())
			{
				Languages l = new Languages();
				l.setName(row.get(i));
				l.setNativeName(row.get(i++));
				l.persist();
			}
		}
	}

	
	
	
	
	private void loadFromODS() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	private String getFileType() 
	{
		return "XLS";
	}
	
	
	
}
