package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "reseller")
public class ResellerResource {

	private Long   id; 
    private String name;
    private String email;
    private String phone;
    private String contactPerson;
}
