// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.rest.v1;

import com.crophealer.rest.v1.ProductResource;
import com.crophealer.rest.v1.ProductResourceList;
import java.util.List;

privileged aspect ProductResourceList_Roo_JavaBean {
    
    public List<ProductResource> ProductResourceList.getProducts() {
        return this.products;
    }
    
    public void ProductResourceList.setProducts(List<ProductResource> products) {
        this.products = products;
    }
    
}
