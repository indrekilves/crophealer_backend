// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantTranslation;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect PlantTranslation_Roo_Finder {
    
    public static TypedQuery<PlantTranslation> PlantTranslation.findPlantTranslationsByLang(Languages lang) {
        if (lang == null) throw new IllegalArgumentException("The lang argument is required");
        EntityManager em = PlantTranslation.entityManager();
        TypedQuery<PlantTranslation> q = em.createQuery("SELECT o FROM PlantTranslation AS o WHERE o.lang = :lang", PlantTranslation.class);
        q.setParameter("lang", lang);
        return q;
    }
    
    public static TypedQuery<PlantTranslation> PlantTranslation.findPlantTranslationsByPlantAndLang(Plant plant, Languages lang) {
        if (plant == null) throw new IllegalArgumentException("The plant argument is required");
        if (lang == null) throw new IllegalArgumentException("The lang argument is required");
        EntityManager em = PlantTranslation.entityManager();
        TypedQuery<PlantTranslation> q = em.createQuery("SELECT o FROM PlantTranslation AS o WHERE o.plant = :plant AND o.lang = :lang", PlantTranslation.class);
        q.setParameter("plant", plant);
        q.setParameter("lang", lang);
        return q;
    }
    
}
