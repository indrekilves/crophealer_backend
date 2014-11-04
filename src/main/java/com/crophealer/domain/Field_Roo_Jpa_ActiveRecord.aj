// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Field_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Field.entityManager;
    
    public static final EntityManager Field.entityManager() {
        EntityManager em = new Field().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Field.countFields() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Field o", Long.class).getSingleResult();
    }
    
    public static List<Field> Field.findAllFields() {
        return entityManager().createQuery("SELECT o FROM Field o", Field.class).getResultList();
    }
    
    public static Field Field.findField(Long id) {
        if (id == null) return null;
        return entityManager().find(Field.class, id);
    }
    
    public static List<Field> Field.findFieldEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Field o", Field.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Field.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Field.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Field attached = Field.findField(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Field.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Field.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Field Field.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Field merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
