// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Reseller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Reseller_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Reseller.entityManager;
    
    public static final EntityManager Reseller.entityManager() {
        EntityManager em = new Reseller().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Reseller.countResellers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Reseller o", Long.class).getSingleResult();
    }
    
    public static List<Reseller> Reseller.findAllResellers() {
        return entityManager().createQuery("SELECT o FROM Reseller o", Reseller.class).getResultList();
    }
    
    public static Reseller Reseller.findReseller(Long id) {
        if (id == null) return null;
        return entityManager().find(Reseller.class, id);
    }
    
    public static List<Reseller> Reseller.findResellerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Reseller o", Reseller.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Reseller.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Reseller.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Reseller attached = Reseller.findReseller(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Reseller.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Reseller.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Reseller Reseller.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Reseller merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
