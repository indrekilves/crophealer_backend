package com.crophealer.domain;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantPartPhaseSymptomsBySymptom", "findPlantPartPhaseSymptomsByProblem" })
public class PlantPartPhaseSymptom {

    /**
     */
    @Column(length = 1000)
    private String comment;

    /**
     */
    @ManyToOne
    private PlantPartPhaseProblem problem;

    /**
     */
    @ManyToOne
    private PlantPartPhase plantPartPhase;

    /**
     */
    @ManyToOne
    private Symptom symptom;
}
