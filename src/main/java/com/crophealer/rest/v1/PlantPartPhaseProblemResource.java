package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean 
@JsonRootName(value = "plantPartPhaseProblem")
public class PlantPartPhaseProblemResource {

	private Long            id; 
	private String          comment;
	private ProblemResource problem; 
}
