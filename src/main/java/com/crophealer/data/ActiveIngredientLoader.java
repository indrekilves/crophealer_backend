package com.crophealer.data;

import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Product;

public class ActiveIngredientLoader 
{

	private SpreadSheetReader ssReader;
	
	public ActiveIngredientLoader(SpreadSheetReader ssReader)
	{
		this.ssReader = ssReader;
		this.ssReader.setActiveSheetNum(1);
	}
	
	
	public void loadAIs()
	{
		List<String> ais = this.ssReader.getColumnAsArray(0, 4);
		
		for (int i = 0; i < ais.size(); i++) 
		{
			ActiveIngredient ai;
			TypedQuery<ActiveIngredient> aiq = ActiveIngredient.findActiveIngredientsByLatinNameEquals(ais.get(i));
			if (aiq.getResultList().size() > 0)
			{
				ai = aiq.getSingleResult();
			}
			else
			{
				ai = new ActiveIngredient();
				ai.setLatinName(ais.get(i));
				ai.persist();
			}	
			
			List<String> aiDetails = this.ssReader.getRowAsArray(i + 4);
			Product product = ProductLoader.loadProductWithDetails(aiDetails.get(1), aiDetails.get(2), aiDetails.get(3));
			
			ActiveIngredientProduct aip = new ActiveIngredientProduct();
			aip.setProduct(product);
			aip.setActiveIngredient(ai);
			aip.persist();
			
			
		}
	}
	
	
	
	
	public void loadAITranslations()
	{
		
	}
	
	
}
