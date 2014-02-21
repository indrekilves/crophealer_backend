package com.crophealer.rest;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Plant;

public class PlantResourceAssembler {

	

	public PlantResource toResource(Plant p) {
		
		PlantResource pr = new PlantResource();
		pr.setId(p.getId());
		pr.setComment(p.getComment());
		pr.setIconUrl(p.getIconUrl());
		return pr;
	}

	
	public PlantResourceList toResource(List<Plant> pl) {
		if (pl == null) return null;

		List<PlantResource> prl = new ArrayList<PlantResource>();
		for (Plant p : pl) {
			prl.add(toResource(p));
		}
		
		return new PlantResourceList(prl);
	}
}
