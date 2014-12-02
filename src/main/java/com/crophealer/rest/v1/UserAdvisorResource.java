package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean 
@JsonRootName(value = "userAdvisor")
public class UserAdvisorResource {

	private Long    id; 
	private Long    clientId;
	private Long    advisorId;
	private boolean enabled;  
	
}
