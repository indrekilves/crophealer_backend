package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.Collections;
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
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.Symptom;
import com.crophealer.rest.v1.GrowthPhaseResourceAssembler;
import com.crophealer.rest.v1.GrowthPhaseResourceList;
import com.crophealer.rest.v1.PlantPartResourceAssembler;
import com.crophealer.rest.v1.PlantPartResourceList;
import com.crophealer.rest.v1.PlantResource;
import com.crophealer.rest.v1.PlantResourceAssembler;
import com.crophealer.rest.v1.PlantResourceList;
import com.crophealer.rest.v1.SymptomResourceAssembler;
import com.crophealer.rest.v1.SymptomResourceList;
import com.crophealer.utils.GrowthPhaseComparator;

@Service
public class PlantRestService extends GenericRestService{

	
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
  		//System.out.println("getAllGrowthPhasesForPlant - found (Plant):" + plant.toString());
  	
  		// Get PlantPartPhase
  		Set<PlantPartPhase> ppps = plant.getPlantPartPhases();
  		if (ppps == null){
  			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
  			return response;
  		}
  		//System.out.println("getAllGrowthPhasesForPlant - found (PlantProblemAreas):" + ppps.toString());
  		
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
  		Collections.sort(gps, new GrowthPhaseComparator());
  				
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
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (Plant):" + plant.getComment());
	
		
		// Get GrowthPhase
		GrowthPhase growthPhase = GrowthPhase.findGrowthPhase(gpId);
		if (growthPhase == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}	
		System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (GrowthPhase):" + growthPhase.getComment());

		
		// Get PlantPartPhase
  		List<PlantPartPhase> plantPartPhases = PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhase(plant, growthPhase).getResultList();
		if (plantPartPhases == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		//System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (PlantPartPhase):" + plantPartPhases.get);
		
		
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
		//System.out.println("getAllPlantPartsForPlantInGrowthPhaseByLanguage - found (Plant Parts):" + plantParts.toString());

		
    	PlantPartResourceAssembler asm = new PlantPartResourceAssembler();
		PlantPartResourceList ppr = asm.toResource(plantParts, language);
		
		response = new ResponseEntity<>(ppr, HttpStatus.OK);
		return response;		
	}

	
	public ResponseEntity<SymptomResourceList> getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage(Long pId, Long gpId, Long ppId, Languages language) 
	{
    	System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage plantID: " + pId + " growthPhaseId: " + gpId + " plantPart: " + ppId + " Lang: " + language);

    	// Get Plant
    	ResponseEntity<SymptomResourceList> response; 
		
		if (pId == null || gpId == null || ppId == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Plant plant = Plant.findPlant(pId);
		if (plant == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage - found (Plant):" + plant.getComment());
	
		
		// GetGrowthPhase
		GrowthPhase growthPhase = GrowthPhase.findGrowthPhase(gpId);
		if (growthPhase == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage - found (GrowthPhase):" + growthPhase.getComment());

		// GetPlantPart
		PlantPart plantPart = PlantPart.findPlantPart(ppId);
		if (plantPart == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage - found (PlantPart):" + plantPart.getComment());

		
		// GetPlantPartPhase
		List<PlantPartPhase> plantPartPhases = (List<PlantPartPhase>) PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhaseAndPlantPart(plant, growthPhase, plantPart).getResultList();
		if (plantPartPhases == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		//System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage - found (plantPartPhases):" + plantPartPhases.toString());
		
		
		// GetSymptoms
		List <Symptom> symptoms = new ArrayList<Symptom>();
		for (PlantPartPhase plantPartPhase : plantPartPhases) 
		{
			Set<PlantPartPhaseSymptom> pppSymptoms = plantPartPhase.getSymptoms();
			for (PlantPartPhaseSymptom pppSymptom : pppSymptoms)
			{
				if ( !symptoms.contains(pppSymptom.getSymptom()) )
				{
					symptoms.add(pppSymptom.getSymptom());
				}
			}

		}
		//System.out.println("getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage - found (Symptoms):" + symptoms.toString());

    	
    	SymptomResourceAssembler asm = new SymptomResourceAssembler();
    	SymptomResourceList srl = asm.toResource(symptoms, language);
		
		response = new ResponseEntity<>(srl, HttpStatus.OK);
		return response;		
	}
    


}
