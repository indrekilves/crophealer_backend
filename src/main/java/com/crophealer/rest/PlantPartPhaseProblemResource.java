package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean 
@JsonRootName(value = "plantPartPhaseSymptom")
public class PlantPartPhaseProblemResource {

	private Long   id; 
	private String comment;
}
