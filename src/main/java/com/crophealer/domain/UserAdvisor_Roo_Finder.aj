// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.UserAdvisor;
import com.crophealer.security.Users;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserAdvisor_Roo_Finder {
    
    public static TypedQuery<UserAdvisor> UserAdvisor.findUserAdvisorsByClientAndEnabled(Users client, Boolean enabled) {
        if (client == null) throw new IllegalArgumentException("The client argument is required");
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        EntityManager em = UserAdvisor.entityManager();
        TypedQuery<UserAdvisor> q = em.createQuery("SELECT o FROM UserAdvisor AS o WHERE o.client = :client AND o.enabled = :enabled", UserAdvisor.class);
        q.setParameter("client", client);
        q.setParameter("enabled", enabled);
        return q;
    }
    
}
