// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.ResellerResource;
import com.crophealer.rest.v1.ResellerResourceList;
import java.util.List;

privileged aspect ResellerResourceList_Roo_JavaBean {
    
    public List<ResellerResource> ResellerResourceList.getResellers() {
        return this.resellers;
    }
    
    public void ResellerResourceList.setResellers(List<ResellerResource> resellers) {
        this.resellers = resellers;
    }
    
}
