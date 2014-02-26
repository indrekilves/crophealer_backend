package com.crophealer.rest.v1.controller.est;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.data.DataLoader;

@Controller
@RequestMapping("/rest/v1/est/data")

public class DataLoaderRestController 
{
	@RequestMapping(method = RequestMethod.GET, value="/loadall/EST")	
	public void loadDemoDatasetEST()
	{
		//DemoDataLoaderEST dataLoader = new DemoDataLoaderEST();
		//dataLoader.loadDefaultDataset();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/loadall/ENG")	
	public void loadDemoDatasetENG()
	{
		//DemoDataLoaderENG dataLoader = new DemoDataLoaderENG();
		//dataLoader.loadDefaultDataset();
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/load")	
	public void loadSpreadsheetData()
	{
		DataLoader dl = new DataLoader();
		dl.setFileName("d:\\projects\\crophealer\\data\\diseases_base_cereals.xls");
		dl.loadFromFile();
	}
}


