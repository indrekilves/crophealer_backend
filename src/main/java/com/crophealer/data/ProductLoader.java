package com.crophealer.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.ProductTranslation;
import com.crophealer.domain.Reseller;

public class ProductLoader 
{

	
	
	public static Product loadProductWithDetails(String productName, String producerName, String resellerName)
	{
		TypedQuery<ProductTranslation> ptq = ProductTranslation.findProductTranslationsByNameEquals(productName);
		if (ptq.getResultList().size() > 0)
		{
			return ptq.getSingleResult().getProduct();
		}
		else
		{
			Product p = new Product();
			p.setComment(productName + " - Estonian");			
			
			ProductTranslation pt = new ProductTranslation();
			pt.setDescription(productName + " - Estonian");
			pt.setName(productName);
			pt.setWarning("Keep out of raech of children.");
			pt.setProduct(p);
			//pt.setLang(Languages.f);
			pt.persist();
			
			Producer producer = ProducerLoader.loadProducerByName(producerName);
			p.setProducer(producer);
			
			Reseller reseller = ResellerLoader.loadResellerByName(resellerName);
			
			ProductReseller pr = new ProductReseller();
			pr.setComment(productName + " - " + resellerName); 
			pr.setProduct(p);
			pr.setReseller(reseller);
			
			Set<ProductReseller> pResellerSet = new HashSet<ProductReseller>();
			pResellerSet.add(pr);
			
			p.setProductResellers(pResellerSet);
			p.persist();
			
			return p;
		}				
	}
	

	
	
	
	
	
	
	
	
}
