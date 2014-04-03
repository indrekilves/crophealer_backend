package com.crophealer.domain;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantPartPhaseProblemsByProblem" })
public class PlantPartPhaseProblem {

    /**
     */
	@Column(length=1000)
    private String comment;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private final Set<PlantPartPhaseSymptom> symptoms = new HashSet<PlantPartPhaseSymptom>();

    /**
     */
    @ManyToOne
    private Problem problem;
    
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPartPhaseProblem")
    private final Set<ProblemActiveIngredient> problemActiveIngredients = new HashSet<ProblemActiveIngredient>();
    
    
    public Plant getPlant()
    {
    	TypedQuery<PlantPartPhaseSymptom> symptomsQ = PlantPartPhaseSymptom.findPlantPartPhaseSymptomsByProblem(this);
    	List<PlantPartPhaseSymptom> symptomsTmp = symptomsQ.getResultList();
    	//for (PlantPartPhaseSymptom ppps : this.symptoms) {
    	for (PlantPartPhaseSymptom ppps : symptomsTmp) {
			Plant plant = ppps.getPlantPartPhase().getPlant();
			if (plant != null)
				return plant;
		}
    	return null;
    }
}
