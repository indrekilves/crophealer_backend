package com.crophealer.rest.v1.controller.est;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.data.DataLoader;

@Controller
@RequestMapping("/rest/v1/est/data")
public class DataLoaderRestController {

	//@Autowired
	//private DeleteTest dt;

	@RequestMapping(method = RequestMethod.GET, value = "/load")
	public void loadSpreadsheetData() {
		//dt.getAllRequests();
		DataLoader dl = new DataLoader();
		dl.runDataLoad();
	}

}
