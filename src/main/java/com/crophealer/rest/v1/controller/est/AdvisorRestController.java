package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.MessageResourceList;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.rest.v1.UserResourceList;
import com.crophealer.rest.v1.service.MessageRestService;
import com.crophealer.rest.v1.service.UserRestService;

@Controller
@RequestMapping("/rest/v1/est/advisors")
public class AdvisorRestController extends GenericController
{

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity < UserResourceList > getAllAdvisors()
    {
        return userRestService.getUsersByRole( "ROLE_ADVISOR" );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < UserResource > getUser( @PathVariable("id")
    Long id )
    {
        return userRestService.getUser( id );
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity < String > createUser( @RequestBody
    UserResource ur )
    {
        return userRestService.createUser( ur, "ROLE_ADVISOR" );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}", consumes = "application/json")
    public ResponseEntity < String > updateAdvisor( @PathVariable("id")
    Long id, @RequestBody
    UserResource ur )
    {
        return userRestService.updateUser( id, ur );
    }

    // User messages

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/messages")
    public ResponseEntity < MessageResourceList > getMessagesForUser( @PathVariable("id")
    Long id )
    {
        return userRestService.getAllMessagesForUser( id, "", "" );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userID}/messages/{messageID}")
    public ResponseEntity < MessageResource > getMessagesForUser( @PathVariable("userID")
    Long userID, @PathVariable("messageID")
    Long messageID )
    {
        MessageRestService messageRestService = new MessageRestService();
        return messageRestService.getMessage( messageID );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/messages", params = "type")
    public ResponseEntity < MessageResourceList > getMessagesForUserWithType( @PathVariable("id")
    Long id, @RequestParam(value = "type", required = false)
    String type )
    {
        return userRestService.getAllMessagesForUser( id, type, "" );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/messages", params = "status")
    public ResponseEntity < MessageResourceList > getMessagesForUserWithStatus( @PathVariable("id")
    Long id, @RequestParam(value = "status", required = false)
    String status )
    {
        return userRestService.getAllMessagesForUser( id, "", status );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/messages", params = { "type", "status" })
    public ResponseEntity < MessageResourceList > getMessagesForUserWithParams( @PathVariable("id")
    Long id, @RequestParam(value = "type", required = false)
    String type, @RequestParam(value = "status", required = false)
    String status )
    {
        return userRestService.getAllMessagesForUser( id, type, status );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/messages", consumes = "application/json")
    public ResponseEntity < String > createMessagesForUser( @PathVariable("id")
    Long id, @RequestBody
    MessageResource mr )
    {
        if ( mr != null && id != null )
        {
            mr.setSenderID( id );
        }
        MessageRestService messageRestService = new MessageRestService();
        return messageRestService.createMessage( mr );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{senderID}/messages/{messageID}", consumes = "application/json")
    public ResponseEntity < String > updateMessageForUser( @PathVariable("senderId")
    Long senderID, @PathVariable("messageID")
    Long messageID, @RequestBody
    MessageResource mr )
    {
        if ( mr != null && senderID != null )
        {
            mr.setSenderID( senderID );
        }
        MessageRestService messageRestService = new MessageRestService();
        return messageRestService.updateMessageById( messageID, mr );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/clients")
    public ResponseEntity < UserResourceList > getClientsForUser( @PathVariable("id")
    Long id )
    {
        return userRestService.getClientsForUser( id, "APPROVED" );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/clients", params = "status")
    public ResponseEntity < UserResourceList > getClientsForUserByStatus( @PathVariable("id")
    Long id, @RequestParam(value = "status", required = false)
    String status )
    {
        return userRestService.getClientsForUser( id, status );
    }

}
