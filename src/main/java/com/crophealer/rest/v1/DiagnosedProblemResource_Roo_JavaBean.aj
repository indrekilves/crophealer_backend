// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.DiagnosedProblemPictureResourceList;
import com.crophealer.rest.v1.DiagnosedProblemResource;
import java.util.Date;

privileged aspect DiagnosedProblemResource_Roo_JavaBean {
    
    public Long DiagnosedProblemResource.getId() {
        return this.id;
    }
    
    public void DiagnosedProblemResource.setId(Long id) {
        this.id = id;
    }
    
    public String DiagnosedProblemResource.getComment() {
        return this.comment;
    }
    
    public void DiagnosedProblemResource.setComment(String comment) {
        this.comment = comment;
    }
    
    public String DiagnosedProblemResource.getLocation() {
        return this.location;
    }
    
    public void DiagnosedProblemResource.setLocation(String location) {
        this.location = location;
    }
    
    public String DiagnosedProblemResource.getSymptopmIDsCSV() {
        return this.symptopmIDsCSV;
    }
    
    public void DiagnosedProblemResource.setSymptopmIDsCSV(String symptopmIDsCSV) {
        this.symptopmIDsCSV = symptopmIDsCSV;
    }
    
    public String DiagnosedProblemResource.getUserId() {
        return this.userId;
    }
    
    public void DiagnosedProblemResource.setUserId(String userId) {
        this.userId = userId;
    }
    
    public Date DiagnosedProblemResource.getCreatedTimestamp() {
        return this.createdTimestamp;
    }
    
    public void DiagnosedProblemResource.setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    
    public Date DiagnosedProblemResource.getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }
    
    public void DiagnosedProblemResource.setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }
    
    public DiagnosedProblemPictureResourceList DiagnosedProblemResource.getPictures() {
        return this.pictures;
    }
    
    public void DiagnosedProblemResource.setPictures(DiagnosedProblemPictureResourceList pictures) {
        this.pictures = pictures;
    }
    
}
