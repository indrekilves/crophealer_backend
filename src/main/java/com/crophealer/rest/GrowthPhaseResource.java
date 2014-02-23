package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "growthPhase")
public class GrowthPhaseResource {

	private Long   id; 
	private String name;
    private String description;   
    private String iconUrl;

}
