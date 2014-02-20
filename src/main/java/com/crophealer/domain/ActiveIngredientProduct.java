package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ActiveIngredientProduct {

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private ActiveIngredient activeIngredient;

    /**
     */
    @ManyToOne
    private Product product;
}
