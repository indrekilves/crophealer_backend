// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPartPhaseSymptom;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PlantPartPhaseSymptom_Roo_Jpa_Entity {
    
    declare @type: PlantPartPhaseSymptom: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PlantPartPhaseSymptom.id;
    
    @Version
    @Column(name = "version")
    private Integer PlantPartPhaseSymptom.version;
    
    public Long PlantPartPhaseSymptom.getId() {
        return this.id;
    }
    
    public void PlantPartPhaseSymptom.setId(Long id) {
        this.id = id;
    }
    
    public Integer PlantPartPhaseSymptom.getVersion() {
        return this.version;
    }
    
    public void PlantPartPhaseSymptom.setVersion(Integer version) {
        this.version = version;
    }
    
}
