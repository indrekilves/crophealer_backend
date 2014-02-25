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
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.domain.Symptom;
import com.crophealer.rest.v1.ActiveIngredientResourceAssembler;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.ProblemResource;
import com.crophealer.rest.v1.ProblemResourceAssembler;
import com.crophealer.rest.v1.ProblemResourceList;

@Service
public class ProblemRestService extends GenericRestService {

	
	public ResponseEntity<ProblemResourceList> getAllProblemByLanguage(Languages language){
		ResponseEntity<ProblemResourceList> response; 
		if (language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		ProblemResourceAssembler asm = new ProblemResourceAssembler();
		ProblemResourceList prl = asm.toResource(Problem.findAllProblems(), language);
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;		
	}
	
	
	public ResponseEntity<ProblemResource> getProblemByLanguage(Long id, Languages language)
	{   	
		ResponseEntity<ProblemResource> response; 
		
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Problem problem = Problem.findProblem(id);
		if (problem == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
			
		ProblemResourceAssembler asm = new ProblemResourceAssembler();
		ProblemResource pr = asm.toResource(problem, language);
				
		response = new ResponseEntity<>(pr, HttpStatus.OK);
		return response;		
	}


	public ResponseEntity<ProblemResourceList> getProblemsBySymptomsAndLanguage(String symptomsCsv, Languages language) {
		System.out
				.println("getProblemsBySymptomsAndLanguage - try to get for symptoms:"
						+ symptomsCsv + " lang:" + language);
		ResponseEntity<ProblemResourceList> response;

		if (symptomsCsv == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		List<String> symptomIdStings = Arrays.asList(symptomsCsv
				.split("\\s*,\\s*"));
		List<Problem> problems = new ArrayList<Problem>();

		for (String symptomIdStr : symptomIdStings) {
			if (symptomIdStr != null) {
				Symptom s = Symptom.findSymptom(Long.parseLong(symptomIdStr));
				if (s != null) {
					List<Problem> symptomProblems = this
							.getProblemsBySymptom(s);
					if (!symptomProblems.isEmpty()) {
						for (Problem problem : symptomProblems) {
							if (!problems.contains(problem)) {
								problems.add(problem);
							}
						}
					}
				}
			}
		}

		ProblemResourceAssembler asm = new ProblemResourceAssembler();
		ProblemResourceList drl = asm.toResource(problems, language);

		response = new ResponseEntity<>(drl, HttpStatus.OK);
		return response;
	}


	private List<Problem> getProblemsBySymptom(Symptom s) {
		List<PlantPartPhaseSymptom> plantPartPhaseSymptoms = PlantPartPhaseSymptom.findPlantPartPhaseSymptomsBySymptom(s).getResultList();
    	List<Problem> problems = new ArrayList<Problem>();
        	
    	for (PlantPartPhaseSymptom pppSymptom : plantPartPhaseSymptoms) 
		{
    		PlantPartPhaseProblem pppProblem = pppSymptom.getProblem();
    		if (pppProblem != null) {
	    		Problem problem = pppProblem.getProblem();
	    		if (problem != null){
					if ( !problems.contains(problem))
					{
						problems.add(problem);
					}
	    		}
    		}

		}
    	
    	return problems;    	
	}


	public ResponseEntity<ActiveIngredientResourceList> getActiveIngredientsByLanguage(Long id, Languages language) {
		System.out.println("getActiveIngredientsByLanguage - try to get for id:" + id + " lang:" + language);
		
		ResponseEntity<ActiveIngredientResourceList> response; 

		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Problem problem = Problem.findProblem(id);
		if (problem == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		// Get ProblemActiveIngredients		
		Set<ProblemActiveIngredient> problemActiveIngredients = problem.getProblemActiveIngredients();
			
		// Get ActiveIngredients
		List <ActiveIngredient> activeIngredients = new ArrayList<ActiveIngredient>();
		for (ProblemActiveIngredient problemActiveIngredient : problemActiveIngredients) {
			activeIngredients.add(problemActiveIngredient.getActiveIngredient());
		}
		
		ActiveIngredientResourceAssembler asm = new ActiveIngredientResourceAssembler();
		ActiveIngredientResourceList airl = asm.toResource(activeIngredients, language);
				
		response = new ResponseEntity<>(airl, HttpStatus.OK);
		return response;	
	}
 }
