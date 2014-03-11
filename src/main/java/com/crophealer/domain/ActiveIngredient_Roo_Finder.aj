// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredient;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ActiveIngredient_Roo_Finder {
    
    public static TypedQuery<ActiveIngredient> ActiveIngredient.findActiveIngredientsByLatinNameEquals(String latinName) {
        if (latinName == null || latinName.length() == 0) throw new IllegalArgumentException("The latinName argument is required");
        EntityManager em = ActiveIngredient.entityManager();
        TypedQuery<ActiveIngredient> q = em.createQuery("SELECT o FROM ActiveIngredient AS o WHERE o.latinName = :latinName", ActiveIngredient.class);
        q.setParameter("latinName", latinName);
        return q;
    }
    
}