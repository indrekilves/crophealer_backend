// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.crophealer.domain;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Product;

privileged aspect ProblemAIProduct_Roo_JavaBean {
    
    public PlantPartPhaseProblem ProblemAIProduct.getProblem() {
        return this.problem;
    }
    
    public void ProblemAIProduct.setProblem(PlantPartPhaseProblem problem) {
        this.problem = problem;
    }
    
    public ActiveIngredient ProblemAIProduct.getActiveIngredient() {
        return this.activeIngredient;
    }
    
    public void ProblemAIProduct.setActiveIngredient(ActiveIngredient activeIngredient) {
        this.activeIngredient = activeIngredient;
    }
    
    public Product ProblemAIProduct.getProduct() {
        return this.product;
    }
    
    public void ProblemAIProduct.setProduct(Product product) {
        this.product = product;
    }
    
    public String ProblemAIProduct.getComment() {
        return this.comment;
    }
    
    public void ProblemAIProduct.setComment(String comment) {
        this.comment = comment;
    }
    
    public Integer ProblemAIProduct.getAiEffect() {
        return this.aiEffect;
    }
    
    public void ProblemAIProduct.setAiEffect(Integer aiEffect) {
        this.aiEffect = aiEffect;
    }
    
    public Integer ProblemAIProduct.getProductEffect() {
        return this.productEffect;
    }
    
    public void ProblemAIProduct.setProductEffect(Integer productEffect) {
        this.productEffect = productEffect;
    }
    
}
