package com.crophealer.rest.conroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Plant;
import com.crophealer.rest.PlantResourceAssembler;
import com.crophealer.rest.PlantResourceList;


@Controller
@RequestMapping("/rest/plants")

public class PlantRestController {

	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<PlantResourceList> getAllPlants(){

    	PlantResourceAssembler asm = new PlantResourceAssembler();
		PlantResourceList prl = asm.toResource(Plant.findAllPlants());
		
		ResponseEntity<PlantResourceList> response; 
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;		
	}
}
