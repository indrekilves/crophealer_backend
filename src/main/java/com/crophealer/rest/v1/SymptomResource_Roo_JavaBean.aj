// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.SymptomPictureResourceList;
import com.crophealer.rest.v1.SymptomResource;

privileged aspect SymptomResource_Roo_JavaBean {
    
    public Long SymptomResource.getId() {
        return this.id;
    }
    
    public void SymptomResource.setId(Long id) {
        this.id = id;
    }
    
    public String SymptomResource.getName() {
        return this.name;
    }
    
    public void SymptomResource.setName(String name) {
        this.name = name;
    }
    
    public String SymptomResource.getDescription() {
        return this.description;
    }
    
    public void SymptomResource.setDescription(String description) {
        this.description = description;
    }
    
    public String SymptomResource.getWarning() {
        return this.warning;
    }
    
    public void SymptomResource.setWarning(String warning) {
        this.warning = warning;
    }
    
    public SymptomPictureResourceList SymptomResource.getPictures() {
        return this.pictures;
    }
    
    public void SymptomResource.setPictures(SymptomPictureResourceList pictures) {
        this.pictures = pictures;
    }
    
}