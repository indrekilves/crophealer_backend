package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "problemPicture")
public class ProblemPictureResource
{

    private Long id;

    private String name;

    private String pictureUrl;

}
