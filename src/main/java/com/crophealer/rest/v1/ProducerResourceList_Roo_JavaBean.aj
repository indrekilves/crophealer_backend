// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.ProducerResource;
import com.crophealer.rest.v1.ProducerResourceList;
import java.util.List;

privileged aspect ProducerResourceList_Roo_JavaBean {
    
    public List<ProducerResource> ProducerResourceList.getProducers() {
        return this.producers;
    }
    
    public void ProducerResourceList.setProducers(List<ProducerResource> producers) {
        this.producers = producers;
    }
    
}