// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.PlantPartPhaseSymptomResource;
import com.crophealer.rest.v1.PlantPartPhaseSymptomResourceList;
import java.util.List;

privileged aspect PlantPartPhaseSymptomResourceList_Roo_JavaBean {
    
    public List<PlantPartPhaseSymptomResource> PlantPartPhaseSymptomResourceList.getPlantPartPhaseSymptoms() {
        return this.plantPartPhaseSymptoms;
    }
    
    public void PlantPartPhaseSymptomResourceList.setPlantPartPhaseSymptoms(List<PlantPartPhaseSymptomResource> plantPartPhaseSymptoms) {
        this.plantPartPhaseSymptoms = plantPartPhaseSymptoms;
    }
    
}
