package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProductTranslationsByLang", "findProductTranslationsByNameEquals", "findProductTranslationsByProductAndLang" })
public class ProductTranslation {

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
    private Product product;

    /**
     */
    @ManyToOne
    private Languages lang;

    /**
     */
    private String type;

    /**
     */
    private String usageRate;

    /**
     */
    private String activeIngredientRate;

    /**
     */
    private String ppc;

    /**
     */
    private String raintFastness;

    /**
     */
    private String formulation;

    /**
     */
    private String waterVolume;

    /**
     */
    private String latestUsegeTimeSprayInterval;
}
