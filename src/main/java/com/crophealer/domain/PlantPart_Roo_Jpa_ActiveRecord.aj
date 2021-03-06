// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPart;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PlantPart_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PlantPart.entityManager;
    
    public static final EntityManager PlantPart.entityManager() {
        EntityManager em = new PlantPart().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PlantPart.countPlantParts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PlantPart o", Long.class).getSingleResult();
    }
    
    public static List<PlantPart> PlantPart.findAllPlantParts() {
        return entityManager().createQuery("SELECT o FROM PlantPart o", PlantPart.class).getResultList();
    }
    
    public static PlantPart PlantPart.findPlantPart(Long id) {
        if (id == null) return null;
        return entityManager().find(PlantPart.class, id);
    }
    
    public static List<PlantPart> PlantPart.findPlantPartEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PlantPart o", PlantPart.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PlantPart.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PlantPart.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PlantPart attached = PlantPart.findPlantPart(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PlantPart.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PlantPart.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PlantPart PlantPart.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PlantPart merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
