// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantTranslation;

privileged aspect PlantTranslation_Roo_JavaBean {
    
    public String PlantTranslation.getName() {
        return this.name;
    }
    
    public void PlantTranslation.setName(String name) {
        this.name = name;
    }
    
    public String PlantTranslation.getDescription() {
        return this.description;
    }
    
    public void PlantTranslation.setDescription(String description) {
        this.description = description;
    }
    
    public Plant PlantTranslation.getPlant() {
        return this.plant;
    }
    
    public void PlantTranslation.setPlant(Plant plant) {
        this.plant = plant;
    }
    
    public Languages PlantTranslation.getLang() {
        return this.lang;
    }
    
    public void PlantTranslation.setLang(Languages lang) {
        this.lang = lang;
    }
    
}
