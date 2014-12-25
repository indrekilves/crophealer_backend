package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.Symptom;

public class PlantPartPhaseSymptomResourceAssembler
{

    public PlantPartPhaseSymptomResource toResource( PlantPartPhaseSymptom pppSymptom, Languages language )
    {

        PlantPartPhaseSymptomResource pppSymptomResource = new PlantPartPhaseSymptomResource();
        pppSymptomResource.setId( pppSymptom.getId() );

        // PlantPartPhase - ID
        PlantPartPhase plantPartPhase = pppSymptom.getPlantPartPhase();
        Long plantPartPhaseId = plantPartPhase != null ? plantPartPhase.getId() : null;
        pppSymptomResource.setPlantPartPhaseId( plantPartPhaseId );

        // PlantPartPhaseProblem - ID
        PlantPartPhaseProblem plantPartPhaseProblem = pppSymptom.getProblem();
        Long plantPartPhaseProblemId = plantPartPhaseProblem != null ? plantPartPhaseProblem.getId() : null;
        pppSymptomResource.setPlantPartPhaseProblemId( plantPartPhaseProblemId );

        // Symptom
        Symptom symptom = pppSymptom.getSymptom();
        if ( language != null && symptom != null )
        {
            SymptomResourceAssembler sAsm = new SymptomResourceAssembler();
            pppSymptomResource.setSymptom( sAsm.toResource( symptom, language ) );
        }

        return pppSymptomResource;
    }

    public PlantPartPhaseSymptomResourceList toResource( List < PlantPartPhaseSymptom > plantPartPhaseSymptoms,
            Languages language )
    {
        if ( plantPartPhaseSymptoms == null )
            return null;

        List < PlantPartPhaseSymptomResource > plantPartPhaseSymptomResources = new ArrayList < PlantPartPhaseSymptomResource >();
        for ( PlantPartPhaseSymptom plantPartPhaseSymptom : plantPartPhaseSymptoms )
        {
            plantPartPhaseSymptomResources.add( toResource( plantPartPhaseSymptom, language ) );
        }

        return new PlantPartPhaseSymptomResourceList( plantPartPhaseSymptomResources );
    }

}
