// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Company;
import com.crophealer.domain.Field;
import com.crophealer.security.Users;

privileged aspect Field_Roo_JavaBean {
    
    public String Field.getName() {
        return this.name;
    }
    
    public void Field.setName(String name) {
        this.name = name;
    }
    
    public String Field.getCoordinates() {
        return this.coordinates;
    }
    
    public void Field.setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    
    public String Field.getPriaID() {
        return this.priaID;
    }
    
    public void Field.setPriaID(String priaID) {
        this.priaID = priaID;
    }
    
    public Users Field.getOwner() {
        return this.owner;
    }
    
    public void Field.setOwner(Users owner) {
        this.owner = owner;
    }
    
    public Company Field.getCompany() {
        return this.company;
    }
    
    public void Field.setCompany(Company company) {
        this.company = company;
    }
    
}
