package com.crophealer.domain;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemTranslationsByLang", "findProblemTranslationsByProblemAndLang",
        "findProblemTranslationsByNameEquals" })
public class ProblemTranslation
{

    /**
     */
    private String name;

    /**
     */
    @Size(max = 1000)
    private String description;

    /**
     */
    @Size(max = 1000)
    private String warning;

    /**
     */
    @ManyToOne
    private Problem problem;

    /**
     */
    @ManyToOne
    private Languages lang;

    public static ProblemTranslation getSingleProblemTranslationByName( String name )
    {
        TypedQuery < ProblemTranslation > ptransQ = ProblemTranslation.findProblemTranslationsByNameEqualsCustom( name );

        if ( ptransQ.getResultList().size() > 0 )
        {
            return ptransQ.getSingleResult();
        }
        else
        {
            return null;
        }

    }

    /** CUSTOM FINDERS **/

    public static TypedQuery < ProblemTranslation > findProblemTranslationsByNameEqualsCustom( String name )
    {
        if ( name == null || name.length() == 0 )
            throw new IllegalArgumentException( "The name argument is required" );
        EntityManager em = ProblemTranslation.entityManager();
        TypedQuery < ProblemTranslation > q = em.createQuery(
                "SELECT o FROM ProblemTranslation AS o WHERE LOWER(o.name) = LOWER(:name)", ProblemTranslation.class );
        q.setParameter( "name", name );
        return q;
    }
}
