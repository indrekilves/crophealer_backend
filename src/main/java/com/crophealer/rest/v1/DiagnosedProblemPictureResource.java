package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "diagnosedProblemPicture")
public class DiagnosedProblemPictureResource
{

    private Long id;

    private String name;

    private String pictureUrl;
}
