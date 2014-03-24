package com.crophealer.rest.v1.controller.est;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.data.DataLoader;

@Controller
@RequestMapping("/rest/v1/est/data")

public class DataLoaderRestController 
{
	
	@RequestMapping(method = RequestMethod.GET, value="/load")	
	public void loadSpreadsheetData()
	{
		DataLoader dl = new DataLoader();
		dl.runDataLoad();
	}
}


