package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.service.MessageRestService;

@Controller
@RequestMapping("/rest/v1/est/messages")
public class MessageRestController extends GenericController
{

    @Autowired
    private MessageRestService messageRestService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < MessageResource > getMessage( @PathVariable("id")
    Long id )
    {
        return messageRestService.getMessage( id );
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity < String > createMessage( @RequestBody
    MessageResource mr )
    {
        return messageRestService.createMessage( mr );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}", consumes = "application/json")
    public ResponseEntity < String > updateMessage( @PathVariable("id")
    Long id, @RequestBody
    MessageResource mr )
    {
        return messageRestService.updateMessageById( id, mr );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/status", consumes = "application/json")
    public ResponseEntity < String > updateMessageStatus( @PathVariable("id")
    Long id, @RequestBody
    MessageResource mr )
    {
        String status = mr.getStatus();
        return messageRestService.updateMessageStatusById( id, status );
    }

}
