package com.crophealer.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.PlantTranslation;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.ProblemTranslation;
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
	private final Integer ofFormulation		= 6; // Preparaadi vorm
	private final Integer ofWaterVolume		= 7;
	private final Integer ofPlant			= 8;
	private final Integer ofPhase			= 9;
	private final Integer ofProblem			= 10;
	private final Integer ofEffect			= 11;
	private final Integer ofUsageNorm1		= 12;
	private final Integer ofUsageNorm2		= 13;
	private final Integer ofSprinkleTime	= 14; //pritsimise aeg
	private final Integer ofSprinkleTimes	= 15; // pritsimiskordade arv
	private final Integer ofWorkDelay		= 16; // tööoode
	private final Integer ofHarvestDelay	= 17; // ooteaeg
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
		//Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	    //logger.setLevel(Level.INFO);
		this.LOGGER.info("Started ProductLoader");
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
				int productSectorEndRow = this.ssReader.getNextFilledRowNum(this.colProductName, i + 1) - 1;
				System.out.println("Loading product from row " + productSectorStartRow + " to " + productSectorEndRow + " - " + productNameStr);				
				this.processProductSector(productSectorStartRow, productSectorEndRow);			
			}		
		}
		System.out.println("Finished loading products.");
	}


	private void processProductSector(Integer productSectorStartRow, int productSectorEndRow) {
		try {
			Product product 	= loadAndGetProduct(productSectorStartRow);
			ActiveIngredient ai = loadAndGetAI(productSectorStartRow);
			Plant plant 		= loadAndGetPlant(productSectorStartRow);

			for (int curRow = productSectorStartRow; curRow < productSectorEndRow; curRow++) {
				List<String> pDetails = this.ssReader.getRowAsArray(curRow);

				List<GrowthPhase> phases = this.getPhasesByPlant(plant, pDetails);
				if (phases.size() < 1)
					continue;

				Problem problem = this.getProblem(pDetails);
				if (problem == null) 
					continue;

				List<PlantPartPhaseProblem> pppProblems = this.getPlantPartPhaseProblems(plant, problem, phases);
				if (pppProblems.size() < 1)
					continue;
				
				int x = 1;
				System.out.println("Found plant part problems: " + pppProblems.size());
				
				for (PlantPartPhaseProblem plantPartPhaseProblem : pppProblems) {
					System.out.print(x++ + ", ");
					this.addProblemAIProduct(product, ai, plantPartPhaseProblem, pDetails);			
				}
				
				curRow++;
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Failed to load problem: " + e.getMessage());
		}
	}
	
	
	private void addProblemAIProduct(Product product, ActiveIngredient ai, PlantPartPhaseProblem plantPartPhaseProblem, List<String> pDetails) {
		ProblemAIProduct paip = new ProblemAIProduct();
		paip.setActiveIngredient(ai);
		paip.setProduct(product);
		paip.setProblem(plantPartPhaseProblem);
		
		Integer effectInt = this.getEffectFormatted(pDetails.get(this.colProductName + this.ofEffect));
		paip.setAiEffect(effectInt );
		
		paip.setProductEffect(effectInt );
		paip.persist();
	}
	
	
	private Integer getEffectFormatted(String effectStr) {
		try {
			if (effectStr.isEmpty()){
				return 99;
			}
			Double d = Double.parseDouble(effectStr);
			Integer effectInt = d.intValue();
			return effectInt;
		} catch (Exception e) {	
			System.out.println("bad [getEffectFormatted]");
			throw e;
		}
	}
	
	
	

	
	private List<PlantPartPhaseProblem> getPlantPartPhaseProblems(Plant plant, Problem problem, List<GrowthPhase> phases) {
		try {
			List<PlantPartPhaseProblem> pppProblems = new ArrayList<PlantPartPhaseProblem>();

			for (GrowthPhase phase : phases) {

				TypedQuery<PlantPartPhaseProblem> ppppQ = PlantPartPhaseProblem.findPlantPartPhaseProblemsByProblem(problem);
				List<PlantPartPhaseProblem> ppppS = ppppQ.getResultList();

				for (PlantPartPhaseProblem plantPartPhaseProblem : ppppS) {
					/*
					System.out.println("Finding Plant for plantPartPhaseProblem " + plantPartPhaseProblem.getId());
					if (plantPartPhaseProblem.getComment().equals("Erysiphe graminis, Blumeria graminis - Winter wheat - F69 - Pähik")) {
						System.out.println("Finding Plant for plantPartPhaseProblem " + plantPartPhaseProblem.getId());
					}*/
					Plant problemPlant = plantPartPhaseProblem.getPlant();
					
					if (problemPlant == null) {
						System.out.println("ProblemPlant is null:" + plantPartPhaseProblem.getComment());
					}
					
					if (plantPartPhaseProblem.getPlant().equals(plant)) {
						
						TypedQuery<PlantPartPhaseSymptom> symptomsQ = PlantPartPhaseSymptom.findPlantPartPhaseSymptomsByProblem(plantPartPhaseProblem);
						if (symptomsQ.getResultList().size() > 0) {
							
							List<PlantPartPhaseSymptom> symptoms = symptomsQ.getResultList();
							for (PlantPartPhaseSymptom plantPartPhaseSymptom : symptoms) {
								GrowthPhase symptomPhase = plantPartPhaseSymptom.getPlantPartPhase().getGrowthPhase();
								
								if (symptomPhase == null) {
									System.out.println("Symptomphase is null");
								}
								
								if (symptomPhase.equals(phase)) {
									pppProblems.add(plantPartPhaseProblem);
								}
							}
						}
					}
				}

			}
			return pppProblems;
		} catch (Exception e) {
			System.out.println("bad [getPlantPartPhaseProblems]");
			throw e;
		}
	}


	private List<GrowthPhase> getPhasesByPlant(Plant plant, List<String> pDetails) {
		try {
			List<GrowthPhase> returnPhases = new ArrayList<GrowthPhase>(); 
			int fromPhase;
			int toPhase;

			String phaseRange = pDetails.get(this.ofPhase + this.colProductName);

			if (phaseRange.contains("-")) {
				String[] split = phaseRange.split("-");
				fromPhase = Integer.parseInt(split[0]);
				toPhase = Integer.parseInt(split[1]);
			} else if (phaseRange.contains("–")) {
				String[] split = phaseRange.split("–");
				fromPhase = Integer.parseInt(split[0]);
				toPhase = Integer.parseInt(split[1]);
			} else if (phaseRange.contains("A")) {
				// all phases 
				fromPhase 	= 0;
				toPhase		= 99;
			} else {
				fromPhase = Integer.parseInt(phaseRange);
				toPhase = fromPhase;
			}

			List<GrowthPhase> allPhases = GrowthPhase.findAllGrowthPhases();

			for (GrowthPhase growthPhase : allPhases) {
				if (plant.isOSR() == growthPhase.isOSRPhase() && !growthPhase.getComment().isEmpty())
				{
					String numeric = growthPhase.getComment();
					numeric = numeric.replace("F", "");
					numeric = numeric.replace("R", "");
					int phaseNum = Integer.parseInt(numeric);

					if (phaseNum >= fromPhase && phaseNum <= toPhase) {
						returnPhases.add(growthPhase);
					}
				}
			}

			return returnPhases;
		} catch (Exception e) {
			System.out.println("bad [getPhasesByPlant]");
			throw e;
		}
	}


	private Problem getProblem(List<String> pDetails) {
		try {
		String probName = pDetails.get(this.ofProblem + this.colProductName);
		ProblemTranslation probTrans = ProblemTranslation.getSingleProblemTranslationByName(probName.trim());
		if (probTrans != null)
			return probTrans.getProblem();
		else {
			System.out.println("Did not find problem:" + probName);
			return null;
		}
		} catch (Exception e) {
			System.out.println("bad [getProblem]");
			throw e;
		}
	}


	private Plant loadAndGetPlant(Integer productSectorStartRow) {
		try {
			String plantNameStr = this.ssReader.getCellContent(productSectorStartRow, this.colProductName + this.ofPlant);
			if ( !plantNameStr.isEmpty())
			{
				PlantTranslation plantTrans = PlantTranslation.getSinglePlantTranslationByName(plantNameStr);
				if (plantTrans != null) {
					Plant plant = plantTrans.getPlant();
					return plant;
				} else {
					System.out.println("Plant not found: " + plantNameStr);
					return null;
				}				
			} else {
				System.out.println("Plant empty on row " + productSectorStartRow);
				return null;
			}
		} catch (Exception e) {
			System.out.println("bad [loadAndGetPlant]");
			throw e;
		}
	}


	private ActiveIngredient loadAndGetAI(Integer productSectorStartRow) {
		try {
			String aiNameStr = this.ssReader.getCellContent(productSectorStartRow, this.colProductName + this.ofAI);
			if ( !aiNameStr.isEmpty())
			{
				ActiveIngredient ai = ActiveIngredient.getSingleAIByComment(aiNameStr);

				if (ai == null) {
					return this.addNewAi(productSectorStartRow);
				} else {
					return ai;
				}		
			}
			else
				return null;
		} catch (Exception e) {
			System.out.println("bad [loadAndGetAI]");
			throw e;
		}
	}


	private ActiveIngredient addNewAi(Integer productSectorStartRow) {
		Integer i = this.colProductName;
		List<String> pDetails = this.ssReader.getRowAsArray(productSectorStartRow);
				
		ActiveIngredient ai = new ActiveIngredient();
		ai.setComment(pDetails.get(this.ofAI + i));
		ai.setCountry(Country.getSingleCountryByName("Estonia"));
		ai.setLatinName(ai.getComment());
		ai.persist();
		return ai;
	}


	private Product loadAndGetProduct(Integer productSectorStartRow) {
		try {
			String productNameStr = this.ssReader.getCellContent(productSectorStartRow, this.colProductName);
			if ( !productNameStr.isEmpty())
			{
				Product product = Product.getSingleProductByComment(productNameStr);

				if (product == null) {
					product = this.addNewProduct(productSectorStartRow);
					return product;
				} else {
					return product;
				}

			}
			else
				return null;
		} catch (Exception e) {
			System.out.println("bad [loadAndGetProduct]");
			throw e;
		}
	}


	private Product addNewProduct(Integer productSectorStartRow) {
		try {
			Product p = new Product();
			List<String> pDetails = this.ssReader.getRowAsArray(productSectorStartRow);
			p.setComment(pDetails.get(this.colProductName));
			p.setRaintFastness(pDetails.get(this.ofRainfastness + this.colProductName));
			p.setCountry(Country.getSingleCountryByName("Estonia"));
			p.persist();

			this.loadProductTranslations(productSectorStartRow, p);

			return p;
		} catch (Exception e) {
			System.out.println("bad [addNewProduct]");
			throw e;
		}
	}


	private void loadProductTranslations(Integer productSectorStartRow,	Product p) {
		try {
			List<String> pDetails = this.ssReader.getRowAsArray(productSectorStartRow);
			ProductTranslation ptrans = new ProductTranslation();

			ptrans.setProduct(p);
			ptrans.setLang(Languages.getSingleLanguageByName("Estonian"));
			ptrans.setDescription(p.getComment());

			ptrans.setName(p.getComment());
			ptrans.setType(pDetails.get(this.ofType + this.colProductName));

			String aiRate = pDetails.get(this.ofAIRate + this.colProductName);
			String aiUnit = pDetails.get(this.ofAIUnit + this.colProductName);
			ptrans.setActiveIngredientRate(aiRate + " " + aiUnit);


			ptrans.setPpc(pDetails.get(this.ofTKT + this.colProductName));
			ptrans.setFormulation(pDetails.get(this.ofFormulation + this.colProductName));
			ptrans.setWaterVolume(pDetails.get(this.ofWaterVolume + this.colProductName));

			String norm1 = pDetails.get(this.ofUsageNorm1 + this.colProductName);
			String norm2 = pDetails.get(this.ofUsageNorm2 + this.colProductName);
			ptrans.setUsageRate(this.getUsageNormString(norm1, norm2));
			ptrans.setSprinkleTime(pDetails.get(this.ofSprinkleTime + this.colProductName));
			ptrans.setSprinkleTimes(pDetails.get(this.ofSprinkleTimes + this.colProductName));

			ptrans.setWorkDelay(pDetails.get(this.ofWorkDelay + this.colProductName));
			ptrans.setHarvestDelay(pDetails.get(this.ofHarvestDelay + this.colProductName));
			ptrans.setEffectMechanism(pDetails.get(this.ofAgentMech + this.colProductName));

			ptrans.setRaintFastness(p.getRaintFastness());

			// not in table yet
			ptrans.setWarning("");

			ptrans.persist();
		} catch (Exception e) {
			System.out.println("bad [loadProductTranslations]");
			throw e;
		}
	}
	
	private String getUsageNormString(String norm1, String norm2) {
		if (norm2.equals("0"))
			return norm1;
		else
			return norm1 + "-" + norm2;
	}
	

	
	
}
