package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProblemActiveIngredient {

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private Problem problem;

    /**
     */
    @ManyToOne
    private ActiveIngredient activeIngredient;
}
