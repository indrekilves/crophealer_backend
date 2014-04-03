package com.crophealer.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.crophealer.domain.DiagnosedProblem;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Users {

    /**
     */
    private String username;

    /**
     */
    private String password;

    /**
     */
    private Boolean enabled;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usr")
    private Set<DiagnosedProblem> diagnosedProblems = new HashSet<DiagnosedProblem>();

    /**
     */
    private String email;
}
