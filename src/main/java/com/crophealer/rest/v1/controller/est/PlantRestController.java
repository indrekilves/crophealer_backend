package com.crophealer.rest.v1.controller.est;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.GrowthPhaseResourceList;
import com.crophealer.rest.v1.PlantPartResourceList;
import com.crophealer.rest.v1.PlantResource;
import com.crophealer.rest.v1.PlantResourceList;
import com.crophealer.rest.v1.SymptomResourceList;
import com.crophealer.rest.v1.service.PlantRestService;


@Controller
@RequestMapping("/rest/v1/est/plants")

public class PlantRestController {

	
	@Autowired
	private PlantRestService plantRestService;
	private Languages estonian = getEstonian();
	
	private Languages getEstonian() {
		TypedQuery<Languages> languages = Languages.findLanguagesesByNameEquals("Estonian");
		return languages != null ? languages.getSingleResult() : null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<PlantResourceList> getAllPlants(){
	    return plantRestService.getAllPlantsByLanguage(estonian);		
	}
	
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<PlantResource> getPlant(@PathVariable("id") Long id)
	{   	
	    return plantRestService.getPlantByLanguage(id, estonian);		
	}
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/growthphases")
  	public ResponseEntity<GrowthPhaseResourceList> getAllGrowthPhasesForPlant(@PathVariable("id") Long id){
    	return plantRestService.getAllGrowthPhasesForPlantByLanguage(id, estonian);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{pId}/growthphases/{gpId}/plantparts")
	public ResponseEntity<PlantPartResourceList> getAllPlantPartsForPlantInGrowthPhase(@PathVariable("pId") Long pId, @PathVariable("gpId") Long gpId){
		return plantRestService.getAllPlantPartsForPlantInGrowthPhaseByLanguage(pId, gpId, estonian);
	}
    
    
	@RequestMapping(method = RequestMethod.GET, value="/{pId}/growthphases/{gpId}/plantparts/{ppId}/symptoms")
	public ResponseEntity<SymptomResourceList> getAllSymptomsForPlantPartAndGrowthPhaseAndPlant(@PathVariable("pId") Long pId, @PathVariable("gpId") Long gpId, @PathVariable("ppId") Long ppId){
		return plantRestService.getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage(pId, gpId, ppId, estonian);
	}


}


