package com.crophealer.rest.v1.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.rest.v1.PaipResource;
import com.crophealer.rest.v1.PaipResourceAssembler;
import com.crophealer.rest.v1.PaipResourceList;

public class PaipRestService {

	public ResponseEntity<PaipResourceList> getAllPaips() {
		System.out.println("getAllPaips");
		
		ResponseEntity<PaipResourceList> response; 
		List<ProblemAIProduct>paips = ProblemAIProduct.findAllProblemAIProducts();
	
		PaipResourceAssembler asm = new PaipResourceAssembler();
		PaipResourceList prl = asm.toResource(paips);

		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}

	
	
	public ResponseEntity<PaipResource> getPaipByID(Long id) {
		System.out.println("getPaipByID: " + id);

		ResponseEntity<PaipResource> response; 

		if (id == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		ProblemAIProduct paip = ProblemAIProduct.findProblemAIProduct(id);
		if (paip == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		PaipResourceAssembler asm = new PaipResourceAssembler();
		PaipResource pr = asm.toResource(paip);

		response = new ResponseEntity<>(pr, HttpStatus.OK);
		return response;	
	}

}
