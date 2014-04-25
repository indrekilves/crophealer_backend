package com.crophealer.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientTranslation;
import com.crophealer.domain.Country;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantTranslation;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemActiveIngredient;
import com.crophealer.domain.ProblemTranslation;

public class ActiveIngredientLoader extends GenericLoader {
	protected Integer activeSheetNum = 1;
	protected Integer plantColNum = 2;
	protected Integer problemStartColNum = 3;
	protected Integer lastRow = 0;
	private HashMap<Integer, Problem> problemsByCols;

	
	public ActiveIngredientLoader(SpreadSheetReader ssReader) {
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
		this.loadProblemsByCols();
		this.lastRow = ssReader.getColumnAsArray(2).size() - 1;
	}
	
	
	protected void loadProblemsByCols()
	{
		this.problemsByCols = new HashMap<Integer, Problem>();
		List<String> probsInEst = this.ssReader.getRowAsArray(0, this.problemStartColNum);

		for (int i = 0; i < probsInEst.size(); i++) {			
			ProblemTranslation probTrans = ProblemTranslation.getSingleProblemTranslationByName(probsInEst.get(i));			
			if (probTrans != null) 
			{
				System.out.println("Loading AI problems, loaded " + probTrans.getName());
				this.problemsByCols.put(i+this.problemStartColNum, probTrans.getProblem());			
			}
		}	
	}
	
	
	// testing auto deployment23456

	public void loadAIs() {
		
		Country country = Country.getSingleCountryByName("Estonia");
		Languages language = Languages.getSingleLanguageByName("Estonian");

		List<String> ais    = this.ssReader.getColumnAsArray(0);
		List<String> aisEst = this.ssReader.getColumnAsArray(1);

		for (int i = 1; i < ais.size(); i++) {
			ActiveIngredient ai;
			if (ais.get(i).isEmpty())
				continue;

			TypedQuery<ActiveIngredient> aiq = ActiveIngredient.findActiveIngredientsByLatinNameEquals(ais.get(i));
			if (aiq.getResultList().size() > 0) 
			{
				ai = aiq.getSingleResult();
			} 
			else 
			{
				ai = new ActiveIngredient();
				ai.setLatinName(ais.get(i));
				ai.setComment(aisEst.get(i));
				ai.setCountry(country);
				ai.persist();

				ActiveIngredientTranslation ait = new ActiveIngredientTranslation();
				ait.setLang(language);
				ait.setActiveIngredient(ai);
				ait.setName(aisEst.get(i));
				ait.persist();
				System.out.println("Loading AIs, saved " + ait.getName());
			}

			this.loadCropsAndProblems(ai, i);

		}
		System.out.println("Finished loading AIs");
	}

	private void loadCropsAndProblems(ActiveIngredient ai, int aiRow) {
		try
		{
			Integer nextAiRow = this.ssReader.getNextFilledRowNum(0, aiRow);

			if (nextAiRow == null) nextAiRow = this.lastRow + 1;

			List<String> plants = this.ssReader.getColumnAsArray(this.plantColNum, aiRow, nextAiRow-1);

			for (int j = 0; j < plants.size(); j++) {
				PlantTranslation plantTrans = PlantTranslation.getSinglePlantTranslationByName(plants.get(j));
				if (plantTrans == null)
				{
					System.out.println("Could not find match for plant: " + plants.get(j));
					continue;
				}
				Plant plant = plantTrans.getPlant();

				for (Integer probCol : this.problemsByCols.keySet()) {
					String aiEff = ssReader.getCellContent(j+aiRow, probCol);
					if (aiEff != "")
					{			 
						Double d = Double.parseDouble(aiEff);
						Integer effInt = d.intValue();
						Problem problem = this.problemsByCols.get(probCol);

						System.out.println("Loading AIs, AI row " + aiRow + ":" + plant.getComment() + " - " + problem.getLatinName() + " - " + aiEff);

						List<PlantPartPhaseProblem> pppps = this.getPlantPartPhaseProblems(plant, problem);
						this.linkPlantPartPhaseProblemsToAIs(ai, pppps, effInt);
					} 
				}		
			}
		}
		catch (Exception e)
		{
			System.out.println("loadCropsAndProblems exception: " + e.getMessage());	
		}
	}
	
	
	protected void linkPlantPartPhaseProblemsToAIs(ActiveIngredient ai, List<PlantPartPhaseProblem> pppps, Integer effect)
	{
		for (PlantPartPhaseProblem pppp : pppps) {
			System.out.println("Creating ProblemActiveIngredient: " + ai.getLatinName() + " - "  + pppp.getComment());
			ProblemActiveIngredient pai = new ProblemActiveIngredient();
			pai.setActiveIngredient(ai);
			pai.setPlantPartPhaseProblem(pppp);
			pai.setComment(ai.getComment() + " - " + pppp.getComment());
			pai.setEffect(effect);
			pai.persist();
		}
	}
	
	

	public List<PlantPartPhaseProblem> getPlantPartPhaseProblems(Plant plant, Problem problem)
	{
		TypedQuery<PlantPartPhaseProblem> ppppQ = PlantPartPhaseProblem.findPlantPartPhaseProblemsByProblem(problem);
		List<PlantPartPhaseProblem> fullList = ppppQ.getResultList();
		List<PlantPartPhaseProblem> resultList = new ArrayList<PlantPartPhaseProblem>();
		
		PlantPartPhaseProblem pppp;
		Plant pppPlant;
		for (int i = 0; i < fullList.size(); i++) {
			pppp = fullList.get(i);
			pppPlant = pppp.getPlant();
			if (pppPlant.equals(plant)) {
				resultList.add(fullList.get(i));
			}			
		}
		return resultList;
	}
}
