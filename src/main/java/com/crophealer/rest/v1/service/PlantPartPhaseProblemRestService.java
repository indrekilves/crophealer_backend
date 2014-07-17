package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Languages;
import com.crophealer.domain.PaipsByAiEffectComparator;
import com.crophealer.domain.PaipsByProductEffectComparator;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Symptom;
import com.crophealer.rest.v1.ActiveIngredientResourceAssembler;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.PaipResourceAssembler;
import com.crophealer.rest.v1.PaipResourceList;
import com.crophealer.rest.v1.PlantPartPhaseProblemResource;
import com.crophealer.rest.v1.PlantPartPhaseProblemResourceAssembler;
import com.crophealer.rest.v1.PlantPartPhaseProblemResourceList;
import com.crophealer.rest.v1.ProductResourceAssembler;
import com.crophealer.rest.v1.ProductResourceList;
import com.crophealer.rest.v1.SymptomResourceAssembler;
import com.crophealer.rest.v1.SymptomResourceList;

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

	
	@Deprecated
	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemByPPPSymptomsAndLanguage(String pppSymptomsCsv, Languages language) {

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
		 
		 // Get AIs by pppProblem
		 Set<ProblemAIProduct> paipsSet = pppProblem.getActiveIngredientProductLinks();
		 List<ProblemAIProduct>paips = new ArrayList<ProblemAIProduct>(paipsSet);
		 	 
		 Comparator<ProblemAIProduct> comparator = new PaipsByAiEffectComparator();
		 Collections.sort(paips, comparator);
		 
		 ActiveIngredientResourceAssembler asm = new ActiveIngredientResourceAssembler();
		 ActiveIngredientResourceList airl = asm.paipsToResource(paips, language);
		
		 response = new ResponseEntity<>(airl, HttpStatus.OK);
		 return response;
	}
	

	public ResponseEntity<PlantPartPhaseProblemResourceList> getPlantPartPhaseProblemBySymptomsAndLanguage(Long id, String symptomsCsv, Languages language) {
		System.out.println("getPlantPartPhaseProblemBySymptomsAndLanguage - try to get for PPPID: " + id + " symptoms:" + symptomsCsv + " lang:" + language);
		ResponseEntity<PlantPartPhaseProblemResourceList> response;
	
		if (id == null || symptomsCsv == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		
		// Get PlantPartPhase
		PlantPartPhase ppp = PlantPartPhase.findPlantPartPhase(id);
		if (ppp == null){
			 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 return response;
		 }

		// Get PlantPartPhaseProblems
		List<String> symptomIdStings = Arrays.asList(symptomsCsv.split("\\s*,\\s*"));
		List<PlantPartPhaseProblem> plantPartPhaseProblems = new ArrayList<PlantPartPhaseProblem>();
		
		for (String symptomIdStr : symptomIdStings) {
			if (symptomIdStr != null) {
				Symptom s = Symptom.findSymptom(Long.parseLong(symptomIdStr));
				if (s != null) {
					
					List<PlantPartPhaseSymptom> plantPartPhaseSymptoms = PlantPartPhaseSymptom.findPlantPartPhaseSymptomsByPlantPartPhaseAndSymptom(ppp, s).getResultList();
			    	for (PlantPartPhaseSymptom pppSymptom : plantPartPhaseSymptoms) 
					{
			    		PlantPartPhaseProblem pppProblem = pppSymptom.getProblem();
			    		if (pppProblem != null) {
							if ( !plantPartPhaseProblems.contains(pppProblem))
							{
								plantPartPhaseProblems.add(pppProblem);
							}
			    		}

					}			    	
				}
			}
		}
		
		PlantPartPhaseProblemResourceAssembler asm = new PlantPartPhaseProblemResourceAssembler();
		PlantPartPhaseProblemResourceList drl = asm.toResource(plantPartPhaseProblems, language);
		
		response = new ResponseEntity<>(drl, HttpStatus.OK);
		return response;
	}


	public ResponseEntity<ProductResourceList> getProductsByPlantPhaseProblemAndActiveIngredientAndLanguage(Long pppID, Long aID, Languages language) {
		System.out.println("getProductsByPlantPhaseProblemsAndActiveIngredientAndLanguage - try to get for pppId:" + pppID + " aID:" + aID + " lang:" + language);
		
		ResponseEntity<ProductResourceList> response; 
	
		if (pppID == null || aID == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		// Get PlantPartPhaseProblem
		PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(pppID);
		if (pppProblem == null){
		 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return response;
		}
		
			
		// Get ActiveIngredient
		ActiveIngredient activeIngredient = ActiveIngredient.findActiveIngredient(aID);
		if (activeIngredient == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		 
		// Get ProblemActiveIngredientProduct links 
		TypedQuery<ProblemAIProduct> tq = ProblemAIProduct.findProblemAIProductsByProblemAndActiveIngredient(pppProblem, activeIngredient);
		if (tq == null || tq.getResultList().size() <= 0) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}

		// Get Products
		List<ProblemAIProduct> paips = tq.getResultList();
		Comparator<ProblemAIProduct> comparator = new PaipsByProductEffectComparator();
		Collections.sort(paips, comparator);

		ProductResourceAssembler asm = new ProductResourceAssembler();
		ProductResourceList prl = asm.paipsToResource(paips, language);
		
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}


	public ResponseEntity<ProductResourceList> getProductsByPlantPhaseProblemAndLanguage(Long pppID, Languages language) {
		System.out.println("getProductsByPlantPhaseProblemAndLanguage - try to get for pppId:" + pppID + " lang:" + language);
		
		ResponseEntity<ProductResourceList> response; 
	
		if (pppID == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		// Get PlantPartPhaseProblem
		PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(pppID);
		if (pppProblem == null){
		 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return response;
		}
		
		 
		// Get ProblemActiveIngredientProduct links 
		Set<ProblemAIProduct> paipsSet = pppProblem.getActiveIngredientProductLinks();
		List<ProblemAIProduct>paips = new ArrayList<ProblemAIProduct>(paipsSet);

		Comparator<ProblemAIProduct> comparator = new PaipsByProductEffectComparator();
		Collections.sort(paips, comparator);

		ProductResourceAssembler asm = new ProductResourceAssembler();
		ProductResourceList prl = asm.paipsToResource(paips, language);
		
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}


	public ResponseEntity<SymptomResourceList> getSymptomsByLanguage(Long pppID, Languages language) {

		System.out.println("getSymptomsByLanguage - try to get for pppId:" + pppID + " lang:" + language);
		
		ResponseEntity<SymptomResourceList> response; 
	
		if (pppID == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		// Get PlantPartPhaseProblem
		PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(pppID);
		if (pppProblem == null){
		 response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return response;
		}
		
		// Get Symptoms 
		List <Symptom> symptoms = new ArrayList<Symptom>();
		for (PlantPartPhaseSymptom plantPartPhaseSymptom : pppProblem.getSymptoms()) {
			if (plantPartPhaseSymptom != null){
				Symptom symptom = plantPartPhaseSymptom.getSymptom();
				if (symptom != null) {
					if (!symptoms.contains(symptom)){
						symptoms.add(symptom);
					}
				}
			}
		}
		
		SymptomResourceAssembler asm = new SymptomResourceAssembler();
		SymptomResourceList srl = asm.toResource(symptoms, language);

		response = new ResponseEntity<>(srl, HttpStatus.OK);
		return response;		

	}


	public ResponseEntity<PaipResourceList> getPaipsById(Long pppID) {
		System.out.println("getPaipsById - try to get for pppId:" + pppID);
		
		ResponseEntity<PaipResourceList> response; 
	
		if (pppID == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		// Get PlantPartPhaseProblem
		PlantPartPhaseProblem pppProblem = PlantPartPhaseProblem.findPlantPartPhaseProblem(pppID);
		if (pppProblem == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
				 
		// Get ProblemActiveIngredientProduct links 
		Set<ProblemAIProduct> paipsSet = pppProblem.getActiveIngredientProductLinks();
		List<ProblemAIProduct>paips = new ArrayList<ProblemAIProduct>(paipsSet);
		
		PaipResourceAssembler asm = new PaipResourceAssembler();
		PaipResourceList prl = asm.toResource(paips);

		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}
}
