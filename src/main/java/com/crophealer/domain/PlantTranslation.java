package com.crophealer.domain;

import javax.persistence.EntityManager;
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

	
	public static PlantTranslation getSinglePlantTranslationByName(String plantTransStr) {
		try {
			/*List<PlantTranslation> all = PlantTranslation.findAllPlantTranslations();
			for (PlantTranslation plantTranslation : all) {
				if (plantTranslation.getName().compareToIgnoreCase(plantTransStr) == 0)
					return plantTranslation;
			}
			return null;*/
			TypedQuery<PlantTranslation> plantQ = PlantTranslation.findPlantTranslationsByNameEqualsCustom(plantTransStr);
			if (plantQ.getResultList().size() > 0)
				return plantQ.getSingleResult();
			else
				return null;
				
		} catch (Exception e) {
			return null;
		}
	}
	
	
    
	public static TypedQuery<PlantTranslation> findPlantTranslationsByNameEqualsCustom(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = PlantTranslation.entityManager();
        TypedQuery<PlantTranslation> q = em.createQuery("SELECT o FROM PlantTranslation AS o WHERE LOWER(o.name) = LOWER(:name)", PlantTranslation.class);
        q.setParameter("name", name);
        return q;
    }

}
