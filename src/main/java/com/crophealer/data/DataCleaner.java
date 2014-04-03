package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.PlantPartTranslation;
import com.crophealer.domain.PlantTranslation;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.domain.ProblemTranslation;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.ProductTranslation;
import com.crophealer.domain.Reseller;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomTranslation;

public class DataCleaner 
{
	
	
	public void truncateAllTables()
	{
		List<Producer> ps = Producer.findAllProducers();
		for (Producer producer : ps) {
			producer.remove();
			producer.flush();
		}
		List<ProductReseller> pr = ProductReseller.findAllProductResellers();
		for (ProductReseller productReseller : pr) {
			productReseller.remove();
			productReseller.flush();
		}
		List<ProductTranslation> prodTrans = ProductTranslation.findAllProductTranslations();
		for (ProductTranslation productTranslation : prodTrans) {
			productTranslation.remove();
			productTranslation.flush();
		}
		
		List<ProblemActiveIngredient> pai = ProblemActiveIngredient.findAllProblemActiveIngredients();
		for (ProblemActiveIngredient problemActiveIngredient : pai) {
			problemActiveIngredient.remove();
			problemActiveIngredient.flush();
		}
		
		List<Product> ps2 = Product.findAllProducts();
		for (Product product : ps2) {
			product.remove();
			product.flush();
		}	
		List<Reseller> rs = Reseller.findAllResellers();
		for (Reseller reseller : rs) {
			reseller.remove();
			reseller.flush();
		}
		
		List<PlantPartPhaseProblem> pppps = PlantPartPhaseProblem.findAllPlantPartPhaseProblems();
		for (PlantPartPhaseProblem plantPartPhaseProblem : pppps) {
			plantPartPhaseProblem.remove();
			plantPartPhaseProblem.flush();
		}
		
		List<PlantPartPhaseSymptom> ppps = PlantPartPhaseSymptom.findAllPlantPartPhaseSymptoms();
		for (PlantPartPhaseSymptom plantPartPhaseSymptom : ppps) {
			plantPartPhaseSymptom.remove();
			plantPartPhaseSymptom.flush();		
		}
		
		List<PlantPartPhase> ppp = PlantPartPhase.findAllPlantPartPhases();
		for (PlantPartPhase plantPartPhase : ppp) {
			plantPartPhase.remove();
			plantPartPhase.flush();
		}
		
		List<SymptomTranslation> sympTrans = SymptomTranslation.findAllSymptomTranslations();
		for (SymptomTranslation symptomTranslation : sympTrans) {
			symptomTranslation.remove();
			symptomTranslation.flush();
		}
		List<Symptom> symptoms = Symptom.findAllSymptoms();
		for (Symptom symptom : symptoms) {
			symptom.remove();
			symptom.flush();
		}
		
		List<PlantPart> pp = PlantPart.findAllPlantParts();
		for (PlantPart plantPart : pp) {
			plantPart.remove();
			plantPart.flush();
		}
		List<PlantPartTranslation> ppls = PlantPartTranslation.findAllPlantPartTranslations();
		for (PlantPartTranslation plantPartTranslation : ppls) {
			plantPartTranslation.remove();
			plantPartTranslation.flush();
		}
		List<ProblemTranslation> problemTrans = ProblemTranslation.findAllProblemTranslations();
		for (ProblemTranslation problemTranslation : problemTrans) {
			problemTranslation.remove();
			problemTranslation.flush();
		}
		List<Problem> problems = Problem.findAllProblems();
		for (Problem problem : problems) {
			problem.remove();
			problem.flush();			
		}
		List<GrowthPhase> gs = GrowthPhase.findAllGrowthPhases();
		for (GrowthPhase growthPhase : gs) {
			growthPhase.remove();
			growthPhase.flush();
		}
		List<Plant> plants = Plant.findAllPlants();
		for (Plant plant : plants) {
			plant.remove();
			plant.flush();
		}
		List<PlantTranslation> plantTranslations = PlantTranslation.findAllPlantTranslations();
		for (PlantTranslation plantTranslation : plantTranslations) {
			plantTranslation.remove();
			plantTranslation.flush();
		}
		
		List<ProblemActiveIngredient> problemAIs = ProblemActiveIngredient.findAllProblemActiveIngredients();
		for (ProblemActiveIngredient problemActiveIngredient : problemAIs) {
			problemActiveIngredient.remove();
			problemActiveIngredient.flush();
		}
		
		List<ActiveIngredient> as = ActiveIngredient.findAllActiveIngredients();
		for (ActiveIngredient activeIngredient : as) {
			activeIngredient.remove();
			activeIngredient.flush();
		}
		List<ActiveIngredientProduct> aip = ActiveIngredientProduct.findAllActiveIngredientProducts();
		for (ActiveIngredientProduct activeIngredientProduct : aip) {
			activeIngredientProduct.remove();
			activeIngredientProduct.flush();
		}
		
		List<Country> cs = Country.findAllCountrys();
		for (Country country : cs) {
			country.remove();
			country.flush();
		}
		List<Languages> ls = Languages.findAllLanguageses();
		for (Languages languages : ls) {
			languages.remove();
			languages.flush();
		}
	}
	
	
	
}
