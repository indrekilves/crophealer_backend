package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.ProductResource;
import com.crophealer.rest.v1.ProductResourceList;
import com.crophealer.rest.v1.ResellerResourceList;
import com.crophealer.rest.v1.service.ProductRestService;


@Controller
@RequestMapping("/rest/v1/est/products")

public class ProductRestController {


	
	@Autowired
	private ProductRestService productRestService;
	private Languages estonian = Languages.findLanguagesesByNameEquals("Estonian").getSingleResult();

	
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<ProductResourceList> getAllProducts(){
	    return productRestService.getAllProductsByLanguage(estonian);		
	}
	
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ProductResource> getProduct(@PathVariable("id") Long id)
	{   	
	    return productRestService.getProductByLanguage(id, estonian);		
	}
    
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/resellers")
	public ResponseEntity<ResellerResourceList> getResellerForProduct(@PathVariable("id") Long id)
	{   	
	    return productRestService.getResellerForProductByLanguage(id, estonian);		
	}
    

}
