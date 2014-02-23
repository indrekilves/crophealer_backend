package com.crophealer.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.rest.GrowthPhaseResourceAssembler;
import com.crophealer.rest.GrowthPhaseResourceList;
import com.crophealer.rest.PlantPartResourceAssembler;
import com.crophealer.rest.PlantPartResourceList;
import com.crophealer.rest.PlantResource;
import com.crophealer.rest.PlantResourceAssembler;
import com.crophealer.rest.PlantResourceList;

@Service
public class PlantRestService {

	
	public ResponseEntity<PlantResourceList> getAllPlantsByLanguage(Languages language){
		ResponseEntity<PlantResourceList> response; 
		if (language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		PlantResourceAssembler asm = new PlantResourceAssembler();
		PlantResourceList prl = asm.toResource(Plant.findAllPlants(), language);
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;		
	}
	
	
	public ResponseEntity<PlantResource> getPlantByLanguage(Long id, Languages language)
	{   	
		ResponseEntity<PlantResource> response; 
		
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Plant plant = Plant.findPlant(id);
		if (plant == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
			
		PlantResourceAssembler asm = new PlantResourceAssembler();
    	PlantResource pr = asm.toResource(plant, language);
				
		response = new ResponseEntity<>(pr, HttpStatus.OK);
		return response;		
	}
    
    
  	public ResponseEntity<GrowthPhaseResourceList> getAllGrowthPhasesForPlantByLanguage(Long id, Languages language)
  	{
    	System.out.println("getAllGrowthPhasesForPlantByLanguage plantID: " + id + " lang:" + language);

      	// Get Plant
      	ResponseEntity<GrowthPhaseResourceList> response; 
  		
		if (id == null || language == null) {
  			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  			return response;
  		}
  			
  		Plant plant = Plant.findPlant(id);
  		if (plant == null){
  			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
  			return response;
  		}
  		System.out.println("getAllGrowthPhasesForPlant - found (Plant):" + plant.toString());
  	
  		// Get PlantPartPhase
  		Set<PlantPartPhase> ppps = plant.getPlantPartPhases();
  		if (ppps == null){
  			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
  			return response;
  		}
  		System.out.println("getAllGrowthPhasesForPlant - found (PlantProblemAreas):" + ppps.toString());
  		
  		// Get GrowthPhases
  		List <GrowthPhase> gps = new ArrayList<GrowthPhase>();
  		for (PlantPartPhase ppp : ppps) {
  			GrowthPhase gp = ppp.getGrowthPhase();
  			if (gp != null){
  				if (!gps.contains(gp)){
  					gps.add(gp);
  				}
  			}
  		}
  				
  		GrowthPhaseResourceAssembler asm = new GrowthPhaseResourceAssembler();
  		GrowthPhaseResourceList gprl = asm.toResource(gps, language);
  		
  		response = new ResponseEntity<>(gprl, HttpStatus.OK);
  		return response;		
  	}
       

	public ResponseEntity<PlantPartResourceList> getAllPlantPartsForPlantInGrowthPhaseByLanguage(Long pId, Long gpId, Languages language)
	{
    	System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage plantID: " + pId + " growthCode: " + gpId + " lang:" + language);

    	// Get Plant
    	ResponseEntity<PlantPartResourceList> response; 
		
		if (pId == null || gpId == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Plant plant = Plant.findPlant(pId);
		if (plant == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (Plant):" + plant.toString());
	
		
		// Get GrowthPhase
		GrowthPhase growthPhase = GrowthPhase.findGrowthPhase(gpId);
		if (growthPhase == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}	
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (GrowthPhase):" + growthPhase.toString());

		
		// Get PlantPartPhase
  		List<PlantPartPhase> plantPartPhases = PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhase(plant, growthPhase).getResultList();
		if (plantPartPhases == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (PlantPartPhase):" + plantPartPhases.toString());
		
		
		// Get PlantParts
		List <PlantPart> plantParts = new ArrayList<PlantPart>();
		for (PlantPartPhase plantPartPhase : plantPartPhases) {
			PlantPart plantPart = plantPartPhase.getPlantPart();
			if (plantPart != null){
				if (!plantParts.contains(plantPart)){
					plantParts.add(plantPart);
				}
			}
		}
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (Plant Parts):" + plantParts.toString());

		
    	PlantPartResourceAssembler asm = new PlantPartResourceAssembler();
		PlantPartResourceList ppr = asm.toResource(plantParts, language);
		
		response = new ResponseEntity<>(ppr, HttpStatus.OK);
		return response;		
	}


    


}
