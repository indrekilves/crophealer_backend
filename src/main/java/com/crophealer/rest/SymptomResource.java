package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean 
@JsonRootName(value = "symptom")
public class SymptomResource {

	private Long   id; 
	private String name;
    private String description;  
    private String warning;   
    private SymptomPictureResourceList pictures;
	private String comment;	
	private String country;
}
