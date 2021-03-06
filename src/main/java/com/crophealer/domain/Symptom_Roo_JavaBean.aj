// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Country;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomPicture;
import com.crophealer.domain.SymptomTranslation;
import java.util.Set;

privileged aspect Symptom_Roo_JavaBean {
    
    public String Symptom.getComment() {
        return this.comment;
    }
    
    public void Symptom.setComment(String comment) {
        this.comment = comment;
    }
    
    public Country Symptom.getCountry() {
        return this.country;
    }
    
    public void Symptom.setCountry(Country country) {
        this.country = country;
    }
    
    public Set<SymptomTranslation> Symptom.getTranslations() {
        return this.translations;
    }
    
    public Set<SymptomPicture> Symptom.getPictures() {
        return this.pictures;
    }
    
    public Set<PlantPartPhaseSymptom> Symptom.getPlantPartPhaseSymptoms() {
        return this.plantPartPhaseSymptoms;
    }
    
}
