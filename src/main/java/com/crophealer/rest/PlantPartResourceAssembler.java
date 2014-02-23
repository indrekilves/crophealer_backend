package com.crophealer.rest;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartTranslation;

public class PlantPartResourceAssembler {

	

	public PlantPartResource toResource(PlantPart pp, Languages l) {
		
		PlantPartResource ppr = new PlantPartResource();
		ppr.setId(pp.getId());
		if (l != null){
			PlantPartTranslation ppt = PlantPartTranslation.findPlantPartTranslationsByLang(l).getSingleResult();
			if (ppt != null){
				ppr.setName(ppt.getName());
			}
		}
		return ppr;
	}

	
	public PlantPartResourceList toResource(List<PlantPart> ppl, Languages l) {
		if (ppl == null) return null;

		List<PlantPartResource> pprl = new ArrayList<PlantPartResource>();
		for (PlantPart pp : ppl) {
			pprl.add(toResource(pp, l));
		}
		
		return new PlantPartResourceList(pprl);
	}
}
