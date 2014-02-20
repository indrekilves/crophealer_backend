// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPartTranslation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PlantPartTranslation_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PlantPartTranslation.entityManager;
    
    public static final EntityManager PlantPartTranslation.entityManager() {
        EntityManager em = new PlantPartTranslation().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PlantPartTranslation.countPlantPartTranslations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PlantPartTranslation o", Long.class).getSingleResult();
    }
    
    public static List<PlantPartTranslation> PlantPartTranslation.findAllPlantPartTranslations() {
        return entityManager().createQuery("SELECT o FROM PlantPartTranslation o", PlantPartTranslation.class).getResultList();
    }
    
    public static PlantPartTranslation PlantPartTranslation.findPlantPartTranslation(Long id) {
        if (id == null) return null;
        return entityManager().find(PlantPartTranslation.class, id);
    }
    
    public static List<PlantPartTranslation> PlantPartTranslation.findPlantPartTranslationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PlantPartTranslation o", PlantPartTranslation.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PlantPartTranslation.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PlantPartTranslation.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PlantPartTranslation attached = PlantPartTranslation.findPlantPartTranslation(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PlantPartTranslation.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PlantPartTranslation.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PlantPartTranslation PlantPartTranslation.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PlantPartTranslation merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
