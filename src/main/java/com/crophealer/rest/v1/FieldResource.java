package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "field")
public class FieldResource
{

    private Long id;

    private String name;

    private String coordinates;

    private String priaID;

    private Long ownerID;

    private Long companyID;

}
