package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.PaipResourceList;
import com.crophealer.rest.v1.ProductResource;
import com.crophealer.rest.v1.ProductResourceList;
import com.crophealer.rest.v1.ResellerResourceList;
import com.crophealer.rest.v1.service.ProductRestService;


@Controller
@RequestMapping("/rest/v1/est/products")

public class ProductRestController {


	
	@Autowired
	private ProductRestService productRestService;
	
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity<ProductResourceList> getAllProducts(){
	    return productRestService.getAllProductsByLanguage(productRestService.getEstonian());		
	}
	
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ProductResource> getProduct(@PathVariable("id") Long id)
	{   	
	    return productRestService.getProductByLanguage(id, productRestService.getEstonian());		
	}
    
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/resellers")
	public ResponseEntity<ResellerResourceList> getResellerForProduct(@PathVariable("id") Long id)
	{   	
	    return productRestService.getResellerForProductByLanguage(id, productRestService.getEstonian());		
	}
    
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/paips")
	public ResponseEntity<PaipResourceList> getPaips(@PathVariable("id") Long id)
	{   	
	    return productRestService.getPaipsById(id);		
	}
    

}
