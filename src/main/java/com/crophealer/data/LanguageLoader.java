package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.Languages;

public class LanguageLoader 
{

	
	private SpreadSheetReader ssReader;
	
	public LanguageLoader(SpreadSheetReader ssReader)
	{
		this.ssReader = ssReader;
		this.ssReader.setActiveSheetNum(0);
	}
	
	
		
	public void loadLanguages()
	{
		ssReader.setActiveSheetNum(0);
		List<String> row = ssReader.getRowAsArray(ssReader.getLanguageRow());
		
		
		for (int i = 0; i < row.size(); i++) 
		{			
			if (!row.get(i).isEmpty())
			{
				Languages l = new Languages();
				l.setName(row.get(i));
				l.setNativeName(row.get(++i));
				l.persist();
			}
		}
	}	
	
}
