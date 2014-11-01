package com.crophealer.rest.v1.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.Message;
import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.MessageResourceAssembler;

@Service
public class MessageRestService extends GenericRestService {

	
	
	public ResponseEntity<MessageResource> getMessage(Long id) {
		System.out.println("getMessage - try to get for id:" + id);
		
		ResponseEntity<MessageResource> response; 

		if (id == null) {
			response = new ResponseEntity<>(HttpStatus.CONFLICT);
			return response;
		}
		
		Message message = Message.findMessage(id);
		if (message == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		
		MessageResourceAssembler asm = new MessageResourceAssembler();
		MessageResource mr = asm.toResource(message);
		
		response = new ResponseEntity<>(mr, HttpStatus.OK);
		return response;	
			
	}
	
 }
