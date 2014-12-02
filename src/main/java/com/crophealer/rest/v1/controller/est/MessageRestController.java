package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.service.MessageRestService;


@Controller
@RequestMapping("/rest/v1/est/messages")

public class MessageRestController {


	
	@Autowired
	private MessageRestService messageRestService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<MessageResource> getUser(@PathVariable("id") Long id)
	{   	
	    return messageRestService.getMessage(id);		
	}
	

}
