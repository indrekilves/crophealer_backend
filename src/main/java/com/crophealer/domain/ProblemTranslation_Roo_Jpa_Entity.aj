// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ProblemTranslation;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect ProblemTranslation_Roo_Jpa_Entity {
    
    declare @type: ProblemTranslation: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ProblemTranslation.id;
    
    @Version
    @Column(name = "version")
    private Integer ProblemTranslation.version;
    
    public Long ProblemTranslation.getId() {
        return this.id;
    }
    
    public void ProblemTranslation.setId(Long id) {
        this.id = id;
    }
    
    public Integer ProblemTranslation.getVersion() {
        return this.version;
    }
    
    public void ProblemTranslation.setVersion(Integer version) {
        this.version = version;
    }
    
}
