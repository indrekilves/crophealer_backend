package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
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
    private Set<ActiveIngredientTranslation> translations = new HashSet<ActiveIngredientTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
    private Set<ProblemActiveIngredient> problemActiveIngredients = new HashSet<ProblemActiveIngredient>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activeIngredient")
    private Set<ActiveIngredientProduct> activeIngredientProducts = new HashSet<ActiveIngredientProduct>();
}
