package com.crophealer.rest.v1.controller.est;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Field;
import com.crophealer.rest.v1.FieldResource;
import com.crophealer.rest.v1.FieldResourceAssembler;
import com.crophealer.utils.ResponseEntityUtil;


@Controller
@RequestMapping("/rest/v1/est/fields")

public class FieldRestController {

	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> createField(@RequestBody FieldResource fr)
	{   	
		if (fr == null) throw new BadRequestException("FieldResource is missng");
		Field field = Field.createFromResource(fr);
				
		return ResponseEntityUtil.CreatedFromCurrentResourceId(field.getId());	
	}

	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<FieldResource> getField(@PathVariable("id") Long id)
	{   	
		if (id == null) throw new BadRequestException("ID is missng");
		
		Field field = Field.findField(id);
		if (field == null) throw new ResourceNotFoundException("Field not found for ID: " + id);		
		
		FieldResourceAssembler asm = new FieldResourceAssembler();
		FieldResource ur = asm.toResource(field);

		return new ResponseEntity<>(ur, HttpStatus.OK);		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/{id}", consumes="application/json")
	public ResponseEntity<String> updateField(@PathVariable("id") Long id, @RequestBody FieldResource fr)
	{   	
		if (id == null) throw new BadRequestException("ID is missng");
		if (fr == null) throw new BadRequestException("FieldResource is missng");
		
		Field field = Field.findField(id);
		if (field == null) throw new ResourceNotFoundException("Field not found for ID: " + id);		

		field.updateFromResource(fr);		
		
		return ResponseEntityUtil.AcceptedWithCurrentUri();		
	}
	
	
	
//	 	
//	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
//	public ResponseEntity<String> createMessage(@RequestBody MessageResource mr)
//	{   	
//	    return messageRestService.createMessage(mr);		
//	}    
//	
//
//	@RequestMapping(method = RequestMethod.POST, value="/{id}", consumes="application/json")
//	public ResponseEntity<String> updateMessage(@PathVariable("id") Long id, @RequestBody MessageResource mr)
//	{   	
//	    return messageRestService.updateMessageById(id, mr);		
//	}    
//	
//	@RequestMapping(method = RequestMethod.POST, value="/{id}/status", consumes="application/json")
//	public ResponseEntity<String> updateMessageStatus(@PathVariable("id") Long id, @RequestBody MessageResource mr)
//	{   	
//		String status = mr.getStatus();
//	    return messageRestService.updateMessageStatusById(id, status);		
//	}    

}
