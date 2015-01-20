package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.model.upload.FileUploadForm;
import com.crophealer.rest.v1.DiagnosedProblemResource;
import com.crophealer.rest.v1.service.DiagnosedProblemRestService;

@Controller
@RequestMapping("/rest/v1/est/diagnosedProblems")
public class DiagnosedProblemRestController extends GenericController
{

    @Autowired
    private DiagnosedProblemRestService diagnosedProblemRestService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < DiagnosedProblemResource > getDiagnoseProblem( @PathVariable("id")
    Long id )
    {
        return diagnosedProblemRestService
                .getDiagnosedProblemByLanguage( id, diagnosedProblemRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity < Void > saveDiagnosedProblem( @ModelAttribute("uploadForm")
    FileUploadForm uploadForm )
    {
        return diagnosedProblemRestService.saveDiagnosedProblemByLanguage( uploadForm,
                diagnosedProblemRestService.getEstonian() );
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String displayForm()
    {
        return "file_upload_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAndShowDiagnosedProblem( @ModelAttribute("uploadForm")
    FileUploadForm uploadForm )
    {
        ResponseEntity < Void > re = diagnosedProblemRestService.saveDiagnosedProblemByLanguage( uploadForm,
                diagnosedProblemRestService.getEstonian() );

        String url = re.getHeaders().getLocation().toString();
        return "redirect:" + url.replace( "/save/", "/" );
    }

}
