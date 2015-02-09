package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "user")
public class UserResource
{

    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    private String email;

    private String phone;

    private String expirationDate;

}
