// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.SymptomResource;
import com.crophealer.rest.v1.SymptomResourceList;
import java.util.List;

privileged aspect SymptomResourceList_Roo_JavaBean {
    
    public List<SymptomResource> SymptomResourceList.getSymptoms() {
        return this.symptoms;
    }
    
    public void SymptomResourceList.setSymptoms(List<SymptomResource> symptoms) {
        this.symptoms = symptoms;
    }
    
}
