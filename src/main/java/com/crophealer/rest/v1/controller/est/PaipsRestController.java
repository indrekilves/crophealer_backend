package com.crophealer.rest.v1.controller.est;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.PaipResource;
import com.crophealer.rest.v1.PaipResourceList;
import com.crophealer.rest.v1.service.PaipRestService;

@Controller
@RequestMapping("/rest/v1/est/paips")
public class PaipsRestController
{

    // @Autowired
    // private PaipRestService paipRestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity < PaipResourceList > getAllPaips()
    {
        PaipRestService paipRestService = new PaipRestService();
        return paipRestService.getAllPaips();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < PaipResource > getProduct( @PathVariable("id")
    Long id )
    {
        PaipRestService paipRestService = new PaipRestService();
        return paipRestService.getPaipByID( id );
    }

}
