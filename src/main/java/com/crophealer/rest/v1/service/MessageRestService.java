package com.crophealer.rest.v1.service;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crophealer.domain.Message;
import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.MessageResourceAssembler;
import com.crophealer.rest.v1.RequestError;

@Service
public class MessageRestService extends GenericRestService
{

    public ResponseEntity < MessageResource > getMessage( Long id )
    {
        System.out.println( "getMessage - try to get for id:" + id );

        ResponseEntity < MessageResource > response;

        if ( id == null )
        {
            response = new ResponseEntity <>( HttpStatus.CONFLICT );
            return response;
        }

        Message message = Message.findMessage( id );
        if ( message == null )
        {
            response = new ResponseEntity <>( HttpStatus.NOT_FOUND );
            return response;
        }

        MessageResourceAssembler asm = new MessageResourceAssembler();
        MessageResource mr = asm.toResource( message );

        response = new ResponseEntity <>( mr, HttpStatus.OK );
        return response;

    }

    public ResponseEntity < String > createMessage( MessageResource mr )
    {
        ResponseEntity < String > response;
        if ( mr == null )
        {
            response = new ResponseEntity <>( HttpStatus.BAD_REQUEST );
            return response;
        }

        RequestError re = Message.validateResource( mr );
        if ( re != null )
        {
            response = new ResponseEntity <>( re.getValue(), HttpStatus.CONFLICT );
            return response;
        }

        Message message = Message.createFromResource( mr );

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment( message.getId().toString() )
                .build().toUri();
        headers.setLocation( location );

        response = new ResponseEntity <>( headers, HttpStatus.CREATED );
        return response;
    }

    public ResponseEntity < String > updateMessageById( Long id, MessageResource mr )
    {
        System.out.println( "updateMessage - try to update id:" + id );

        ResponseEntity < String > response;
        if ( id == null )
        {
            response = new ResponseEntity <>( HttpStatus.CONFLICT );
            return response;
        }

        if ( mr == null )
        {
            response = new ResponseEntity <>( HttpStatus.BAD_REQUEST );
            return response;
        }

        Message message = Message.findMessage( id );
        if ( message == null )
        {
            response = new ResponseEntity <>( HttpStatus.NOT_FOUND );
            return response;
        }

        RequestError re = Message.validateResource( mr );
        if ( re != null )
        {
            response = new ResponseEntity <>( re.getValue(), HttpStatus.CONFLICT );
            return response;
        }

        message.updateFromResource( mr );

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment( message.getId().toString() )
                .build().toUri();
        headers.setLocation( location );

        response = new ResponseEntity <>( headers, HttpStatus.ACCEPTED );
        return response;
    }

    public ResponseEntity < String > updateMessageStatusById( Long id, String status )
    {
        System.out.println( "updateMessageStatusById - try to update id:" + id + " status: " + status );

        ResponseEntity < String > response;
        if ( id == null )
        {
            return new ResponseEntity <>( HttpStatus.BAD_REQUEST );
        }

        Message message = Message.findMessage( id );
        if ( message == null )
        {
            response = new ResponseEntity <>( HttpStatus.NOT_FOUND );
            return response;
        }

        message.setStatus( status );
        message.persist();

        HttpHeaders headers = new HttpHeaders();
        response = new ResponseEntity <>( headers, HttpStatus.ACCEPTED );
        return response;
    }

}
