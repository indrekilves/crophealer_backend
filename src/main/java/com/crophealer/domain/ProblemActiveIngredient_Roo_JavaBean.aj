// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemActiveIngredient;

privileged aspect ProblemActiveIngredient_Roo_JavaBean {
    
    public String ProblemActiveIngredient.getComment() {
        return this.comment;
    }
    
    public void ProblemActiveIngredient.setComment(String comment) {
        this.comment = comment;
    }
    
    public Problem ProblemActiveIngredient.getProblem() {
        return this.problem;
    }
    
    public void ProblemActiveIngredient.setProblem(Problem problem) {
        this.problem = problem;
    }
    
    public ActiveIngredient ProblemActiveIngredient.getActiveIngredient() {
        return this.activeIngredient;
    }
    
    public void ProblemActiveIngredient.setActiveIngredient(ActiveIngredient activeIngredient) {
        this.activeIngredient = activeIngredient;
    }
    
}