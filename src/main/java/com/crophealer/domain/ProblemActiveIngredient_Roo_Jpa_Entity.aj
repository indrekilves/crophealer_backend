// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ProblemActiveIngredient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect ProblemActiveIngredient_Roo_Jpa_Entity {
    
    declare @type: ProblemActiveIngredient: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ProblemActiveIngredient.id;
    
    @Version
    @Column(name = "version")
    private Integer ProblemActiveIngredient.version;
    
    public Long ProblemActiveIngredient.getId() {
        return this.id;
    }
    
    public void ProblemActiveIngredient.setId(Long id) {
        this.id = id;
    }
    
    public Integer ProblemActiveIngredient.getVersion() {
        return this.version;
    }
    
    public void ProblemActiveIngredient.setVersion(Integer version) {
        this.version = version;
    }
    
}
