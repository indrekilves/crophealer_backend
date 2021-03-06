// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.DiagnosedProblemPicture;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DiagnosedProblemPicture_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager DiagnosedProblemPicture.entityManager;
    
    public static final EntityManager DiagnosedProblemPicture.entityManager() {
        EntityManager em = new DiagnosedProblemPicture().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long DiagnosedProblemPicture.countDiagnosedProblemPictures() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DiagnosedProblemPicture o", Long.class).getSingleResult();
    }
    
    public static List<DiagnosedProblemPicture> DiagnosedProblemPicture.findAllDiagnosedProblemPictures() {
        return entityManager().createQuery("SELECT o FROM DiagnosedProblemPicture o", DiagnosedProblemPicture.class).getResultList();
    }
    
    public static DiagnosedProblemPicture DiagnosedProblemPicture.findDiagnosedProblemPicture(Long id) {
        if (id == null) return null;
        return entityManager().find(DiagnosedProblemPicture.class, id);
    }
    
    public static List<DiagnosedProblemPicture> DiagnosedProblemPicture.findDiagnosedProblemPictureEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DiagnosedProblemPicture o", DiagnosedProblemPicture.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void DiagnosedProblemPicture.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void DiagnosedProblemPicture.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            DiagnosedProblemPicture attached = DiagnosedProblemPicture.findDiagnosedProblemPicture(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void DiagnosedProblemPicture.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void DiagnosedProblemPicture.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public DiagnosedProblemPicture DiagnosedProblemPicture.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DiagnosedProblemPicture merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
