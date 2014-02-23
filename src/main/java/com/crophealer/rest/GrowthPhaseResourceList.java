package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "growthPhases")
public class GrowthPhaseResourceList {

	private List<GrowthPhaseResource> growthPhases;

	public GrowthPhaseResourceList() {
	}

	public GrowthPhaseResourceList(List<GrowthPhaseResource> growthPhases) {
		this.setGrowthPhases(growthPhases);
	}

}
