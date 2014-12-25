package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantPartPhaseSymptoms")
public class PlantPartPhaseSymptomResourceList
{

    private List < PlantPartPhaseSymptomResource > plantPartPhaseSymptoms;

    public PlantPartPhaseSymptomResourceList()
    {
    }

    public PlantPartPhaseSymptomResourceList( List < PlantPartPhaseSymptomResource > plantPartPhaseSymptoms )
    {
        this.setPlantPartPhaseSymptoms( plantPartPhaseSymptoms );
    }

}
