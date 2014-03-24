package com.crophealer.data;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.ActiveIngredientTranslation;
import com.crophealer.domain.Country;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductTranslation;

public class DataLoader 
{
	protected String fileName;
	protected SpreadSheetReader ssReader;
	
	
	public DataLoader()
	{
		this.loadFileName();
	}

	
	public void loadFileName() 
	{
		this.fileName = this.getDatafileRelativePath();		
	}
	
	
	public String getDatafileRelativePath()
	{
		return "/data/diseases_base_cereals.xls";
	}
	
	
	
	public void runDataLoad()
	{
		switch (this.getFileType()) 
		{
		case "XLS":
			this.loadFromExcel();
			break;
		case "ODS":
			this.loadFromODS();
			break;
		default:
			break;
		}
	}


	
	private void loadFromExcel() 
	{
		this.ssReader = new ExcelReader();
		this.ssReader.setFileName(this.fileName);
		this.ssReader.loadWorkBook();
		
		DataCleaner dCleaner = new DataCleaner();
		dCleaner.truncateAllTables();

		// load capsulated data:
		this.loadCountries();
		this.loadLanguages();
		this.loadPhases();
		this.loadProducers();
		this.loadResellers();
		this.loadActiveIngredientsAndProducts();
		this.loadPlants();
		this.loadProblems();
		this.loadTempDemoData();
		
		//this.loadProductsWithProblemLinks();
		
	}

	
	
	private void loadProductsWithProblemLinks() {
		
		
	}


	private void loadTempDemoData() {
		this.loadAarislaiksus();
		this.loadHelelaiksus();
	}
	
	private void loadHelelaiksus() {
		Country country = Country.getSingleCountryByName("Estonia");
		Languages lang = Languages.getSingleLanguageByName("Estonian");
		
		// äärislaiksus
		ActiveIngredient ai = new ActiveIngredient();
		ai.setComment("pikoksüstrobiin");
		ai.setCountry(country);
		ai.setLatinName("picoxystrobin");
		ai.persist();
		
		ActiveIngredientTranslation aiTrans = new ActiveIngredientTranslation();
		aiTrans.setActiveIngredient(ai);
		aiTrans.setLang(lang);
		aiTrans.setName(ai.getComment());
		aiTrans.persist();
				
		Product product = new Product();
		product.setComment("Acanto 250 SC");
		product.setEfficiency((long) 5);
		product.setRaintFastness("2");
		product.persist();
		
		ActiveIngredientProduct aip = new ActiveIngredientProduct();
		aip.setActiveIngredient(ai);
		aip.setProduct(product);
		aip.setComment(ai.getLatinName() + " - "  + product.getComment() );
		aip.persist();
				
		ProductTranslation productTrans = new ProductTranslation();
		productTrans.setName("Acanto 250 SC");
		productTrans.setProduct(product);
		productTrans.setWaterVolume("100-300 (optim. 200)");
		productTrans.setType("fungitsiid");
		productTrans.setLang(lang);
		productTrans.setWarning("Parim on piirduda ühe strobiluriinide rühma fungitsiidi pritsega hooajal. Parim aeg pritsimiseks lipulehe faasis.");
		productTrans.setActiveIngredientRate("250");
		productTrans.setLatestUsegeTimeSprayInterval("35/14");
		productTrans.persist();
		
		Problem problem = Problem.getSingleProblemByLatinName("Septoria tritici, Septoria nodorum");
		
		ProblemActiveIngredient problemAI = new ProblemActiveIngredient();
		problemAI.setActiveIngredient(ai);
		problemAI.setProblem(problem);
		problemAI.setComment(problem.getLatinName() + " - " + ai.getComment());
		problemAI.persist();
	}
	

	
	private void loadAarislaiksus()
	{
		Country country = Country.getSingleCountryByName("Estonia");
		Languages lang = Languages.getSingleLanguageByName("Estonian");
		
		// äärislaiksus
		ActiveIngredient ai = new ActiveIngredient();
		ai.setComment("pikoksüstrobiin");
		ai.setCountry(country);
		ai.setLatinName("picoxystrobin");
		ai.persist();
		
		ActiveIngredientTranslation aiTrans = new ActiveIngredientTranslation();
		aiTrans.setActiveIngredient(ai);
		aiTrans.setLang(lang);
		aiTrans.setName(ai.getComment());
		aiTrans.persist();
				
		Product product = new Product();
		product.setComment("Acanto 250 SC");
		product.setEfficiency((long) 5);
		product.setRaintFastness("2");
		product.persist();
		
		ActiveIngredientProduct aip = new ActiveIngredientProduct();
		aip.setActiveIngredient(ai);
		aip.setProduct(product);
		aip.setComment(ai.getLatinName() + " - "  + product.getComment() );
		aip.persist();
				
		ProductTranslation productTrans = new ProductTranslation();
		productTrans.setName("Acanto 250 SC");
		productTrans.setProduct(product);
		productTrans.setWaterVolume("100-300 (optim. 200)");
		productTrans.setType("fungitsiid");
		productTrans.setLang(lang);
		productTrans.setWarning("Parim on piirduda ühe strobiluriinide rühma fungitsiidi pritsega hooajal. Parim aeg pritsimiseks lipulehe faasis.");
		productTrans.setActiveIngredientRate("250");
		productTrans.setLatestUsegeTimeSprayInterval("35/14");
		productTrans.persist();
		
		Problem problem = Problem.getSingleProblemByLatinName("Rhynchosporium graminicola, Rhynchosporium secalis");
		
		ProblemActiveIngredient problemAI = new ProblemActiveIngredient();
		problemAI.setActiveIngredient(ai);
		problemAI.setProblem(problem);
		problemAI.setComment(problem.getLatinName() + " - " + ai.getComment());
		problemAI.persist();
		

	}

	private void loadProblems() {
		ProblemLoader pl = new ProblemLoader(ssReader);
		pl.loadProblems();		
	}



	private void loadPlants() 
	{
		PlantLoader pl = new PlantLoader(this.ssReader);
		pl.loadPlants();
	}



	private void loadActiveIngredientsAndProducts() 
	{
		ActiveIngredientLoader ail = new ActiveIngredientLoader(this.ssReader);
		ail.loadAIs();
		ail.loadAITranslations();
	}


	private void loadResellers() {
		ResellerLoader rl = new ResellerLoader(this.ssReader);
		rl.loadResellers();		
	}



	private void loadProducers() 
	{
		ProducerLoader pl = new ProducerLoader(this.ssReader);
		pl.loadProducers();
	}



	private void loadPhases() 
	{
		ssReader.setActiveSheetNum(2);
		GrowthPhaseLoader gpl = new GrowthPhaseLoader(this.ssReader);
		gpl.loadPhaseCodes();
		gpl.loadPhaseTranslations();				
	}

	

	private void loadCountries()
	{
		CountryLoader cl = new CountryLoader(this.ssReader);
		cl.loadCountries();
	}
	
	
	private void loadLanguages()
	{
		LanguageLoader cl = new LanguageLoader(this.ssReader);
		cl.loadLanguages();
	}

	
	
	
	
	private void loadFromODS() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	private String getFileType() 
	{
		return "XLS";
	}
	
	
	
}
