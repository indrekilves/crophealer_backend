package com.crophealer.security;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAuthoritiesesByAuthorityEquals" })
public class Authorities
{

    /**
     */
    private String authority;
}
