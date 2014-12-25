package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "fields")
public class FieldResourceList
{

    private List < FieldResource > fields;

    public FieldResourceList()
    {
    }

    public FieldResourceList( List < FieldResource > fields )
    {
        this.setFields( fields );
    }

}
