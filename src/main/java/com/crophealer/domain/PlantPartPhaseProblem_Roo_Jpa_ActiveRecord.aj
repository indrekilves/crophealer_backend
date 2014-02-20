// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPartPhaseProblem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PlantPartPhaseProblem_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PlantPartPhaseProblem.entityManager;
    
    public static final EntityManager PlantPartPhaseProblem.entityManager() {
        EntityManager em = new PlantPartPhaseProblem().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PlantPartPhaseProblem.countPlantPartPhaseProblems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PlantPartPhaseProblem o", Long.class).getSingleResult();
    }
    
    public static List<PlantPartPhaseProblem> PlantPartPhaseProblem.findAllPlantPartPhaseProblems() {
        return entityManager().createQuery("SELECT o FROM PlantPartPhaseProblem o", PlantPartPhaseProblem.class).getResultList();
    }
    
    public static PlantPartPhaseProblem PlantPartPhaseProblem.findPlantPartPhaseProblem(Long id) {
        if (id == null) return null;
        return entityManager().find(PlantPartPhaseProblem.class, id);
    }
    
    public static List<PlantPartPhaseProblem> PlantPartPhaseProblem.findPlantPartPhaseProblemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PlantPartPhaseProblem o", PlantPartPhaseProblem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PlantPartPhaseProblem.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PlantPartPhaseProblem.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PlantPartPhaseProblem attached = PlantPartPhaseProblem.findPlantPartPhaseProblem(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PlantPartPhaseProblem.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PlantPartPhaseProblem.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PlantPartPhaseProblem PlantPartPhaseProblem.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PlantPartPhaseProblem merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
