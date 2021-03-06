// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.GrowthPhase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GrowthPhase_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager GrowthPhase.entityManager;
    
    public static final EntityManager GrowthPhase.entityManager() {
        EntityManager em = new GrowthPhase().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long GrowthPhase.countGrowthPhases() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GrowthPhase o", Long.class).getSingleResult();
    }
    
    public static List<GrowthPhase> GrowthPhase.findAllGrowthPhases() {
        return entityManager().createQuery("SELECT o FROM GrowthPhase o", GrowthPhase.class).getResultList();
    }
    
    public static GrowthPhase GrowthPhase.findGrowthPhase(Long id) {
        if (id == null) return null;
        return entityManager().find(GrowthPhase.class, id);
    }
    
    public static List<GrowthPhase> GrowthPhase.findGrowthPhaseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GrowthPhase o", GrowthPhase.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void GrowthPhase.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void GrowthPhase.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            GrowthPhase attached = GrowthPhase.findGrowthPhase(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void GrowthPhase.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void GrowthPhase.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public GrowthPhase GrowthPhase.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GrowthPhase merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
