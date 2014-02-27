package com.crophealer.data;

import java.util.List;

public interface SpreadSheetReader 
{
	
	public void setFileName(String s);
	
	public void loadWorkBook();
	
	public Integer getLastColumn();
	
	public Integer getLastRow();
	
	public Integer getDiseaseLatinColumn();
	
	public Integer getPhaseOffset();
	
	public Integer getPlantPartOffset();
	
	public Integer getSymptomOffset();
	
	public Integer getYieldLossOffset();
	
	public Integer getActivIngredientOffset();
	
	public Integer getHeaderRow();
	
	public Integer getCountryRow();
	
	public Integer getLanguageRow();
	
	public void setActiveSheetNum(Integer activeSheetNum);
	
	public Integer getActiveSheetNum();
	
	public String getCellContent(Integer rowNum, Integer column);
		
	public List<String> getRowAsArray(Integer rowNum);
	
	public List<String> getColumnAsArray(Integer columnNum);
	
	public List<String> getColumnAsArray(Integer columnNum, Integer fromRow);
	


	
}
