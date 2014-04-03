package com.crophealer.domain;

import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantTranslationsByLang",
		"findPlantTranslationsByPlantAndLang",
		"findPlantTranslationsByNameEquals" })
public class PlantTranslation {

	/**
     */
	private String name;

	/**
     */
	@Size(max = 1000)
	private String description;

	/**
     */
	@ManyToOne
	private Plant plant;

	/**
     */
	@ManyToOne
	private Languages lang;

	public static PlantTranslation getSinglePlantTranslationByName(
			String plantTransStr) {
		try {
			TypedQuery<PlantTranslation> plantQ = PlantTranslation
					.findPlantTranslationsByNameEquals(plantTransStr);
			if (plantQ.getResultList().size() > 0)
				return plantQ.getSingleResult();
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}
}
