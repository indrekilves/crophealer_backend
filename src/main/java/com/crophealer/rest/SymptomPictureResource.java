package com.crophealer.rest;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "symptomPicture")
public class SymptomPictureResource {

	private Long   id; 
	private String name;	
	private String pictureUrl;
}
