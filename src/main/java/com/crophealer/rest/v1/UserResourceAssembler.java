package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.security.Users;

public class UserResourceAssembler
{

    public UserResource toResource( Users u )
    {

        UserResource ur = new UserResource();
        ur.setId( u.getId() );
        ur.setUsername( u.getUsername() );
        // ur.setPassword(u.getPassword());
        ur.setEmail( u.getEmail() );
        ur.setEnabled( u.getEnabled() );
        ur.setPhone( u.getPhone() );
        return ur;
    }

    public UserResourceList toResource( List < Users > ul )
    {
        if ( ul == null )
            return null;

        List < UserResource > url = new ArrayList < UserResource >();
        for ( Users u : ul )
        {
            url.add( toResource( u ) );
        }

        return new UserResourceList( url );
    }
}
