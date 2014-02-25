package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductTranslation;

public class ProductResourceAssembler {

	

	public ProductResource toResource(Product p, Languages language) {
		
		ProductResource pr = new ProductResource();
		pr.setId(p.getId());
		pr.setPictureUrl(p.getPictureUrl());
		if (language != null){
			ProductTranslation pt = ProductTranslation.findProductTranslationsByLang(language).getSingleResult();
			if (pt != null){
				pr.setName(pt.getName());
				pr.setDescription(pt.getDescription());
				pr.setWarning(pt.getWarning());
			}
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
