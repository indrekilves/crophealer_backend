
package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.PlantPartPhaseProblemResource;
import com.crophealer.rest.v1.PlantPartPhaseProblemResourceList;
import com.crophealer.rest.v1.service.PlantPartPhaseProblemRestService;


@Controller
@RequestMapping("/rest/v1/est/plantPartPhaseProblems")

public class PlantPartPhaseProblemRestController {


	
	@Autowired
	private PlantPartPhaseProblemRestService plantPartPhaseProblemRestService;

	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<PlantPartPhaseProblemResourceList> getAllPlantPartPhaseProblems(){
	    return plantPartPhaseProblemRestService.getAllPlantPartPhaseProblemByLanguage(plantPartPhaseProblemRestService.getEstonian());		
	}

	
    
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<PlantPartPhaseProblemResource> getPlantPartPhaseProblem(@PathVariable("id") Long id)
	{   	
	    return plantPartPhaseProblemRestService.getPlantPartPhaseProblemByLanguage(id, plantPartPhaseProblemRestService.getEstonian());		
	}
    
    
    @RequestMapping(method = RequestMethod.GET, params="searchBySymptoms")
  	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemBySymptoms( @RequestParam(value = "searchBySymptoms", required = false) String symptomsCsv){
    	System.out.println("getPlantPartPhaseProblemBySymptoms: " + symptomsCsv);
    	return plantPartPhaseProblemRestService.getPlantPartPhaseProblemBySymptomsAndLanguage(symptomsCsv, plantPartPhaseProblemRestService.getEstonian());
    }

    @RequestMapping(method = RequestMethod.GET, params="loll")
  	public void loll( @RequestParam(value = "loll", required = false) String loll){
    	System.out.println("loll: " + loll);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/activeIngredients")
	public ResponseEntity<ActiveIngredientResourceList> getActiveIngredients(@PathVariable("id") Long id)
	{   	
	    return plantPartPhaseProblemRestService.getActiveIngredientsByLanguage(id, plantPartPhaseProblemRestService.getEstonian());		
	}
    

}
