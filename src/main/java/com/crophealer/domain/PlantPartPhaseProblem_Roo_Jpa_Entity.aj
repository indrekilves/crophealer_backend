// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPartPhaseProblem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PlantPartPhaseProblem_Roo_Jpa_Entity {
    
    declare @type: PlantPartPhaseProblem: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PlantPartPhaseProblem.id;
    
    @Version
    @Column(name = "version")
    private Integer PlantPartPhaseProblem.version;
    
    public Long PlantPartPhaseProblem.getId() {
        return this.id;
    }
    
    public void PlantPartPhaseProblem.setId(Long id) {
        this.id = id;
    }
    
    public Integer PlantPartPhaseProblem.getVersion() {
        return this.version;
    }
    
    public void PlantPartPhaseProblem.setVersion(Integer version) {
        this.version = version;
    }
    
}
