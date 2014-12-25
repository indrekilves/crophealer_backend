package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "users")
public class UserResourceList
{

    private List < UserResource > users;

    public UserResourceList()
    {
    }

    public UserResourceList( List < UserResource > users )
    {
        this.setUsers( users );
    }

}
