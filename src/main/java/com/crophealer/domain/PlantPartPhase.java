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
@RooJpaActiveRecord(finders = { "findPlantPartPhasesByPlantAndGrowthPhase", "findPlantPartPhasesByPlantAndGrowthPhaseAndPlantPart" })
public class PlantPartPhase {

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private Plant plant;

    /**
     */
    @ManyToOne
    private PlantPart plantPart;

    /**
     */
    @ManyToOne
    private GrowthPhase growthPhase;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPartPhase")
    private Set<PlantPartPhaseSymptom> symptoms = new HashSet<PlantPartPhaseSymptom>();
}
