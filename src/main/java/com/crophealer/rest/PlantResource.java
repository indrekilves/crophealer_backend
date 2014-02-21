package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "plant")
public class PlantResource {

	private Long   id; 
	private String comment;
    private String iconUrl;

}
