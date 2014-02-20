// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredientProduct;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ActiveIngredientProduct_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ActiveIngredientProduct.entityManager;
    
    public static final EntityManager ActiveIngredientProduct.entityManager() {
        EntityManager em = new ActiveIngredientProduct().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ActiveIngredientProduct.countActiveIngredientProducts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ActiveIngredientProduct o", Long.class).getSingleResult();
    }
    
    public static List<ActiveIngredientProduct> ActiveIngredientProduct.findAllActiveIngredientProducts() {
        return entityManager().createQuery("SELECT o FROM ActiveIngredientProduct o", ActiveIngredientProduct.class).getResultList();
    }
    
    public static ActiveIngredientProduct ActiveIngredientProduct.findActiveIngredientProduct(Long id) {
        if (id == null) return null;
        return entityManager().find(ActiveIngredientProduct.class, id);
    }
    
    public static List<ActiveIngredientProduct> ActiveIngredientProduct.findActiveIngredientProductEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ActiveIngredientProduct o", ActiveIngredientProduct.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ActiveIngredientProduct.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ActiveIngredientProduct.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ActiveIngredientProduct attached = ActiveIngredientProduct.findActiveIngredientProduct(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ActiveIngredientProduct.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ActiveIngredientProduct.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ActiveIngredientProduct ActiveIngredientProduct.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ActiveIngredientProduct merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
