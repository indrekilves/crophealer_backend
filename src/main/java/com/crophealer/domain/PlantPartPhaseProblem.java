package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantPartPhaseProblem {

    /**
     */
    private String comment;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private Set<PlantPartPhaseSymptom> symptoms = new HashSet<PlantPartPhaseSymptom>();

    /**
     */
    @ManyToOne
    private Problem problem;
}
