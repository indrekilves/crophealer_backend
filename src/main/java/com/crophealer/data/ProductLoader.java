package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.Country;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductTranslation;


public class ProductLoader extends GenericLoader 
{
	
	protected Integer activeSheetNum    		= 6;
	private final Integer rowNumCountry 		= 2;
	private final Integer colProductName   	 	= 0;
	//private final Integer colNumProductsStart 	= 0;
	
	// offsets by productName col
	private final Integer ofType 			= 1;
	private final Integer ofAI				= 2;
	private final Integer ofAIRate 			= 3;
	private final Integer ofAIUnit 			= 4;
	private final Integer ofTKT				= 5;
	private final Integer ofFormulation		= 6;
	private final Integer ofWaterVolume		= 7;
	private final Integer ofPlant			= 8;
	//private final Integer ofUsageRate		= 9;
	private final Integer ofPhase			= 9;
	private final Integer ofProblem			= 10;
	private final Integer ofEffect			= 11;
	private final Integer ofUsageNorm1		= 12;
	private final Integer ofUsageNorm2		= 13;
	private final Integer ofSprinkleTime	= 14; //pritsimise aeg
	private final Integer ofSprinkleFreq	= 15; // pritsimiskordade arv
	private final Integer ofWorkWait		= 16; // tööoode
	private final Integer ofWaitingTime		= 17; // ooteaeg
	private final Integer ofAgentMech     	= 18; // Toimemehhanism
	private final Integer ofRainfastness	= 19; // vihmakindlus
	private final Integer ofComment			= 20; // kommentaar
	private final Integer ofProducerDetails = 21; 
	
	private final Integer problemsStartRow 	= 1;

	
	//private LinkedHashMap<String, Integer> countryCols;

	
	public ProductLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
	}
	
	
	public void loadProducts()
	{
		List<String> productNames = this.ssReader.getColumnAsArray(this.colProductName, this.problemsStartRow);
		
		for (int i = 0; i < productNames.size(); i++) 
		{
			Integer productSectorStartRow = i+this.problemsStartRow;
			String productNameStr = productNames.get(i).trim();
			
			if ( !productNameStr.isEmpty() )
			{
				int productSectorEndRow = this.ssReader.getNextFilledRowNum(this.colProductName, this.problemsStartRow);
				System.out.println("Loading product from row " + productSectorStartRow + " to " + productSectorEndRow + " - " + productNameStr);
				this.processProductSector(productSectorStartRow, productSectorEndRow);
			}		
		}
	}


	private void processProductSector(Integer productSectorStartRow,
			int productSectorEndRow) {
		Product product = loadAndGetProduct(productSectorStartRow);
		//ActiveIngredient ai = loadAndGetAI(productSectorStartRow);
		
		
		
	}

	/*
	private ActiveIngredient loadAndGetAI(Integer productSectorStartRow) {
		String productNameStr = this.ssReader.getCellContent(productSectorStartRow, this.colProductName);
		if ( !productNameStr.isEmpty())
		{
			ActiveIngredient ai = ActiveIngredient.find
			
			
			if (product == null) {
				return this.addNewProduct(productSectorStartRow);
			} else {
				return product;
			}
		
		}
		else
			return null;
	}*/


	private Product loadAndGetProduct(Integer productSectorStartRow) {
		String productNameStr = this.ssReader.getCellContent(productSectorStartRow, this.colProductName);
		if ( !productNameStr.isEmpty())
		{
			Product product = Product.getSingleProductByComment(productNameStr);
			
			if (product == null) {
				return this.addNewProduct(productSectorStartRow);
			} else {
				return product;
			}
		
		}
		else
			return null;
	}


	private Product addNewProduct(Integer productSectorStartRow) {
		Product p = new Product();
		List<String> pDetails = this.ssReader.getRowAsArray(productSectorStartRow);
		p.setComment(pDetails.get(this.colProductName));
		p.setRaintFastness(pDetails.get(this.ofRainfastness + this.colProductName));
		p.setCountry(Country.getSingleCountryByName("Estonia"));
		p.persist();
		
		this.loadProductTranslations(productSectorStartRow, p);
		
		return null;
	}


	private void loadProductTranslations(Integer productSectorStartRow,	Product p) {
		List<String> pDetails = this.ssReader.getRowAsArray(productSectorStartRow);
		ProductTranslation ptrans = new ProductTranslation();
		ptrans.setLang(Languages.getSingleLanguageByName("Estonian"));
		ptrans.setActiveIngredientRate(pDetails.get(this.ofAIRate + this.colProductName));
		ptrans.setDescription(p.getComment());
		ptrans.setFormulation(pDetails.get(this.ofFormulation + this.colProductName));
		//ptrans.setLatestUsegeTimeSprayInterval(pDetails.get(this.));
		ptrans.setName(p.getComment());
		//ptrans.setPpc(ppc);
		ptrans.setProduct(p);
		ptrans.setRaintFastness(p.getRaintFastness());
		ptrans.setType(pDetails.get(this.ofType + this.colProductName));
		ptrans.setUsageRate(pDetails.get(this.ofUsageNorm1 + this.colProductName));
		ptrans.setWarning("");
		ptrans.setWaterVolume(pDetails.get(this.ofWaterVolume + this.colProductName));
		
		ptrans.persist();
	}
	
	/*
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
	
*/	
	
	
	
	
	
	
}
