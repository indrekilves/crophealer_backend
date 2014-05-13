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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPartPhaseProblem")
    private final Set<ProblemActiveIngredient> problemActiveIngredients = new HashSet<ProblemActiveIngredient>();
     */
    /**
     */
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private final Set<ProblemAIProduct> activeIngredientProductLinks = new HashSet<ProblemAIProduct>();
    
    
    public Plant getPlant()
    {
    	TypedQuery<PlantPartPhaseSymptom> symptomsQ = PlantPartPhaseSymptom.findPlantPartPhaseSymptomsByProblem(this);
    	List<PlantPartPhaseSymptom> symptomsTmp = symptomsQ.getResultList();
    	for (PlantPartPhaseSymptom ppps : symptomsTmp) {
			Plant plant = ppps.getPlantPartPhase().getPlant();
			if (plant != null)
				return plant;
			else
				System.out.println("Did not find plant");
		}
    	System.out.println("Did not find plant");
    	return null;
    }


	@Override
	public String toString() {
		String symptomIdsCSV = getSymptomIdsCsv();
		Long problemId = getProblemId();
		String problemActiveIngredientIdCsv = getProblemActiveIngredientIdCsv();
		return "PlantPartPhaseProblem [comment=" + comment + ", pppSymptoms="
				+ symptomIdsCSV + ", problem=" + problemId
				+ ", problemActiveIngredients=" + problemActiveIngredientIdCsv
				+ "]";
	}



	private String getSymptomIdsCsv() {
		if (symptoms == null) return null;
		
		StringBuilder sb = new StringBuilder();
	    for (PlantPartPhaseSymptom pppSymptom : symptoms) {
	    	if (pppSymptom != null){
	    		if (sb.equals("")){
	    			sb.append(pppSymptom.getId());
	    		}
	    		else{
	    			sb.append("," + pppSymptom.getId());
	    		}
	    	}
		}
			    
		return sb.toString();
	}

	

	private Long getProblemId() {
		if (problem == null) {
			return null;
		}
		else{
			return problem.getId();
		}
	}
	

	private String getProblemActiveIngredientIdCsv() {
		if (activeIngredientProductLinks == null) return null;
		
		StringBuilder sb = new StringBuilder();
	    for (ProblemAIProduct pai : activeIngredientProductLinks) {
	    	if (pai != null){
	    		if (sb.equals("")){
	    			sb.append(pai.getId());
	    		}
	    		else{
	    			sb.append("," + pai.getId());
	    		}
	    	}
		}
			    
		return sb.toString();
	}

    
    
}
