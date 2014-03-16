package com.crophealer.data;

public class GenericLoader 
{
    protected SpreadSheetReader ssReader;
    protected Integer activeSheetNum = 0;
	
    public GenericLoader(){}
    
	
    public GenericLoader(SpreadSheetReader ssReader)
	{
    	this.ssReader = ssReader;
    	this.ssReader.setActiveSheetNum(this.getActiveSheetNum());
	}
	
	public void setSSReader(SpreadSheetReader ssr)
	{
		this.ssReader = ssr;
	}
	
	
	public void setActiveSheetNum(Integer actvieSheetNum)
	{
		this.activeSheetNum = actvieSheetNum;
		this.ssReader.setActiveSheetNum(actvieSheetNum);
	}
	
	
	public Integer getActiveSheetNum()
	{
		return this.activeSheetNum;
	}
	
}
