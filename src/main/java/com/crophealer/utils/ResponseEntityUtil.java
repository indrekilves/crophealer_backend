package com.crophealer.utils;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseEntityUtil
{

    public static ResponseEntity < String > AcceptedWithCurrentUri()
    {

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        headers.setLocation( location );

        return new ResponseEntity <>( headers, HttpStatus.ACCEPTED );
    }

    public static ResponseEntity < String > CreatedFromCurrentResourceId( Long id )
    {
        if ( id == null )
            return null;

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment( id.toString() ).build().toUri();
        headers.setLocation( location );

        return new ResponseEntity <>( headers, HttpStatus.CREATED );
    }

}
