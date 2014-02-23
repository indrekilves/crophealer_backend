package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantPartPhaseSymptoms")
public class PlantPartPhaseSymptomResourceList {

	private List<PlantPartPhaseSymptomResource> plantPartPhaseSymptomResources;

	public PlantPartPhaseSymptomResourceList() {
	}

	public PlantPartPhaseSymptomResourceList(List<PlantPartPhaseSymptomResource> plantPartPhaseSymptomResources) {
//		this.setPlantPartPhaseSymptomResources(plantPartPhaseSymptomResources);
	}

}
