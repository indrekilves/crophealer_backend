// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.security;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.security.Users;
import java.util.Set;

privileged aspect Users_Roo_JavaBean {
    
    public String Users.getUsername() {
        return this.username;
    }
    
    public void Users.setUsername(String username) {
        this.username = username;
    }
    
    public String Users.getPassword() {
        return this.password;
    }
    
    public void Users.setPassword(String password) {
        this.password = password;
    }
    
    public Boolean Users.getEnabled() {
        return this.enabled;
    }
    
    public void Users.setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public Set<DiagnosedProblem> Users.getDiagnosedProblems() {
        return this.diagnosedProblems;
    }
    
    public void Users.setDiagnosedProblems(Set<DiagnosedProblem> diagnosedProblems) {
        this.diagnosedProblems = diagnosedProblems;
    }
    
    public String Users.getEmail() {
        return this.email;
    }
    
    public void Users.setEmail(String email) {
        this.email = email;
    }
    
}