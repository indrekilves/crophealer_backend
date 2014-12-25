package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crophealer.rest.v1.ProblemResource;
import com.crophealer.rest.v1.ProblemResourceList;
import com.crophealer.rest.v1.service.ProblemRestService;

@Controller
@RequestMapping("/rest/v1/est/problems")
public class ProblemRestController
{

    @Autowired
    private ProblemRestService problemRestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity < ProblemResourceList > getAllProblems()
    {
        return problemRestService.getAllProblemByLanguage( problemRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < ProblemResource > getProblem( @PathVariable("id")
    Long id )
    {
        return problemRestService.getProblemByLanguage( id, problemRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchBySymptoms")
    public ResponseEntity < ProblemResourceList > getProblemsBySymptoms(
            @RequestParam(value = "searchBySymptoms", required = false)
            String symptomsCsv )
    {
        return problemRestService.getProblemsBySymptomsAndLanguage( symptomsCsv, problemRestService.getEstonian() );
    }

}
