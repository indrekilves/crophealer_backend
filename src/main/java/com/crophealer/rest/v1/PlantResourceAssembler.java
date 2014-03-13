package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantTranslation;

public class PlantResourceAssembler {

	

	public PlantResource toResource(Plant p, Languages l) {
		
		PlantResource pr = new PlantResource();
		pr.setId(p.getId());
		pr.setIconUrl(p.getIconUrl());
		if (l != null){
			TypedQuery<PlantTranslation> tq = PlantTranslation.findPlantTranslationsByPlantAndLang(p, l);
			if (tq != null){
				PlantTranslation pt = tq.getSingleResult();
				if (pt != null){
					pr.setName(pt.getName());
					pr.setDescription(pt.getDescription());
				}
			}
		}
		return pr;
	}

	
	public PlantResourceList toResource(List<Plant> pl, Languages l) {
		if (pl == null) return null;

		List<PlantResource> prl = new ArrayList<PlantResource>();
		for (Plant p : pl) {
			prl.add(toResource(p, l));
		}
		
		return new PlantResourceList(prl);
	}
}
