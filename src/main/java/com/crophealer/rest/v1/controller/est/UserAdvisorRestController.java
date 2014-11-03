package com.crophealer.rest.v1.controller.est;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.UserAdvisor;
import com.crophealer.rest.v1.UserAdvisorResource;
import com.crophealer.rest.v1.UserAdvisorResourceAssembler;
import com.crophealer.rest.v1.UserAdvisorResourceList;


@Controller
@RequestMapping("/rest/v1/est/userAdvisors")

public class UserAdvisorRestController extends GenericController{

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserAdvisorResourceList> getAllUserAdvisors()
	{   	
		List<UserAdvisor> ual = UserAdvisor.findAllUserAdvisors();
		if (ual == null || ual.isEmpty()) throw new ResourceNotFoundException("UserAdvisors not found for ID");		
		
		UserAdvisorResourceAssembler asm = new UserAdvisorResourceAssembler();
		UserAdvisorResourceList uarl = asm.toResource(ual);

		return new ResponseEntity<>(uarl, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserAdvisorResource> getUserAdvisor(@PathVariable("id") Long id)
	{   	
		if (id == null) throw new BadRequestException("ID is missng");
		
		UserAdvisor ua = UserAdvisor.findUserAdvisor(id);
		if (ua == null) throw new ResourceNotFoundException("UserAdvisor not found for ID: " + id);		
		
		UserAdvisorResourceAssembler asm = new UserAdvisorResourceAssembler();
		UserAdvisorResource uar = asm.toResource(ua);

		return new ResponseEntity<>(uar, HttpStatus.OK);
	}
		
}
