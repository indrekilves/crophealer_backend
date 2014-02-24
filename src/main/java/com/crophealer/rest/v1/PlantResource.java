package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "plant")
public class PlantResource {

	private Long   id; 
	private String name;
    private String description;   
    private String iconUrl;
}
