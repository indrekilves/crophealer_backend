package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.Symptom;
import com.crophealer.rest.v1.PlantPartPhaseSymptomResourceAssembler;
import com.crophealer.rest.v1.PlantPartPhaseSymptomResourceList;
import com.crophealer.rest.v1.SymptomResourceAssembler;
import com.crophealer.rest.v1.SymptomResourceList;

@Service
public class PlantPartPhaseRestService extends GenericRestService {

	
	public ResponseEntity<SymptomResourceList> getSymptomsByLanguage(Long id, Languages language) {
		System.out.println("getSymptomsByLanguage - try to get for PPPID: " + id + " lang:" + language);
		ResponseEntity<SymptomResourceList> response;
	
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		
		// Get PlantPartPhase
		PlantPartPhase ppp = PlantPartPhase.findPlantPartPhase(id);
		if (ppp == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		
		Set<PlantPartPhaseSymptom> pppSymptomsSet = ppp.getSymptoms();
		if (pppSymptomsSet == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		
		List<Symptom> symptoms = new ArrayList<Symptom>();
		for (PlantPartPhaseSymptom plantPartPhaseSymptom : pppSymptomsSet) {
			Symptom symptom = plantPartPhaseSymptom.getSymptom();
			if (symptom != null){
				if (!symptoms.contains(symptom))
				{
					symptoms.add(symptom);
				}
			}	
		}
		
		
		SymptomResourceAssembler asm = new SymptomResourceAssembler();
    	SymptomResourceList srl = asm.toResource(symptoms, language);
		
		response = new ResponseEntity<>(srl, HttpStatus.OK);
		return response;
	}
	
	
	
	@Deprecated
	public ResponseEntity<PlantPartPhaseSymptomResourceList> getPlantPartPhaseSymptomsByLanguage(Long id, Languages language) {
		System.out.println("getPlantPartPhaseSymptomsByLanguage - try to get for PPPID: " + id + " lang:" + language);
		ResponseEntity<PlantPartPhaseSymptomResourceList> response;
	
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		
		// Get PlantPartPhase
		PlantPartPhase ppp = PlantPartPhase.findPlantPartPhase(id);
		if (ppp == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		
		Set<PlantPartPhaseSymptom> pppSymptomsSet = ppp.getSymptoms();
		if (pppSymptomsSet == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		List<PlantPartPhaseSymptom> pppSymptoms = new ArrayList<PlantPartPhaseSymptom>(pppSymptomsSet);
		
		PlantPartPhaseSymptomResourceAssembler asm = new PlantPartPhaseSymptomResourceAssembler();
		PlantPartPhaseSymptomResourceList pppsrl = asm.toResource(pppSymptoms, language);
		
		response = new ResponseEntity<>(pppsrl, HttpStatus.OK);
		return response;
	}


}
