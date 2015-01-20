package com.crophealer.rest.v1.controller.est;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.rest.v1.GrowthPhaseResourceList;
import com.crophealer.rest.v1.PlantPartPhaseResourceList;
import com.crophealer.rest.v1.PlantPartPhaseSymptomResourceList;
import com.crophealer.rest.v1.PlantPartResourceList;
import com.crophealer.rest.v1.PlantResource;
import com.crophealer.rest.v1.PlantResourceList;
import com.crophealer.rest.v1.SymptomResourceList;
import com.crophealer.rest.v1.service.PlantRestService;

@Controller
@RequestMapping("/rest/v1/est/plants")
public class PlantRestController extends GenericController
{

    @Autowired
    private PlantRestService plantRestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity < PlantResourceList > getAllPlants()
    {
        return plantRestService.getAllPlantsByLanguage( plantRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity < PlantResource > getPlant( @PathVariable("id")
    Long id )
    {
        return plantRestService.getPlantByLanguage( id, plantRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/growthphases")
    public ResponseEntity < GrowthPhaseResourceList > getAllGrowthPhasesForPlant( @PathVariable("id")
    Long id )
    {
        return plantRestService.getAllGrowthPhasesForPlantByLanguage( id, plantRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pId}/growthphases/{gpId}/plantparts")
    public ResponseEntity < PlantPartResourceList > getAllPlantPartsForPlantInGrowthPhase( @PathVariable("pId")
    Long pId, @PathVariable("gpId")
    Long gpId )
    {
        return plantRestService.getAllPlantPartsForPlantInGrowthPhaseByLanguage( pId, gpId,
                plantRestService.getEstonian() );
    }

    @Deprecated
    @RequestMapping(method = RequestMethod.GET, value = "/{pId}/growthphases/{gpId}/plantparts/{ppId}/symptoms")
    public ResponseEntity < SymptomResourceList > getAllSymptomsForPlantPartAndGrowthPhaseAndPlant(
            @PathVariable("pId")
            Long pId, @PathVariable("gpId")
            Long gpId, @PathVariable("ppId")
            Long ppId )
    {
        return plantRestService.getAllSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage( pId, gpId, ppId,
                plantRestService.getEstonian() );
    }

    @Deprecated
    @RequestMapping(method = RequestMethod.GET, value = "/{pId}/growthphases/{gpId}/plantparts/{ppId}/plantPartPhaseSymptoms")
    public ResponseEntity < PlantPartPhaseSymptomResourceList > getAllPlantPartPhaseSymptomsForPlantPartAndGrowthPhaseAndPlant(
            @PathVariable("pId")
            Long pId, @PathVariable("gpId")
            Long gpId, @PathVariable("ppId")
            Long ppId )
    {
        return plantRestService.getAllPlantPartPhaseSymptomsForPlantPartAndGrowthPhaseAndPlantByLanguage( pId, gpId,
                ppId, plantRestService.getEstonian() );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pId}/growthphases/{gpId}/plantparts/{ppId}/plantPartPhases")
    public ResponseEntity < PlantPartPhaseResourceList > getPlantPartPhasesForPlantPartAndGrowthPhaseAndPlant(
            @PathVariable("pId")
            Long pId, @PathVariable("gpId")
            Long gpId, @PathVariable("ppId")
            Long ppId )
    {
        return plantRestService.getPlantPartPhasesForPlantPartAndGrowthPhaseAndPlantByLanguage( pId, gpId, ppId,
                plantRestService.getEstonian() );
    }

}
