package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "paip")
public class PaipResource
{

    private Long id;

    private String comment;

    private Long problemID;

    private Long activeIngredientID;

    private Long productID;

    private String aiEffect;

    private String productEffect;
}
