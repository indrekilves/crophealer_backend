package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductTranslation;

public class ProductResourceAssembler {

	

	public ProductResource toResource(Product p, Languages language) {
		
		ProductResource pr = new ProductResource();
		pr.setId(p.getId());
		pr.setPictureUrl(p.getPictureUrl());
		if (language != null){
			TypedQuery<ProductTranslation> tq = ProductTranslation.findProductTranslationsByProductAndLang(p, language);
			if (tq != null){			
				ProductTranslation pt = tq.getSingleResult();
				if (pt != null){
					pr.setName(pt.getName());
					pr.setDescription(pt.getDescription());
					pr.setWarning(pt.getWarning());
				}
			}	
		}
		Producer producer = p.getProducer();
		if (producer != null){
			ProducerResourceAssembler asm = new ProducerResourceAssembler();
			pr.setProducer(asm.toResource(producer));
		}

		return pr;
	}

	
	public ProductResourceList toResource(List<Product> pl, Languages l) {
		if (pl == null) return null;

		List<ProductResource> prl = new ArrayList<ProductResource>();
		for (Product p : pl) {
			prl.add(toResource(p, l));
		}
		
		return new ProductResourceList(prl);
	}
}
