// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.security;

import com.crophealer.security.Assignments;
import com.crophealer.security.Authorities;
import com.crophealer.security.Users;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Assignments_Roo_Finder {
    
    public static TypedQuery<Assignments> Assignments.findAssignmentsesByAuthority(Authorities authority) {
        if (authority == null) throw new IllegalArgumentException("The authority argument is required");
        EntityManager em = Assignments.entityManager();
        TypedQuery<Assignments> q = em.createQuery("SELECT o FROM Assignments AS o WHERE o.authority = :authority", Assignments.class);
        q.setParameter("authority", authority);
        return q;
    }
    
    public static TypedQuery<Assignments> Assignments.findAssignmentsesByUsrAndAuthority(Users usr, Authorities authority) {
        if (usr == null) throw new IllegalArgumentException("The usr argument is required");
        if (authority == null) throw new IllegalArgumentException("The authority argument is required");
        EntityManager em = Assignments.entityManager();
        TypedQuery<Assignments> q = em.createQuery("SELECT o FROM Assignments AS o WHERE o.usr = :usr AND o.authority = :authority", Assignments.class);
        q.setParameter("usr", usr);
        q.setParameter("authority", authority);
        return q;
    }
    
}
