package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientTranslation;
import com.crophealer.domain.Languages;

public class ActiveIngredientResourceAssembler {

	

	public ActiveIngredientResource toResource(ActiveIngredient ai, Languages language) {
		
		ActiveIngredientResource air = new ActiveIngredientResource();
		air.setId(ai.getId());
		air.setLatinName(ai.getLatinName());
		if (language != null){
			ActiveIngredientTranslation ait = ActiveIngredientTranslation.findActiveIngredientTranslationsByLang(language).getSingleResult();
			if (ait != null){
				air.setName(ait.getName());
				air.setDescription(ait.getDescription());
				air.setWarning(ait.getWarning());
			}
		}

		return air;
	}

	
	public ActiveIngredientResourceList toResource(List<ActiveIngredient> ail, Languages l) {
		if (ail == null) return null;

		List<ActiveIngredientResource> airl = new ArrayList<ActiveIngredientResource>();
		for (ActiveIngredient ai : ail) {
			airl.add(toResource(ai, l));
		}
		
		return new ActiveIngredientResourceList(airl);
	}
}
