package com.crophealer.rest.v1.service;

import javax.persistence.TypedQuery;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.crophealer.domain.Languages;
import com.crophealer.rest.v1.controller.est.ResourceNotFoundException;
import com.crophealer.security.Users;

public class GenericRestService
{

    public Languages getEstonian()
    {
        Languages lang = null;

        TypedQuery < Languages > tq = Languages.findLanguagesesByNameEquals( "Estonian" );
        if ( tq != null && tq.getResultList().size() > 0 )
        {
            lang = tq.getSingleResult();
        }
        else
        {
            throw new ResourceNotFoundException( "Language: Estonian not found." );
        }

        return lang;
    }

    public Users getUserFromAuth()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        Users user = null;

        TypedQuery < Users > tq = Users.findUsersesByUsernameEquals( name );
        if ( tq != null && tq.getResultList().size() > 0 )
        {
            user = tq.getSingleResult();
        }
        else
        {
            throw new ResourceNotFoundException( "User not found for username: " + name );
        }

        return user;
    }

}
