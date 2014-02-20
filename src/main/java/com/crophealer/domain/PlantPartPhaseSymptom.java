package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantPartPhaseSymptom {

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private PlantPartPhaseProblem problem;

    /**
     */
    @ManyToOne
    private PlantPartPhase plantPartPhase;
}
