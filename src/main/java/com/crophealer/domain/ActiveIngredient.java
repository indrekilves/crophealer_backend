package com.crophealer.domain;

import java.util.HashSet;
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
@RooJpaActiveRecord(finders = { "findActiveIngredientsByLatinNameEquals", "findActiveIngredientsByCommentEquals" })
public class ActiveIngredient
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
    private final Set < ActiveIngredientTranslation > translations = new HashSet < ActiveIngredientTranslation >();

    /**
     * @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
     *                    private Set<ProblemActiveIngredient>
     *                    problemActiveIngredients = new
     *                    HashSet<ProblemActiveIngredient>();
     */
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
    private final Set < ProblemAIProduct > problemProductLinks = new HashSet < ProblemAIProduct >();

    public static ActiveIngredient getSingleAIByComment( String comment )
    {
        TypedQuery < ActiveIngredient > pQ = ActiveIngredient.findActiveIngredientsByCommentEqualsCustom( comment );

        if ( pQ.getResultList().size() > 0 )
        {
            return pQ.getSingleResult();
        }
        else
        {
            return null;
        }
    }

    /** CUSTOM FINDERS **/

    public static TypedQuery < ActiveIngredient > findActiveIngredientsByCommentEqualsCustom( String comment )
    {
        if ( comment == null || comment.length() == 0 )
            throw new IllegalArgumentException( "The comment argument is required" );
        EntityManager em = ActiveIngredient.entityManager();
        TypedQuery < ActiveIngredient > q = em.createQuery(
                "SELECT o FROM ActiveIngredient AS o WHERE LOWER(o.comment) = LOWER(:comment)", ActiveIngredient.class );
        q.setParameter( "comment", comment );
        return q;
    }

    public static TypedQuery < ActiveIngredient > findActiveIngredientsByLatinNameEqualsCustom( String latinName )
    {
        if ( latinName == null || latinName.length() == 0 )
            throw new IllegalArgumentException( "The latinName argument is required" );
        EntityManager em = ActiveIngredient.entityManager();
        TypedQuery < ActiveIngredient > q = em.createQuery(
                "SELECT o FROM ActiveIngredient AS o WHERE LOWER(o.latinName) = LOWER(:latinName)",
                ActiveIngredient.class );
        q.setParameter( "latinName", latinName );
        return q;
    }

}
