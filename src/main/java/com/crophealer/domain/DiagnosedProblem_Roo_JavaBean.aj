// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.DiagnosedProblemPicture;
import com.crophealer.domain.Field;
import com.crophealer.domain.Message;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.security.Users;
import java.util.Date;
import java.util.Set;

privileged aspect DiagnosedProblem_Roo_JavaBean {
    
    public String DiagnosedProblem.getComment() {
        return this.comment;
    }
    
    public void DiagnosedProblem.setComment(String comment) {
        this.comment = comment;
    }
    
    public String DiagnosedProblem.getLocation() {
        return this.location;
    }
    
    public void DiagnosedProblem.setLocation(String location) {
        this.location = location;
    }
    
    public Date DiagnosedProblem.getCreatedTimestamp() {
        return this.createdTimestamp;
    }
    
    public void DiagnosedProblem.setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    
    public Date DiagnosedProblem.getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }
    
    public void DiagnosedProblem.setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }
    
    public Date DiagnosedProblem.getClosedTimestamp() {
        return this.closedTimestamp;
    }
    
    public void DiagnosedProblem.setClosedTimestamp(Date closedTimestamp) {
        this.closedTimestamp = closedTimestamp;
    }
    
    public Users DiagnosedProblem.getUsr() {
        return this.usr;
    }
    
    public void DiagnosedProblem.setUsr(Users usr) {
        this.usr = usr;
    }
    
    public Set<DiagnosedProblemPicture> DiagnosedProblem.getPictures() {
        return this.pictures;
    }
    
    public void DiagnosedProblem.setPictures(Set<DiagnosedProblemPicture> pictures) {
        this.pictures = pictures;
    }
    
    public PlantPartPhaseProblem DiagnosedProblem.getPlantPartPhaseProblem() {
        return this.plantPartPhaseProblem;
    }
    
    public void DiagnosedProblem.setPlantPartPhaseProblem(PlantPartPhaseProblem plantPartPhaseProblem) {
        this.plantPartPhaseProblem = plantPartPhaseProblem;
    }
    
    public String DiagnosedProblem.getSymptomIDsCSV() {
        return this.symptomIDsCSV;
    }
    
    public void DiagnosedProblem.setSymptomIDsCSV(String symptomIDsCSV) {
        this.symptomIDsCSV = symptomIDsCSV;
    }
    
    public Set<Message> DiagnosedProblem.getMessages() {
        return this.messages;
    }
    
    public void DiagnosedProblem.setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
    public Field DiagnosedProblem.getField() {
        return this.field;
    }
    
    public void DiagnosedProblem.setField(Field field) {
        this.field = field;
    }
    
}
