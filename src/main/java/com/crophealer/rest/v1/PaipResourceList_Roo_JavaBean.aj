// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.PaipResource;
import com.crophealer.rest.v1.PaipResourceList;
import java.util.List;

privileged aspect PaipResourceList_Roo_JavaBean {
    
    public List<PaipResource> PaipResourceList.getPaips() {
        return this.paips;
    }
    
    public void PaipResourceList.setPaips(List<PaipResource> paips) {
        this.paips = paips;
    }
    
}
