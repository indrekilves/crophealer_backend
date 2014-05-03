package com.crophealer.domain;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemAIProductsByActiveIngredient", "findProblemAIProductsByProblem" })
public class ProblemAIProduct {

    /**
     */
    @ManyToOne
    private PlantPartPhaseProblem problem;

    /**
     */
    @ManyToOne
    private ActiveIngredient activeIngredient;

    /**
     */
    @ManyToOne
    private Product product;

    /**
     */
    private String comment;

    /**
     */
    private Integer aiEffect;

    /**
     */
    private Integer productEffect;
}
