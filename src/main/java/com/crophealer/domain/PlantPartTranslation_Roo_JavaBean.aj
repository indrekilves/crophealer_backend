// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartTranslation;

privileged aspect PlantPartTranslation_Roo_JavaBean {
    
    public String PlantPartTranslation.getName() {
        return this.name;
    }
    
    public void PlantPartTranslation.setName(String name) {
        this.name = name;
    }
    
    public PlantPart PlantPartTranslation.getPlantPart() {
        return this.plantPart;
    }
    
    public void PlantPartTranslation.setPlantPart(PlantPart plantPart) {
        this.plantPart = plantPart;
    }
    
    public Languages PlantPartTranslation.getLang() {
        return this.lang;
    }
    
    public void PlantPartTranslation.setLang(Languages lang) {
        this.lang = lang;
    }
    
    public String PlantPartTranslation.getDescription() {
        return this.description;
    }
    
    public void PlantPartTranslation.setDescription(String description) {
        this.description = description;
    }
    
}
