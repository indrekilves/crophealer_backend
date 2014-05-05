package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Languages;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Product;
import com.crophealer.rest.v1.ActiveIngredientResource;
import com.crophealer.rest.v1.ActiveIngredientResourceAssembler;
import com.crophealer.rest.v1.ActiveIngredientResourceList;
import com.crophealer.rest.v1.ProductResourceAssembler;
import com.crophealer.rest.v1.ProductResourceList;

@Service
public class ActiveIngredientRestService extends GenericRestService {

	
	public ResponseEntity<ActiveIngredientResourceList> getAllActiveIngredientsByLanguage(Languages language) {
		ResponseEntity<ActiveIngredientResourceList> response; 
		if (language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		ActiveIngredientResourceAssembler asm = new ActiveIngredientResourceAssembler();
		ActiveIngredientResourceList airl = asm.toResource(ActiveIngredient.findAllActiveIngredients(), language);
		
		response = new ResponseEntity<>(airl, HttpStatus.OK);
		return response;		
	}
	
	
	public ResponseEntity<ActiveIngredientResource> getActiveIngredientByLanguage(Long id, Languages language)
	{   	
		ResponseEntity<ActiveIngredientResource> response; 
		
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		ActiveIngredient activeIngredient = ActiveIngredient.findActiveIngredient(id);
		if (activeIngredient == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
			
		ActiveIngredientResourceAssembler asm = new ActiveIngredientResourceAssembler();
		ActiveIngredientResource air = asm.toResource(activeIngredient, language);
				
		response = new ResponseEntity<>(air, HttpStatus.OK);
		return response;		
	}



	public ResponseEntity<ProductResourceList> getProductsForActiveIngrediensByLanguage(Long id, Languages language) {
		System.out.println("getProductsForActiveIngrediensByLanguage - try to get for id:" + id + " lang:" + language);
		
		ResponseEntity<ProductResourceList> response; 

		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		ActiveIngredient activeIngredient = ActiveIngredient.findActiveIngredient(id);
		if (activeIngredient == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		Set<ProblemAIProduct> paips = activeIngredient.getProblemProductLinks();
		
		// Get Products
		List <Product> products = new ArrayList<Product>();
		for (ProblemAIProduct problemAIProduct : paips) {
			products.add(problemAIProduct.getProduct());
		}
		
		ProductResourceAssembler asm = new ProductResourceAssembler();
		ProductResourceList prl = asm.toResource(products, language);
				
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}




 }
