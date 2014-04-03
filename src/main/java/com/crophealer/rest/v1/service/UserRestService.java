package com.crophealer.rest.v1.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.security.Users;

@Service
public class UserRestService extends GenericRestService {

	
	

	public ResponseEntity<DiagnosedProblemResourceList> getDiagnosedProblemsForUserByLanguage(Long id, Languages language) {
		System.out.println("getDiagnosedProblemsForUserByLanguage - try to get for id:" + id + " lang:" + language);
		
		ResponseEntity<DiagnosedProblemResourceList> response; 

		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Users user = Users.findUsers(id);
		if (user == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		
		DiagnosedProblemResourceAssembler dprAsm = new DiagnosedProblemResourceAssembler();
		DiagnosedProblemResourceList dprl = dprAsm.toResource(user.getDiagnosedProblems(), language);
		
		response = new ResponseEntity<>(dprl, HttpStatus.OK);
		return response;	
	}





 }
