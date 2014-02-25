package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "activeIngredients")
public class ActiveIngredientResourceList {

	private List<ActiveIngredientResource> activeIngredients;

	public ActiveIngredientResourceList() {
	}

	public ActiveIngredientResourceList(List<ActiveIngredientResource> activeIngredients) {
		this.setActiveIngredients(activeIngredients);
	}

}