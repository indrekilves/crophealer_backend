package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "symptoms")
public class SymptomResourceList {

	private List<SymptomResource> symptoms;

	public SymptomResourceList() {
	}

	public SymptomResourceList(List<SymptomResource> symptoms) {
		this.setSymptoms(symptoms);
	}

}
