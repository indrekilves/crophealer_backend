// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.UserRoleResource;
import com.crophealer.rest.v1.UserRoleResourceList;
import java.util.List;

privileged aspect UserRoleResourceList_Roo_JavaBean {
    
    public List<UserRoleResource> UserRoleResourceList.getUserRoles() {
        return this.userRoles;
    }
    
    public void UserRoleResourceList.setUserRoles(List<UserRoleResource> userRoles) {
        this.userRoles = userRoles;
    }
    
}
