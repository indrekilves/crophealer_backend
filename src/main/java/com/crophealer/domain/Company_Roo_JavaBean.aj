// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Company;
import com.crophealer.domain.Field;
import com.crophealer.security.Users;
import java.util.Set;

privileged aspect Company_Roo_JavaBean {
    
    public String Company.getName() {
        return this.name;
    }
    
    public void Company.setName(String name) {
        this.name = name;
    }
    
    public String Company.getAddress() {
        return this.address;
    }
    
    public void Company.setAddress(String address) {
        this.address = address;
    }
    
    public String Company.getPhone() {
        return this.phone;
    }
    
    public void Company.setPhone(String phone) {
        this.phone = phone;
    }
    
    public String Company.getEmail() {
        return this.email;
    }
    
    public void Company.setEmail(String email) {
        this.email = email;
    }
    
    public String Company.getContactPerson() {
        return this.contactPerson;
    }
    
    public void Company.setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    public String Company.getFieldSize() {
        return this.fieldSize;
    }
    
    public void Company.setFieldSize(String fieldSize) {
        this.fieldSize = fieldSize;
    }
    
    public Set<Users> Company.getUsers() {
        return this.users;
    }
    
    public void Company.setUsers(Set<Users> users) {
        this.users = users;
    }
    
    public Set<Field> Company.getFields() {
        return this.fields;
    }
    
    public void Company.setFields(Set<Field> fields) {
        this.fields = fields;
    }
    
}