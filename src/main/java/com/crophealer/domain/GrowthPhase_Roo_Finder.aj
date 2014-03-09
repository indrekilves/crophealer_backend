// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.GrowthPhase;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect GrowthPhase_Roo_Finder {
    
    public static TypedQuery<GrowthPhase> GrowthPhase.findGrowthPhasesByCommentEquals(String comment) {
        if (comment == null || comment.length() == 0) throw new IllegalArgumentException("The comment argument is required");
        EntityManager em = GrowthPhase.entityManager();
        TypedQuery<GrowthPhase> q = em.createQuery("SELECT o FROM GrowthPhase AS o WHERE o.comment = :comment", GrowthPhase.class);
        q.setParameter("comment", comment);
        return q;
    }
    
}
