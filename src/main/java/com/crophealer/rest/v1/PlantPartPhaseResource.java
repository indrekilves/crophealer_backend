package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "plantPartPhase")
public class PlantPartPhaseResource {

	private Long   id; 
	private String comment;
    private PlantResource plant;
    private PlantPartResource plantPart;
    private GrowthPhaseResource growthPhase;
    private PlantPartPhaseSymptomResourceList symptoms;

}
