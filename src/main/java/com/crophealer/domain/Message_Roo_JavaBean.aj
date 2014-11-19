// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Field;
import com.crophealer.domain.Message;
import com.crophealer.security.Users;
import java.util.Date;
import java.util.Set;

privileged aspect Message_Roo_JavaBean {
    
    public String Message.getSubject() {
        return this.subject;
    }
    
    public void Message.setSubject(String subject) {
        this.subject = subject;
    }
    
    public String Message.getComment() {
        return this.comment;
    }
    
    public void Message.setComment(String comment) {
        this.comment = comment;
    }
    
    public String Message.getStatus() {
        return this.status;
    }
    
    public void Message.setStatus(String status) {
        this.status = status;
    }
    
    public Users Message.getSender() {
        return this.sender;
    }
    
    public void Message.setSender(Users sender) {
        this.sender = sender;
    }
    
    public Users Message.getReceiver() {
        return this.receiver;
    }
    
    public void Message.setReceiver(Users receiver) {
        this.receiver = receiver;
    }
    
    public DiagnosedProblem Message.getDiagnosedProblem() {
        return this.diagnosedProblem;
    }
    
    public void Message.setDiagnosedProblem(DiagnosedProblem diagnosedProblem) {
        this.diagnosedProblem = diagnosedProblem;
    }
    
    public Message Message.getParent() {
        return this.parent;
    }
    
    public void Message.setParent(Message parent) {
        this.parent = parent;
    }
    
    public Set<Message> Message.getChildren() {
        return this.children;
    }
    
    public Date Message.getCreatedTimestamp() {
        return this.createdTimestamp;
    }
    
    public void Message.setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    
    public Date Message.getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }
    
    public void Message.setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }
    
    public Field Message.getField() {
        return this.field;
    }
    
    public void Message.setField(Field field) {
        this.field = field;
    }
    
}
