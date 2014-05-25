package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "plantPartPhase")
public class PlantPartPhaseResource {

	private Long  id; 
	private String comment;
    private Long plantId;
    private Long plantPartId;
    private Long growthPhaseId;
    private String symptomsIDs;
}
