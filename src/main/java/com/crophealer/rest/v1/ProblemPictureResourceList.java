package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "problemPictures")
public class ProblemPictureResourceList
{

    private List < ProblemPictureResource > problemPictures;

    public ProblemPictureResourceList()
    {
    }

    public ProblemPictureResourceList( List < ProblemPictureResource > problemPictures )
    {
        this.setProblemPictures( problemPictures );
    }
}
