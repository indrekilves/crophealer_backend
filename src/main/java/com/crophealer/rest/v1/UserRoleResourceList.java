package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "userRoles")
public class UserRoleResourceList
{

    private List < UserRoleResource > userRoles;

    public UserRoleResourceList()
    {
    }

    public UserRoleResourceList( List < UserRoleResource > userRoles )
    {
        this.setUserRoles( userRoles );
    }

}
