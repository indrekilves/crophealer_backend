package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "plantPartPhaseSymptom")
public class PlantPartPhaseSymptomResource {

	private Long   id; 
	private String comment;
    private PlantPartPhaseProblemResource problem;
    private PlantPartPhaseResource plantPartPhase;
    private SymptomResource symptom;

}
