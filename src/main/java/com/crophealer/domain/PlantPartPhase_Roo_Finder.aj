// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect PlantPartPhase_Roo_Finder {
    
    public static TypedQuery<PlantPartPhase> PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhase(Plant plant, GrowthPhase growthPhase) {
        if (plant == null) throw new IllegalArgumentException("The plant argument is required");
        if (growthPhase == null) throw new IllegalArgumentException("The growthPhase argument is required");
        EntityManager em = PlantPartPhase.entityManager();
        TypedQuery<PlantPartPhase> q = em.createQuery("SELECT o FROM PlantPartPhase AS o WHERE o.plant = :plant AND o.growthPhase = :growthPhase", PlantPartPhase.class);
        q.setParameter("plant", plant);
        q.setParameter("growthPhase", growthPhase);
        return q;
    }
    
    public static TypedQuery<PlantPartPhase> PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhaseAndPlantPart(Plant plant, GrowthPhase growthPhase, PlantPart plantPart) {
        if (plant == null) throw new IllegalArgumentException("The plant argument is required");
        if (growthPhase == null) throw new IllegalArgumentException("The growthPhase argument is required");
        if (plantPart == null) throw new IllegalArgumentException("The plantPart argument is required");
        EntityManager em = PlantPartPhase.entityManager();
        TypedQuery<PlantPartPhase> q = em.createQuery("SELECT o FROM PlantPartPhase AS o WHERE o.plant = :plant AND o.growthPhase = :growthPhase AND o.plantPart = :plantPart", PlantPartPhase.class);
        q.setParameter("plant", plant);
        q.setParameter("growthPhase", growthPhase);
        q.setParameter("plantPart", plantPart);
        return q;
    }
    
}