package com.crophealer.rest.v1.controller.est;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.ProblemResource;
import com.crophealer.rest.v1.ProblemResourceList;
import com.crophealer.rest.v1.service.ProblemRestService;


@Controller
@RequestMapping("/rest/v1/est/problems")

public class ProblemRestController {


	
	@Autowired
	private ProblemRestService problemRestService;
	private Languages estonian = getEstonian();
		
	private Languages getEstonian() {
		TypedQuery<Languages> languages = Languages.findLanguagesesByNameEquals("Estonian");
		return languages != null ? languages.getSingleResult() : null;
	}

	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<ProblemResourceList> getAllProblems(){
	    return problemRestService.getAllProblemByLanguage(estonian);		
	}

	
    
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ProblemResource> getProblem(@PathVariable("id") Long id)
	{   	
	    return problemRestService.getProblemByLanguage(id, estonian);		
	}
    
    
    @RequestMapping(method = RequestMethod.GET, params="searchBySymptoms")
  	public ResponseEntity<ProblemResourceList> getProblemsBySymptoms( @RequestParam(value = "searchBySymptoms", required = false) String symptomsCsv){
    	return problemRestService.getProblemsBySymptomsAndLanguage(symptomsCsv, estonian);
    }

    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/activeIngredients")
	public ResponseEntity<ActiveIngredientResourceList> getActiveIngredients(@PathVariable("id") Long id)
	{   	
	    return problemRestService.getActiveIngredientsByLanguage(id, estonian);		
	}
    

}
