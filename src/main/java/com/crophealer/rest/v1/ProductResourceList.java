package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "products")
public class ProductResourceList
{

    private List < ProductResource > products;

    public ProductResourceList()
    {
    }

    public ProductResourceList( List < ProductResource > products )
    {
        this.setProducts( products );
    }

}
