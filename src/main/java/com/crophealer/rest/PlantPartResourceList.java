package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "plantParts")
public class PlantPartResourceList {

	private List<PlantPartResource> plantParts;

	public PlantPartResourceList() {
	}

	public PlantPartResourceList(List<PlantPartResource> plantParts) {
		this.setPlantParts(plantParts);
	}

}
