package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.MessageResourceList;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.rest.v1.service.MessageRestService;
import com.crophealer.rest.v1.service.UserRestService;


@Controller
@RequestMapping("/rest/v1/est/users")

public class UserRestController {


	
	@Autowired
	private UserRestService userRestService;

	
//  Not needed and too dangerous	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<UserResource> getUser(@PathVariable("id") Long id)
	{   	
	    return userRestService.getUser(id);		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, params="isLoginAllowedForUser")
	public ResponseEntity<Boolean> isLoginAllowed(@RequestParam("isLoginAllowedForUser") String userName)
	{   	
	    return userRestService.isLoginAllowed(userName);		
	}    
	
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> createUser(@RequestBody UserResource ur)
	{   	
	    return userRestService.createUser(ur);		
	}    
	
	
	
	
    @RequestMapping(method = RequestMethod.GET, value="/{id}/diagnosedProblems")
	public ResponseEntity<DiagnosedProblemResourceList> getDiagnosedProblemsForUser(@PathVariable("id") Long id)
	{   	
    	Languages estonian = userRestService.getEstonian();
	    return userRestService.getDiagnosedProblemsForUserByLanguage(id, estonian);		
	}
    


    @RequestMapping(method = RequestMethod.GET, value="/{id}/messages")
	public ResponseEntity<MessageResourceList> getMessagesForUser(@PathVariable("id") Long id)
	{   	
	    return userRestService.getMessagesForUser(id, "", "");		
	}
    
    @RequestMapping(method = RequestMethod.GET, value="/{userID}/messages/{messageID}")
	public ResponseEntity<MessageResource> getMessagesForUser(@PathVariable("userID") Long userID, @PathVariable("messageID") Long messageID)
	{   	
    	MessageRestService messageRestService = new MessageRestService();
    	return messageRestService.getMessage(messageID);
	}

    @RequestMapping(method = RequestMethod.GET, value="/{id}/messages", params="type")
	public ResponseEntity<MessageResourceList> getMessagesForUserWithType(@PathVariable("id") Long id,
															              @RequestParam(value = "type", required = false) String type)
	{   	
	    return userRestService.getMessagesForUser(id, type, "");		
	}

    
    @RequestMapping(method = RequestMethod.GET, value="/{id}/messages", params="status")
	public ResponseEntity<MessageResourceList> getMessagesForUserWithStatus(@PathVariable("id") Long id,
															              @RequestParam(value = "status", required = false) String status)
	{   	
	    return userRestService.getMessagesForUser(id, "", status);		
	}

    @RequestMapping(method = RequestMethod.GET, value="/{id}/messages", params={"type", "status"})
	public ResponseEntity<MessageResourceList> getMessagesForUserWithParams(@PathVariable("id") Long id,
															            	@RequestParam(value = "type", required = false) String type,
																            @RequestParam(value = "status", required = false) String status)
	{   	
	    return userRestService.getMessagesForUser(id, type, status);		
	}
    
 
    @RequestMapping(method = RequestMethod.POST, value="/{id}/messages", consumes="application/json")
   	public ResponseEntity<String> createMessagesForUser(@PathVariable("id") Long id, @RequestBody MessageResource mr)
   	{   	
    	if (mr != null && id != null){
    		mr.setSenderID(id);
    	}
    	MessageRestService messageRestService = new MessageRestService();
    	return messageRestService.createMessage(mr);		
   	}
    
    
    @RequestMapping(method = RequestMethod.POST, 
    		        value="/{senderID}/messages/{messageID}", 
    		        consumes="application/json")
	public ResponseEntity<String> updateMessageForUser(@PathVariable("senderId") Long senderID, 
												       @PathVariable("messageID") Long messageID, 
			                                           @RequestBody MessageResource mr)
	{   	
    	if (mr != null && senderID != null){
    		mr.setSenderID(senderID);
    	}
    	MessageRestService messageRestService = new MessageRestService();
	    return messageRestService.updateMessageById(messageID, mr);		
	}    
}
