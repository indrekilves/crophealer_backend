package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantPartPhases")

public class PlantPartPhaseResourceList {

	private List<PlantPartPhaseResource> plantPartPhases;

	public PlantPartPhaseResourceList() {
	}

	public PlantPartPhaseResourceList(List<PlantPartPhaseResource> plantPartPhases) {
		this.setPlantPartPhases(plantPartPhases);
	}

	
}
