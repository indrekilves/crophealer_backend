package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.rest.v1.service.UserRestService;


@Controller
@RequestMapping("/rest/v1/est/users")

public class UserRestController {


	
	@Autowired
	private UserRestService userRestService;
	
//  Not needed and too dangerous	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserResource> getUser(@PathVariable("id") Long id)
	{   	
	    return userRestService.getUser(id);		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}",  params="isLoginAllowed")
	public ResponseEntity<Boolean> isLoginAllowed(@PathVariable("id") Long id)
	{   	
	    return userRestService.isLoginAllowed(id);		
	}    
	
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Void> addUser(@RequestBody UserResource ur)
	{   	
	    return userRestService.addUser(ur);		
	}    
	
	
	
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}/diagnosedProblems")
	public ResponseEntity<DiagnosedProblemResourceList> getDiagnosedProblemsForUser(@PathVariable("id") Long id)
	{   	
    	Languages estonian = userRestService.getEstonian();
	    return userRestService.getDiagnosedProblemsForUserByLanguage(id, estonian);		
	}
    

}
