// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Languages;
import com.crophealer.domain.ProblemTranslation;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ProblemTranslation_Roo_Finder {
    
    public static TypedQuery<ProblemTranslation> ProblemTranslation.findProblemTranslationsByLang(Languages lang) {
        if (lang == null) throw new IllegalArgumentException("The lang argument is required");
        EntityManager em = ProblemTranslation.entityManager();
        TypedQuery<ProblemTranslation> q = em.createQuery("SELECT o FROM ProblemTranslation AS o WHERE o.lang = :lang", ProblemTranslation.class);
        q.setParameter("lang", lang);
        return q;
    }
    
}