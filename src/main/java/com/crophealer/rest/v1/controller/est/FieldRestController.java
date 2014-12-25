package com.crophealer.rest.v1.controller.est;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Field;
import com.crophealer.domain.Message;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.rest.v1.FieldResource;
import com.crophealer.rest.v1.FieldResourceAssembler;
import com.crophealer.rest.v1.MessageResourceAssembler;
import com.crophealer.rest.v1.MessageResourceList;
import com.crophealer.rest.v1.service.GenericRestService;
import com.crophealer.utils.ResponseEntityUtil;

@Controller
@RequestMapping("/rest/v1/est/fields")
public class FieldRestController extends GenericController
{

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity < String > createField( @RequestBody
    FieldResource fr )
    {
        if ( fr == null )
            throw new BadRequestException( "FieldResource is missng" );
        Field field = Field.createFromResource( fr );

        return ResponseEntityUtil.CreatedFromCurrentResourceId( field.getId() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < FieldResource > getField( @PathVariable("id")
    Long id )
    {
        if ( id == null )
            throw new BadRequestException( "ID is missng" );

        Field field = Field.findField( id );
        if ( field == null )
            throw new ResourceNotFoundException( "Field not found for ID: " + id );

        FieldResourceAssembler asm = new FieldResourceAssembler();
        FieldResource ur = asm.toResource( field );

        return new ResponseEntity <>( ur, HttpStatus.OK );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}", consumes = "application/json")
    public ResponseEntity < String > updateField( @PathVariable("id")
    Long id, @RequestBody
    FieldResource fr )
    {
        if ( id == null )
            throw new BadRequestException( "ID is missng" );
        if ( fr == null )
            throw new BadRequestException( "FieldResource is missng" );

        Field field = Field.findField( id );
        if ( field == null )
            throw new ResourceNotFoundException( "Field not found for ID: " + id );

        field.updateFromResource( fr );

        return ResponseEntityUtil.AcceptedWithCurrentUri();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/messages")
    public ResponseEntity < MessageResourceList > getMessagesForField( @PathVariable("id")
    Long id )
    {
        if ( id == null )
            throw new BadRequestException( "ID is missng" );

        Field field = Field.findField( id );
        if ( field == null )
            throw new ResourceNotFoundException( "Field not found for ID: " + id );

        Set < Message > msgSet = field.getMessages();
        if ( msgSet.isEmpty() )
            throw new ResourceNotFoundException( "Messages not found for ID: " + id );

        List < Message > messages = new ArrayList < Message >();
        messages.addAll( msgSet );

        MessageResourceAssembler asm = new MessageResourceAssembler();
        MessageResourceList mrl = asm.toResource( messages );

        return new ResponseEntity <>( mrl, HttpStatus.OK );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/diagnosedProblems")
    public ResponseEntity < DiagnosedProblemResourceList > getDiagnosedProblemsForField( @PathVariable("id")
    Long id )
    {
        if ( id == null )
            throw new BadRequestException( "ID is missng" );

        Field field = Field.findField( id );
        if ( field == null )
            throw new ResourceNotFoundException( "Field not found for ID: " + id );

        Set < DiagnosedProblem > diagProbSet = field.getDiagnosedProblems();
        if ( diagProbSet.isEmpty() )
            throw new ResourceNotFoundException( "DiagnosedProblems not found for ID: " + id );

        List < DiagnosedProblem > problems = new ArrayList < DiagnosedProblem >();
        problems.addAll( diagProbSet );

        GenericRestService grs = new GenericRestService();
        DiagnosedProblemResourceAssembler asm = new DiagnosedProblemResourceAssembler();
        DiagnosedProblemResourceList dprl = asm.toResource( problems, grs.getEstonian() );

        return new ResponseEntity <>( dprl, HttpStatus.OK );
    }
}
