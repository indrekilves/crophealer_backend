package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantTranslation;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.Reseller;

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
		
	}
}
