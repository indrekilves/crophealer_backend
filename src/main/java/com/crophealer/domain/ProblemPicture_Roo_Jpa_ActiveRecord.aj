// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ProblemPicture;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ProblemPicture_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ProblemPicture.entityManager;
    
    public static final EntityManager ProblemPicture.entityManager() {
        EntityManager em = new ProblemPicture().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ProblemPicture.countProblemPictures() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ProblemPicture o", Long.class).getSingleResult();
    }
    
    public static List<ProblemPicture> ProblemPicture.findAllProblemPictures() {
        return entityManager().createQuery("SELECT o FROM ProblemPicture o", ProblemPicture.class).getResultList();
    }
    
    public static ProblemPicture ProblemPicture.findProblemPicture(Long id) {
        if (id == null) return null;
        return entityManager().find(ProblemPicture.class, id);
    }
    
    public static List<ProblemPicture> ProblemPicture.findProblemPictureEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ProblemPicture o", ProblemPicture.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ProblemPicture.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ProblemPicture.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ProblemPicture attached = ProblemPicture.findProblemPicture(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ProblemPicture.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ProblemPicture.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ProblemPicture ProblemPicture.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ProblemPicture merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
