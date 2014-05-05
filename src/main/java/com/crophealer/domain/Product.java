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
@RooJpaActiveRecord(finders = { "findProductsByCommentEquals" })
public class Product {

    /**
     */
    private String comment;

    /**
     */
    private String pictureUrl;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @ManyToOne
    private Producer producer;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private final Set<ProductTranslation> translations = new HashSet<ProductTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private final Set<ProductReseller> productResellers = new HashSet<ProductReseller>();

    /**
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
     private Set<ActiveIngredientProduct> activeIngredientProducts = new HashSet<ActiveIngredientProduct>();
     */
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private final Set<ProblemAIProduct> activeIngredientProblemLinks = new HashSet<ProblemAIProduct>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private final Set<GrowthPhaseProduct> growthPhaseProducts = new HashSet<GrowthPhaseProduct>();

    /**
     */
    private Long efficiency;

    /**
     */
    private String raintFastness;

    public static boolean existsByName() {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    public static Product getSingleProductByComment(String comment)
    {
    	TypedQuery<Product> pQ = Product.findProductsByCommentEquals(comment);
		
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
