package com.crophealer.data;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.ProductTranslation;
import com.crophealer.domain.Reseller;
import com.crophealer.domain.Symptom;

public class ProductLoader extends GenericLoader 
{
	
	protected Integer activeSheetNum    = 6;
	private Integer rowNumCountry 		= 2;
	private Integer colNumGenericName   = 0;
	private Integer colNumProductsStart = 3;
	
	// offsets by country col
	private Integer ofType 		= 1;
	private Integer ofAI		= 2;
	private Integer ofAIRate 	= 3;
	private Integer ofAIUnit 	= 4;
	private Integer ofTKT		= 5;
	private Integer ofFormulation	= 6;
	private Integer ofWaterVolume	= 7;
	private Integer ofPlant			= 8;
	private Integer ofUsageRate		= 9;
	private Integer ofPhase			= 10;
	private Integer ofProblem		= 11;
	private Integer ofEffect		= 12;
	private Integer ofWaitingTime	= 13;
	private Integer ofAgentMech     = 14; //Toimemehhanism
	private Integer ofRainfastness	= 15;
	private Integer ofWarnings		= 16; //Märkused

	
	private LinkedHashMap<String, Integer> countryCols;

	
	public ProductLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
		this.loadCountryStartCols();
	}
	
	private void loadCountryStartCols() 
	{
		this.countryCols = new LinkedHashMap<String, Integer>();
		List<String> countryRow = ssReader.getRowAsArray(rowNumCountry);
		for (int i = 0; i < countryRow.size(); i++) 
		{
			String country = countryRow.get(i);
			if ( !country.isEmpty())
			{
				if (Country.getSingleCountryByName(country) != null)
				{
					this.countryCols.put(country, i);
					
				}
			}
		}
	}

	
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
			Producer producer = ProducerLoader.loadProducerByName(producerName);
			p.setProducer(producer);
			
			p.persist();
			
			ProductTranslation pt = new ProductTranslation();
			pt.setDescription(productName + " - Estonian");
			pt.setName(productName);
			pt.setWarning("Keep out of raech of children.");
			pt.setProduct(p);
			//pt.setLang(Languages.f);
			pt.persist();			
			
			Reseller reseller = ResellerLoader.loadResellerByName(resellerName);
			
			ProductReseller pr = new ProductReseller();
			pr.setComment(productName + " - " + resellerName); 
			pr.setProduct(p);
			pr.setReseller(reseller);
			
			Set<ProductReseller> pResellerSet = new HashSet<ProductReseller>();
			pResellerSet.add(pr);
			
			p.setProductResellers(pResellerSet);
			p.merge();
			
			return p;
		}				
	}
	
	
	public void loadProductsWithProblemLinks()
	{
	  //getNextFilledRowNum(Integer column, Integer fromRow)
		List<String> genericNames = this.ssReader.getColumnAsArray(this.colNumGenericName, this.colNumProductsStart);
		
		for (int i = 0; i < genericNames.size(); i++) 
		{
			Integer realRowNum = i+this.colNumProductsStart;
			String genericName = genericNames.get(i);
			
			if ( !genericName.isEmpty() )
			{
				//this.processProductSector(genericName, realRowNum);
			}		
		}
	}

	
	//private void processProductSector(String genericName, Integer curRowNum) {
	private void processProductSector() {
		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();
			Country country = Country.getSingleCountryByName(countryCol.getKey());
			
			List<String> productNames = this.ssReader.getColumnAsArray(this.colNumGenericName, this.colNumProductsStart);
			
			String curProductName = "";
			
			for (int i = 0; i < productNames.size(); i++) 
			{
				Integer realRowNum = i+this.colNumProductsStart;
				String genericName = productNames.get(i);
				
				if ( !genericName.isEmpty() )
					curProductName = genericName;
				
				//this.loadProductWithTranslation(curProductName, realRowNum)
			}
			
		}	
		
	}
	
	
	
	
	
	
	
	
}
