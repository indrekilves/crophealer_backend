package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Product;

public class PaipResourceAssembler {

	

	public PaipResource toResource(ProblemAIProduct p) {
		
		PaipResource pr = new PaipResource();
		pr.setId(p.getId());
		pr.setComment(p.getComment());
		pr.setAiEffect(p.getAiEffect());
		pr.setProductEffect(p.getProductEffect());
		
		PlantPartPhaseProblem prob = p.getProblem();
		if (prob != null){
			pr.setProblemID(prob.getId());
		}
		
		ActiveIngredient ai = p.getActiveIngredient();
		if (ai != null){
			pr.setActiveIngredientID(ai.getId());
		}
			
		Product prod = p.getProduct();
		if (prod != null){
			pr.setProductID(prod.getId());
		}
		
		return pr;
	}

	
	public PaipResourceList toResource(List<ProblemAIProduct> pl) {
		if (pl == null) return null;

		List<PaipResource> prl = new ArrayList<PaipResource>();
		for (ProblemAIProduct p : pl) {
			prl.add(toResource(p));
		}
		
		return new PaipResourceList(prl);
	}
}
