// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.PlantPart;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PlantPart_Roo_Jpa_Entity {
    
    declare @type: PlantPart: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PlantPart.id;
    
    @Version
    @Column(name = "version")
    private Integer PlantPart.version;
    
    public Long PlantPart.getId() {
        return this.id;
    }
    
    public void PlantPart.setId(Long id) {
        this.id = id;
    }
    
    public Integer PlantPart.getVersion() {
        return this.version;
    }
    
    public void PlantPart.setVersion(Integer version) {
        this.version = version;
    }
    
}
