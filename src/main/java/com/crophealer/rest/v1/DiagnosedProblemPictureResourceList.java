package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "diagnosedProblemPictures")
public class DiagnosedProblemPictureResourceList
{

    private List < DiagnosedProblemPictureResource > diagnosedProblemPictures;

    public DiagnosedProblemPictureResourceList()
    {
    }

    public DiagnosedProblemPictureResourceList( List < DiagnosedProblemPictureResource > diagnosedProblemPictures )
    {
        this.setDiagnosedProblemPictures( diagnosedProblemPictures );
    }

}
