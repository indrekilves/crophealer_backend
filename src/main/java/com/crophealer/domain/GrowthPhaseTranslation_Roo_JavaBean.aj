// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.GrowthPhaseTranslation;
import com.crophealer.domain.Languages;

privileged aspect GrowthPhaseTranslation_Roo_JavaBean {
    
    public String GrowthPhaseTranslation.getName() {
        return this.name;
    }
    
    public void GrowthPhaseTranslation.setName(String name) {
        this.name = name;
    }
    
    public String GrowthPhaseTranslation.getDescription() {
        return this.description;
    }
    
    public void GrowthPhaseTranslation.setDescription(String description) {
        this.description = description;
    }
    
    public GrowthPhase GrowthPhaseTranslation.getGrowthPhase() {
        return this.growthPhase;
    }
    
    public void GrowthPhaseTranslation.setGrowthPhase(GrowthPhase growthPhase) {
        this.growthPhase = growthPhase;
    }
    
    public Languages GrowthPhaseTranslation.getLang() {
        return this.lang;
    }
    
    public void GrowthPhaseTranslation.setLang(Languages lang) {
        this.lang = lang;
    }
    
}
