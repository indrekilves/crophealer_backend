// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.DiagnosedProblemPicture;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect DiagnosedProblemPicture_Roo_Jpa_Entity {
    
    declare @type: DiagnosedProblemPicture: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long DiagnosedProblemPicture.id;
    
    @Version
    @Column(name = "version")
    private Integer DiagnosedProblemPicture.version;
    
    public Long DiagnosedProblemPicture.getId() {
        return this.id;
    }
    
    public void DiagnosedProblemPicture.setId(Long id) {
        this.id = id;
    }
    
    public Integer DiagnosedProblemPicture.getVersion() {
        return this.version;
    }
    
    public void DiagnosedProblemPicture.setVersion(Integer version) {
        this.version = version;
    }
    
}
