package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseSymptom;

public class PlantPartPhaseResourceAssembler {


	public PlantPartPhaseResource toResource(PlantPartPhase ppp, Languages l) {
		
		PlantPartPhaseResource pppr = new PlantPartPhaseResource();
		pppr.setId(ppp.getId());
		pppr.setComment(ppp.getComment());
		
		Plant p = ppp.getPlant();
		if (p != null){
			pppr.setPlantId(p.getId());
		}
		
		GrowthPhase gp = ppp.getGrowthPhase();
		if (gp != null){
			pppr.setGrowthPhaseId(gp.getId());
		}
		
		PlantPart pp = ppp.getPlantPart();
		if (pp != null){
			pppr.setPlantPartId(pp.getId());
		}
		
		String symptomIDs = null;
		Set<PlantPartPhaseSymptom> symptoms = ppp.getSymptoms();
		if (symptoms != null && symptoms.size() > 0){
			for (PlantPartPhaseSymptom symptom : symptoms) {
				if (symptom != null){
					if (symptomIDs == null){
						symptomIDs = symptom.getId().toString();
					}
					else{
						symptomIDs += ", " + symptom.getId().toString();
					}
				}
			}
		}
		pppr.setSymptomsIDs(symptomIDs);
			
		return pppr;
	}

	
	@SuppressWarnings("unused")
	private PlantResource getPlantResource(PlantPartPhase ppp, Languages l) {
		Plant p = ppp.getPlant();
		if (p == null) return null;
		
		PlantResourceAssembler asm = new PlantResourceAssembler();
		return asm.toResource(p, l); 
	}
	
	
	@SuppressWarnings("unused")
	private PlantPartResource getPlantPartResource(PlantPartPhase ppp, Languages l) {
		PlantPart pp = ppp.getPlantPart();
		if (pp == null) return null;
		
		PlantPartResourceAssembler asm = new PlantPartResourceAssembler();
		return asm.toResource(pp, l); 
	}
	
	

	@SuppressWarnings("unused")
	private GrowthPhaseResource getGrowthPhaseResource(PlantPartPhase ppp, Languages l) {
		GrowthPhase gp = ppp.getGrowthPhase();
		if (gp == null) return null;
		
		GrowthPhaseResourceAssembler asm = new GrowthPhaseResourceAssembler();
		return asm.toResource(gp, l); 
	}



	public PlantPartPhaseResourceList toResource(List<PlantPartPhase> pppl, Languages l) {
		if (pppl == null) return null;

		List<PlantPartPhaseResource> pprl = new ArrayList<PlantPartPhaseResource>();
		for (PlantPartPhase pp : pppl) {
			pprl.add(toResource(pp, l));
		}
		
		return new PlantPartPhaseResourceList(pprl);
	}
}
