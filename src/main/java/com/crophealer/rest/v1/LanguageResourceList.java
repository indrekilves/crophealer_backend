package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "languages")
public class LanguageResourceList
{

    private List < LanguageResource > languages;

    public LanguageResourceList()
    {
    }

    public LanguageResourceList( List < LanguageResource > languages )
    {
        this.setLanguages( languages );
    }

}
