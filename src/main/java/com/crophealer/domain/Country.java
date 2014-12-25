package com.crophealer.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCountrysByNameEquals" })
public class Country
{

    /**
     */
    private String name;

    public static Country getSingleCountryByName( String countryStr )
    {
        TypedQuery < Country > countryQ = Country.findCountrysByNameEqualsCustom( countryStr );

        if ( countryQ.getResultList().size() > 0 )
        {
            return countryQ.getSingleResult();
        }
        else
        {
            return null;
        }
    }

    /** CUSTOM FINDERS **/

    public static TypedQuery < Country > findCountrysByNameEqualsCustom( String name )
    {
        if ( name == null || name.length() == 0 )
            throw new IllegalArgumentException( "The name argument is required" );
        EntityManager em = Country.entityManager();
        TypedQuery < Country > q = em.createQuery( "SELECT o FROM Country AS o WHERE LOWER(o.name) = LOWER(:name)",
                Country.class );
        q.setParameter( "name", name );
        return q;
    }
}
