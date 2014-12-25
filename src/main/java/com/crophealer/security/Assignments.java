package com.crophealer.security;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAssignmentsesByUsrAndAuthority", "findAssignmentsesByAuthority",
        "findAssignmentsesByUsr" })
public class Assignments
{

    /**
     */
    @ManyToOne
    private Users usr;

    /**
     */
    @ManyToOne
    private Authorities authority;

    public static void assignAuthorityToUser( Authorities auth, Users user )
    {
        if ( auth == null || user == null )
            return;
        Assignments a = new Assignments();
        a.setAuthority( auth );
        a.setUsr( user );
        a.persist();
    }
}
