// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Problem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Problem_Roo_Jpa_Entity {
    
    declare @type: Problem: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Problem.id;
    
    @Version
    @Column(name = "version")
    private Integer Problem.version;
    
    public Long Problem.getId() {
        return this.id;
    }
    
    public void Problem.setId(Long id) {
        this.id = id;
    }
    
    public Integer Problem.getVersion() {
        return this.version;
    }
    
    public void Problem.setVersion(Integer version) {
        this.version = version;
    }
    
}
