// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredientProduct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect ActiveIngredientProduct_Roo_Jpa_Entity {
    
    declare @type: ActiveIngredientProduct: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ActiveIngredientProduct.id;
    
    @Version
    @Column(name = "version")
    private Integer ActiveIngredientProduct.version;
    
    public Long ActiveIngredientProduct.getId() {
        return this.id;
    }
    
    public void ActiveIngredientProduct.setId(Long id) {
        this.id = id;
    }
    
    public Integer ActiveIngredientProduct.getVersion() {
        return this.version;
    }
    
    public void ActiveIngredientProduct.setVersion(Integer version) {
        this.version = version;
    }
    
}
