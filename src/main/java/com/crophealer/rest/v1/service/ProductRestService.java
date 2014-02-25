package com.crophealer.rest.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.Reseller;
import com.crophealer.rest.v1.ProductResource;
import com.crophealer.rest.v1.ProductResourceAssembler;
import com.crophealer.rest.v1.ProductResourceList;
import com.crophealer.rest.v1.ResellerResourceAssembler;
import com.crophealer.rest.v1.ResellerResourceList;

@Service
public class ProductRestService {

	
	public ResponseEntity<ProductResourceList> getAllProductsByLanguage(Languages language) {
		ResponseEntity<ProductResourceList> response; 
		if (language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		ProductResourceAssembler asm = new ProductResourceAssembler();
		ProductResourceList prl = asm.toResource(Product.findAllProducts(), language);
		
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;		
	}
	
	
	public ResponseEntity<ProductResource> getProductByLanguage(Long id, Languages language)
	{   	
		ResponseEntity<ProductResource> response; 
		
		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Product product = Product.findProduct(id);
		if (product == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
			
		ProductResourceAssembler asm = new ProductResourceAssembler();
		ProductResource pr = asm.toResource(product, language);
				
		response = new ResponseEntity<>(pr, HttpStatus.OK);
		return response;		
	}



	public ResponseEntity<ResellerResourceList> getResellerForProductByLanguage(Long id, Languages language) {
		System.out.println("getResellerForProductByLanguage - try to get for id:" + id + " lang:" + language);
		
		ResponseEntity<ResellerResourceList> response; 

		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
			
		Product product = Product.findProduct(id);
		if (product == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		// Get ProductResellers	
		Set<ProductReseller> productResellers = product.getProductResellers();
		
		// Get Resellers
		List <Reseller> resellers = new ArrayList<Reseller>();
		for (ProductReseller productReseller : productResellers) {
			resellers.add(productReseller.getReseller());
		}
		
		ResellerResourceAssembler asm = new ResellerResourceAssembler();
		ResellerResourceList prl = asm.toResource(resellers, language);
				
		response = new ResponseEntity<>(prl, HttpStatus.OK);
		return response;	
	}





 }
