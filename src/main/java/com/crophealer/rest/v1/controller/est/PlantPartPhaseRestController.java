package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.PlantPartPhaseSymptomResourceList;
import com.crophealer.rest.v1.SymptomResourceList;
import com.crophealer.rest.v1.service.PlantPartPhaseRestService;

@Controller
@RequestMapping("/rest/v1/est/plantPartPhases")
public class PlantPartPhaseRestController
{

    @Autowired
    private PlantPartPhaseRestService plantPartPhaseRestService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/symptoms")
    public ResponseEntity < SymptomResourceList > getSymptoms( @PathVariable("id")
    Long id )
    {
        return plantPartPhaseRestService.getSymptomsByLanguage( id, plantPartPhaseRestService.getEstonian() );
    }

    @Deprecated
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/plantPartPhaseSymptoms")
    public ResponseEntity < PlantPartPhaseSymptomResourceList > getPlantPartPhaseSymptoms( @PathVariable("id")
    Long id )
    {
        return plantPartPhaseRestService.getPlantPartPhaseSymptomsByLanguage( id,
                plantPartPhaseRestService.getEstonian() );
    }

}
