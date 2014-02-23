package com.crophealer.rest;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;
@RooJavaBean
@JsonRootName(value = "symptomPictures")
public class SymptomPictureResourceList {

	private List<SymptomPictureResource> symptomPictureResource;

	public SymptomPictureResourceList() {
	}

	public SymptomPictureResourceList(List<SymptomPictureResource> symptomPictureResource) {
		//TODO : set
	}

}
