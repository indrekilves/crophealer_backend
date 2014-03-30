package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.rest.v1.ActiveIngredientResourceAssembler;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.PlantPartPhaseProblemResource;
import com.crophealer.rest.v1.PlantPartPhaseProblemResourceAssembler;
import com.crophealer.rest.v1.PlantPartPhaseProblemResourceList;

@Service
public class PlantPartPhaseProblemRestService extends GenericRestService {


	public ResponseEntity<PlantPartPhaseProblemResourceList> getAllPlantPartPhaseProblemByLanguage(Languages language) {
		ResponseEntity<PlantPartPhaseProblemResourceList> response; 
		if (language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		PlantPartPhaseProblemResourceAssembler asm = new PlantPartPhaseProblemResourceAssembler();
		PlantPartPhaseProblemResourceList prl = asm.toResource(PlantPartPhaseProblem.findAllPlantPartPhaseProblems(), language);
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}
	
	
	public ResponseEntity<PlantPartPhaseProblemResource> getPlantPartPhaseProblemByLanguage(Long id, Languages language) {
		 System.out.println("getPlantPartPhaseProblemByLanguage - try to get for id:" + id + " lang:" + language);
			
		 ResponseEntity<PlantPartPhaseProblemResource> response;
		
		 if (id == null || language == null) {
			 response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			 return response;
		 }
		
		 PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(id);
		 if (pppProblem == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		 
			
		PlantPartPhaseProblemResourceAssembler asm = new PlantPartPhaseProblemResourceAssembler();
		PlantPartPhaseProblemResource ppppr = asm.toResource(pppProblem, language);
				
		response = new ResponseEntity<>(ppppr, HttpStatus.OK);
		return response;		
	}

	
	
	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemBySymptomsAndLanguage(String pppSymptomsCsv, Languages language) {

		System.out.println("getProblemsBySymptomsAndLanguage - try to get for symptoms:" + pppSymptomsCsv + " lang:" + language);
		ResponseEntity<PlantPartPhaseProblemResourceList> response;

		if (pppSymptomsCsv == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		List<String> pppSymptomIdStings = Arrays.asList(pppSymptomsCsv.split("\\s*,\\s*"));
		List<PlantPartPhaseProblem> pppProblems = new ArrayList<PlantPartPhaseProblem>();

		for (String plantPartPhaseSymptomIdStr : pppSymptomIdStings) {
			if (plantPartPhaseSymptomIdStr != null) {
				PlantPartPhaseSymptom pppSymptom = PlantPartPhaseSymptom.findPlantPartPhaseSymptom(Long.parseLong(plantPartPhaseSymptomIdStr));
				if (pppSymptom != null) {
					PlantPartPhaseProblem pppProblem = pppSymptom.getProblem();
					if (!pppProblems.contains(pppProblem)) {
						pppProblems.add(pppProblem);
					}
				}
			}
		}

		PlantPartPhaseProblemResourceAssembler asm = new PlantPartPhaseProblemResourceAssembler();
		PlantPartPhaseProblemResourceList drl = asm.toResource(pppProblems, language);

		response = new ResponseEntity<>(drl, HttpStatus.OK);
		return response;
	}

	
	
	
	public ResponseEntity<ActiveIngredientResourceList> getActiveIngredientsByLanguage(Long id, Languages language) {
		 System.out.println("getActiveIngredientsByLanguage - try to get for id:" + id + " lang:" + language);
		
		 ResponseEntity<ActiveIngredientResourceList> response;
		
		 if (id == null || language == null) {
			 response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			 return response;
		 }
		
		 PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(id);
		 if (pppProblem == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }
		
		 // Get ProblemActiveIngredients
		 Set<ProblemActiveIngredient> problemActiveIngredients = pppProblem.getProblemActiveIngredients();
		
		 // Get ActiveIngredients
		 List <ActiveIngredient> activeIngredients = new ArrayList<ActiveIngredient>();
		 for (ProblemActiveIngredient problemActiveIngredient :
			 problemActiveIngredients) {
			 activeIngredients.add(problemActiveIngredient.getActiveIngredient());
		 }
		
		 ActiveIngredientResourceAssembler asm = new ActiveIngredientResourceAssembler();
		 ActiveIngredientResourceList airl = asm.toResource(activeIngredients, language);
		
		 response = new ResponseEntity<>(airl, HttpStatus.OK);
		 return response;
	 }




}
