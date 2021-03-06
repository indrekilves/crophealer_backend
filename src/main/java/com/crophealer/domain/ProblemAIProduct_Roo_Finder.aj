// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.ProblemAIProduct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ProblemAIProduct_Roo_Finder {
    
    public static TypedQuery<ProblemAIProduct> ProblemAIProduct.findProblemAIProductsByActiveIngredient(ActiveIngredient activeIngredient) {
        if (activeIngredient == null) throw new IllegalArgumentException("The activeIngredient argument is required");
        EntityManager em = ProblemAIProduct.entityManager();
        TypedQuery<ProblemAIProduct> q = em.createQuery("SELECT o FROM ProblemAIProduct AS o WHERE o.activeIngredient = :activeIngredient", ProblemAIProduct.class);
        q.setParameter("activeIngredient", activeIngredient);
        return q;
    }
    
    public static TypedQuery<ProblemAIProduct> ProblemAIProduct.findProblemAIProductsByProblem(PlantPartPhaseProblem problem) {
        if (problem == null) throw new IllegalArgumentException("The problem argument is required");
        EntityManager em = ProblemAIProduct.entityManager();
        TypedQuery<ProblemAIProduct> q = em.createQuery("SELECT o FROM ProblemAIProduct AS o WHERE o.problem = :problem", ProblemAIProduct.class);
        q.setParameter("problem", problem);
        return q;
    }
    
    public static TypedQuery<ProblemAIProduct> ProblemAIProduct.findProblemAIProductsByProblemAndActiveIngredient(PlantPartPhaseProblem problem, ActiveIngredient activeIngredient) {
        if (problem == null) throw new IllegalArgumentException("The problem argument is required");
        if (activeIngredient == null) throw new IllegalArgumentException("The activeIngredient argument is required");
        EntityManager em = ProblemAIProduct.entityManager();
        TypedQuery<ProblemAIProduct> q = em.createQuery("SELECT o FROM ProblemAIProduct AS o WHERE o.problem = :problem AND o.activeIngredient = :activeIngredient", ProblemAIProduct.class);
        q.setParameter("problem", problem);
        q.setParameter("activeIngredient", activeIngredient);
        return q;
    }
    
}
