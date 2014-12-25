package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantPartPhaseProblems")
public class PlantPartPhaseProblemResourceList
{

    private List < PlantPartPhaseProblemResource > plantPartPhaseProblems;

    public PlantPartPhaseProblemResourceList()
    {
    }

    public PlantPartPhaseProblemResourceList( List < PlantPartPhaseProblemResource > plantPartPhaseProblems )
    {
        this.setPlantPartPhaseProblems( plantPartPhaseProblems );
    }

}