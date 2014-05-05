
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
import com.crophealer.rest.v1.ProductResourceList;
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
    
    
//    @RequestMapping(method = RequestMethod.GET, params="searchBySymptoms")
//  	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemByPPPSymptoms( @RequestParam(value = "searchBySymptoms", required = false) String symptomsCsv){
//    	System.out.println("getPlantPartPhaseProblemBySymptoms: " + symptomsCsv);
//    	return plantPartPhaseProblemRestService.getPlantPartPhaseProblemByPPPSymptomsAndLanguage(symptomsCsv, plantPartPhaseProblemRestService.getEstonian());
//    }

	
    @RequestMapping(method = RequestMethod.GET, params={"plantPartPhaseId", "searchBySymptoms"})
  	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemBySymptoms(@RequestParam(value = "plantPartPhaseId", required = false) String idStr, 
  																								@RequestParam(value = "searchBySymptoms", required = false) String symptomsCsv){
    	Long id = Long.parseLong(idStr);
    	return plantPartPhaseProblemRestService.getPlantPartPhaseProblemBySymptomsAndLanguage(id, symptomsCsv, plantPartPhaseProblemRestService.getEstonian());
    }    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/activeIngredients")
	public ResponseEntity<ActiveIngredientResourceList> getActiveIngredients(@PathVariable("id") Long id)
	{   	
	    return plantPartPhaseProblemRestService.getActiveIngredientsByLanguage(id, plantPartPhaseProblemRestService.getEstonian());		
	}


    @RequestMapping(method = RequestMethod.GET, value="/{pppID}/activeIngredients/{aID}/products")
	public ResponseEntity<ProductResourceList> getProductsByPlantPhaseProblemAndActiveIngredient(@PathVariable("pppID") Long pppID, @PathVariable("aID") Long aID)
	{   	
	    return plantPartPhaseProblemRestService.getProductsByPlantPhaseProblemAndActiveIngredientAndLanguage(pppID, aID, plantPartPhaseProblemRestService.getEstonian());		
	}

    
    @RequestMapping(method = RequestMethod.GET, value="/{pppID}/products")
	public ResponseEntity<ProductResourceList> getProductsByPlantPhaseProblem(@PathVariable("pppID") Long pppID)
	{   	
	    return plantPartPhaseProblemRestService.getProductsByPlantPhaseProblemAndLanguage(pppID, plantPartPhaseProblemRestService.getEstonian());		
	}


}
