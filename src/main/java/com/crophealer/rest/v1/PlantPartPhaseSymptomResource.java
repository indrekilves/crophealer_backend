package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantPartPhaseSymptom")
public class PlantPartPhaseSymptomResource
{

    private Long id;

    private String comment;

    private Long plantPartPhaseId;

    private Long plantPartPhaseProblemId;

    private SymptomResource symptom;

}
