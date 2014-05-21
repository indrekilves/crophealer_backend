package com.crophealer.rest.v1.service;


import java.net.URI;

import javax.persistence.TypedQuery;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.rest.v1.RequestError;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.rest.v1.UserResourceAssembler;
import com.crophealer.security.Assignments;
import com.crophealer.security.Authorities;
import com.crophealer.security.Users;

@Service
public class UserRestService extends GenericRestService {

	
	
	public ResponseEntity<Boolean> isLoginAllowed(String userName) {
		System.out.println("getUser - try to get for id:" + userName);
		
		ResponseEntity<Boolean> response; 

		if (userName == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Users user = null;
		TypedQuery<Users> tq = Users.findUsersesByUsernameEquals(userName);
		if (tq != null && tq.getResultList().size() > 0){
			user = tq.getSingleResult();
		}
		
		if (user == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
				
		response = new ResponseEntity<>(user.getEnabled(), HttpStatus.OK);
		return response;	
	}


	public ResponseEntity<String> createUser(UserResource ur) {
		ResponseEntity<String> response;
		if (ur == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		RequestError re = Users.validateUserResource(ur);
		if (re != null){
			// TODO: use requestError
			response = new ResponseEntity<>(re.getValue(), HttpStatus.CONFLICT);
			return response;
		}
				
		Users user = Users.createFromResource(ur);
		Authorities authority = getUserRole();				
		Assignments.assignAuthorityToUser(authority, user);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(user.getId().toString()).build().toUri();
		headers.setLocation(location);

		response = new ResponseEntity<>(headers,HttpStatus.CREATED);
		return response;
	}
	
	
	private Authorities getUserRole() {
		TypedQuery<Authorities> tq = Authorities.findAuthoritiesesByAuthorityEquals("ROLE_USER");
		if (tq == null){
			return null;
		}
 		return tq.getSingleResult();
	}


	public ResponseEntity<UserResource> getUser(Long id) {
		System.out.println("getUser - try to get for id:" + id);
		
		ResponseEntity<UserResource> response; 

		if (id == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Users user = Users.findUsers(id);
		if (user == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResource ur = asm.toResource(user);
		
		response = new ResponseEntity<>(ur, HttpStatus.OK);
		return response;	
			
	}
	

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
