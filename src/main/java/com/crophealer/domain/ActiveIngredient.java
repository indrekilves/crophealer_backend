package com.crophealer.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findActiveIngredientsByLatinNameEquals", "findActiveIngredientsByCommentEquals" })
public class ActiveIngredient {

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
    private final Set<ActiveIngredientTranslation> translations = new HashSet<ActiveIngredientTranslation>();

    /**
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
     private Set<ProblemActiveIngredient> problemActiveIngredients = new HashSet<ProblemActiveIngredient>();
     */
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
    private final Set<ProblemAIProduct> problemProductLinks = new HashSet<ProblemAIProduct>();
    
    
    public static ActiveIngredient getSingleAIByComment(String comment)
    {
    	TypedQuery<ActiveIngredient> pQ = ActiveIngredient.findActiveIngredientsByCommentEquals(comment);
		
		if (pQ.getResultList().size() > 0)
		{
			return pQ.getSingleResult();
		}
		else
		{
			return null;
		}
    }
    
}
