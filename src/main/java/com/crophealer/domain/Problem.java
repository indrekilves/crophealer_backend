package com.crophealer.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemsByLatinNameEquals" })
public class Problem
{

    /**
     */
    private String latinName;

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private final Set < ProblemTranslation > translations = new HashSet < ProblemTranslation >();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private final Set < ProblemPicture > pictures = new HashSet < ProblemPicture >();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private final Set < PlantPartPhaseSymptom > plantPartPhaseProblems = new HashSet < PlantPartPhaseSymptom >();

    public static Problem getSingleProblemByLatinName( String latinName )
    {
        TypedQuery < Problem > pQ = Problem.findProblemsByLatinNameEqualsCustom( latinName );

        if ( pQ.getResultList().size() > 0 )
        {
            return pQ.getSingleResult();
        }
        else
        {
            return null;
        }
    }

    public boolean isOSRProblem()
    {
        try
        {
            for ( Iterator < PlantPartPhaseSymptom > iterator = this.plantPartPhaseProblems.iterator(); iterator
                    .hasNext(); )
            {
                PlantPartPhaseSymptom ppps = iterator.next();
                if ( ppps.getPlantPartPhase().getPlant().isOSR() )
                    return true;
            }
        }
        catch ( Exception e )
        {
            return false;
        }
        return false;
    }

    /** CUSTOM FINDERS **/

    public static TypedQuery < Problem > findProblemsByLatinNameEqualsCustom( String latinName )
    {
        if ( latinName == null || latinName.length() == 0 )
            throw new IllegalArgumentException( "The latinName argument is required" );
        EntityManager em = Problem.entityManager();
        TypedQuery < Problem > q = em.createQuery(
                "SELECT o FROM Problem AS o WHERE LOWER(o.latinName) = LOWER(:latinName)", Problem.class );
        q.setParameter( "latinName", latinName );
        return q;
    }

}
