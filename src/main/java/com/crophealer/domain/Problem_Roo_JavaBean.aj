// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Country;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemPicture;
import com.crophealer.domain.ProblemTranslation;
import java.util.Set;

privileged aspect Problem_Roo_JavaBean {
    
    public String Problem.getLatinName() {
        return this.latinName;
    }
    
    public void Problem.setLatinName(String latinName) {
        this.latinName = latinName;
    }
    
    public String Problem.getComment() {
        return this.comment;
    }
    
    public void Problem.setComment(String comment) {
        this.comment = comment;
    }
    
    public Country Problem.getCountry() {
        return this.country;
    }
    
    public void Problem.setCountry(Country country) {
        this.country = country;
    }
    
    public Set<ProblemTranslation> Problem.getTranslations() {
        return this.translations;
    }
    
    public Set<ProblemPicture> Problem.getPictures() {
        return this.pictures;
    }
    
    public Set<PlantPartPhaseSymptom> Problem.getPlantPartPhaseProblems() {
        return this.plantPartPhaseProblems;
    }
    
}
