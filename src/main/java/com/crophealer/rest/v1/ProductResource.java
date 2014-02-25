package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "product")
public class ProductResource {

	private Long   id; 
	private String name;
    private String description;   
    private String warning;
	private String pictureUrl;
}
