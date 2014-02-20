// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Country;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantTranslation;
import java.util.Set;

privileged aspect Plant_Roo_JavaBean {
    
    public String Plant.getComment() {
        return this.comment;
    }
    
    public void Plant.setComment(String comment) {
        this.comment = comment;
    }
    
    public String Plant.getIconUrl() {
        return this.iconUrl;
    }
    
    public void Plant.setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    public Country Plant.getCountry() {
        return this.country;
    }
    
    public void Plant.setCountry(Country country) {
        this.country = country;
    }
    
    public Set<PlantTranslation> Plant.getTranslations() {
        return this.translations;
    }
    
    public void Plant.setTranslations(Set<PlantTranslation> translations) {
        this.translations = translations;
    }
    
    public Set<PlantPartPhase> Plant.getPlantPartPhases() {
        return this.plantPartPhases;
    }
    
    public void Plant.setPlantPartPhases(Set<PlantPartPhase> plantPartPhases) {
        this.plantPartPhases = plantPartPhases;
    }
    
}
