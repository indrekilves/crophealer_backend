// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhaseProduct;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.ProductTranslation;
import java.util.Set;

privileged aspect Product_Roo_JavaBean {
    
    public String Product.getComment() {
        return this.comment;
    }
    
    public void Product.setComment(String comment) {
        this.comment = comment;
    }
    
    public String Product.getPictureUrl() {
        return this.pictureUrl;
    }
    
    public void Product.setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public Country Product.getCountry() {
        return this.country;
    }
    
    public void Product.setCountry(Country country) {
        this.country = country;
    }
    
    public Producer Product.getProducer() {
        return this.producer;
    }
    
    public void Product.setProducer(Producer producer) {
        this.producer = producer;
    }
    
    public Set<ProductTranslation> Product.getTranslations() {
        return this.translations;
    }
    
    public Set<ProductReseller> Product.getProductResellers() {
        return this.productResellers;
    }
    
    public Set<ProblemAIProduct> Product.getActiveIngredientProblemLinks() {
        return this.activeIngredientProblemLinks;
    }
    
    public Set<GrowthPhaseProduct> Product.getGrowthPhaseProducts() {
        return this.growthPhaseProducts;
    }
    
}
