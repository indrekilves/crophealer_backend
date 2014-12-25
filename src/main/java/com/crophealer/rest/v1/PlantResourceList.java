package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plants")
public class PlantResourceList
{

    private List < PlantResource > plants;

    public PlantResourceList()
    {
    }

    public PlantResourceList( List < PlantResource > plants )
    {
        this.setPlants( plants );
    }

}
