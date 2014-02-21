package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plants")
public class PlantResourceList {

	private List<PlantResource> plantResources;

	public PlantResourceList() {
	}

	public PlantResourceList(List<PlantResource> plantResources) {
		this.setPlantResources(plantResources);
	}

}
