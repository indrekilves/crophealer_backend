package com.crophealer.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartPhase;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.domain.PlantPartPhaseSymptom;
import com.crophealer.domain.PlantPartTranslation;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemTranslation;
import com.crophealer.domain.Reseller;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomTranslation;

public class ProblemLoader extends GenericLoader
{
	protected Integer activeSheetNum 	= 0;
	private Integer latinNameColNum 	= 7;
	private Integer problemsStartRow 	= 5; 
	private Integer phaseColumn 		= 8;
	private Integer countryRowNum 		= 2;
	private Integer headerRowNum		= 4;
	private LinkedHashMap<String, Integer> countryCols;
	
	// offsets
	private Integer plantPartOS = 1;
	private Integer warningOS	= 3;
	private Integer symptomOS 	= 2;
	
	public ProblemLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
		this.loadCountryStartCols();
	}
	
	
	
	private void loadCountryStartCols() 
	{
		
		
		this.countryCols = new LinkedHashMap<String, Integer>();
		List<String> countryRow = ssReader.getRowAsArray(countryRowNum);
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



	public void loadProblems()
	{	
		List<String> latinNames = this.ssReader.getColumnAsArray(this.latinNameColNum, this.problemsStartRow);
		
		for (int i = 0; i < latinNames.size(); i++) 
		{
			Integer realRowNum = i+this.problemsStartRow;
			String pLatinName = latinNames.get(i);
			
			if ( !pLatinName.isEmpty() )
			{
				this.processProblemSector(pLatinName, realRowNum);
			}		
		}
	}
	
	

	private void processProblemSector(String problemLatin, int pRow) 
	{
		Problem problem = new Problem();
	
		if ( !this.problemExists(problemLatin) )
		{
			problem = this.addProblemByLatin(problemLatin);
			this.loadProblemTranslations(problem, pRow);	
		}
		else
		{
			problem = this.getProblemByLatin(problemLatin);
		}
		
		List<GrowthPhase> phases   = this.getPhasesForProblem(problem, pRow);
		List<PlantPart> plantParts = this.loadAndGetPlantPartsForProblem(pRow);
		List<Plant>		plants	   = this.getPlantsForProblem(problem, pRow);
		List<Symptom>	symptoms   = this.loadAndGetSymptomsForProblem(problem, pRow);
		
		// hurray to relations!
		
		for (Plant plant : plants) {
			for (GrowthPhase phase : phases) {
				for (PlantPart pPart : plantParts) {
					
					if ( !this.plant_plantPart_phase_exists(plant, phase, pPart) )
					{
						PlantPartPhase ppp = new PlantPartPhase();
						ppp.setPlantPart(pPart);
						ppp.setGrowthPhase(phase);
						ppp.setPlant(plant);
						ppp.setComment(plant.getComment() + " - " + phase.getComment() + " - " + pPart.getComment());
						ppp.persist();
						
						PlantPartPhaseProblem pppp = new PlantPartPhaseProblem();
						pppp.setProblem(problem);
						pppp.setComment(problem.getLatinName() + " - " + ppp.getComment());
						pppp.persist();
						
						for (Symptom symptom : symptoms) {
							PlantPartPhaseSymptom ppps = new PlantPartPhaseSymptom();
							ppps.setComment(plant.getComment() + " - " + ppp.getComment());
							ppps.setPlantPartPhase(ppp);
							ppps.setSymptom(symptom);
							ppps.setProblem(pppp);
							ppps.persist();							
						}
					}				
				}
			}
		}
		
		
//		for (GrowthPhase phase : phases) {
//			for (PlantPart plantPart : plantParts) {
//				PlantPartPhase ppp = new PlantPartPhase();
//				ppp.se
//			}
//			
//		}
		
		// get plant parts for problem (should add parts as well)
		// get symptoms for problem (add symptom with AI)
		// get 
		
	}
	
	




	private List<Symptom> loadAndGetSymptomsForProblem(Problem problem, int pRow) 
	{
		List<Symptom> symptomList = new ArrayList<Symptom>();
		Integer nextProblemRow = this.getNextProblemRowNum(pRow);
		
		Boolean loopOnce = true;
		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();
			List<String> symptomStrs = ssReader.getColumnAsArray(countryColNum + symptomOS, pRow, nextProblemRow);
			
			// loop symptoms for problem and add translations
			for (int i = 0; i < symptomStrs.size(); i++) 
			{
				Symptom symp = this.addSymptomByRowWithTrans(i+pRow, symptomStrs.get(i), countryColNum);
				symptomList.add(symp);	
			}
			
			if (loopOnce)
				break;
		}

		return symptomList;				
	}
	
	
	
	private Symptom addSymptomByRowWithTrans(Integer sympRow, String symptomStr, Integer baseCountryCol)
	{
		// add new base rec
		Symptom symptom = new Symptom();
		symptom.setComment(symptomStr);
		Country country = this.getCountryByCountryCol(baseCountryCol);
		if (country != null)
			symptom.setCountry(country);
		symptom.persist();

		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();
//			if(countryColNum.equals(baseCountryCol))
//					continue;
			
			// add symptom translations
			String symptomTransStr = ssReader.getCellContent(sympRow, countryColNum + symptomOS);

			SymptomTranslation sympTrans = new SymptomTranslation();
			sympTrans.setName(symptomTransStr);
			sympTrans.setSymptom(symptom);

			Languages lang = this.getLanguageByCountryCol(countryColNum);
			if (lang != null)
				sympTrans.setLang(lang);

			sympTrans.persist();
		}
		return symptom;
	}



	private boolean plant_plantPart_phase_exists(Plant plant, GrowthPhase phase, PlantPart pPart) 
	{
		try
		{
			TypedQuery<PlantPartPhase> pppQ = PlantPartPhase.findPlantPartPhasesByPlantAndGrowthPhaseAndPlantPart(plant, phase, pPart);
			if (pppQ.getResultList().size() > 0) 
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;	
		}		
	}

	
	private boolean plant_plantPart_phase_problem_exists(Plant plant, GrowthPhase phase, PlantPart pPart, Problem problem) 
	{
		// TODO check if such link already exists
		return false;		
	}
	
	
	
	private boolean plant_plantPart_phase_symptom_exists(Plant plant, GrowthPhase phase, PlantPart pPart, Symptom symptom) 
	{
		// TODO check if such link already exists
		return false;		
	}


	
	private List<Plant> getPlantsForProblem(Problem problem, int pRow) 
	{
		List<Plant> plants = new ArrayList<Plant>();
		Plant plant;
		String plantStr;
		
		for (int i = this.latinNameColNum; i >= 0; i--) {
			String plantCell = ssReader.getCellContent(pRow, i);
			if (plantCell.equalsIgnoreCase("yes"))
			{
				plantStr = ssReader.getCellContent(this.headerRowNum, i);
				plant = Plant.getSinglePlantByName(plantStr);
				
				if (plant != null)
					plants.add(plant);
			}			
		}
		return plants;
	}



	private boolean problemExists(String problemLatin) 
	{
		try
		{
			TypedQuery<Problem> problemQ = Problem.findProblemsByLatinNameEquals(problemLatin);	
			if (problemQ.getResultList().size() > 0)
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}



	private Problem getProblemByLatin(String problemLatin) {
		Problem problem = null;
		try
		{
			TypedQuery<Problem> problemQ = Problem.findProblemsByLatinNameEquals(problemLatin);	
			if (problemQ.getResultList().size() > 0)
			{
				problem = problemQ.getSingleResult();
			}
		}
		catch (Exception e)
		{}
		return problem;		
	}



	private Problem addProblemByLatin(String problemLatin) {
		Problem p = new Problem();
		p.setLatinName(problemLatin);
		p.setCountry(Country.getSingleCountryByName("Estonia"));
		p.persist();
		return p;
	}
	



	private void loadProblemTranslations(Problem problem, Integer realRowNum) 
	{	
		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();
			String problemTrans = ssReader.getCellContent(realRowNum, countryColNum);
			if ( !problemTrans.isEmpty() )
			{
				ProblemTranslation pTrans = new ProblemTranslation();
				pTrans.setProblem(problem);
				pTrans.setName(problemTrans);
				
				Languages language = this.getLanguageByCountryCol(countryColNum);
				if (language != null)
				{
					pTrans.setLang(language);
				}
				String warning = ssReader.getCellContent(realRowNum, countryColNum + warningOS);
				pTrans.setWarning(warning);
				pTrans.persist();								
			}
		}
	}



	private Integer getNextProblemRowNum(Integer curProblemRow)
	{
		List<String> problemRows = this.ssReader.getColumnAsArray(this.latinNameColNum, curProblemRow+1);
		
		for (int i = 0; i < problemRows.size(); i++) 
		{
			Integer realRowNum = i+curProblemRow+1;
			String pLatinName = problemRows.get(i);
			
			if ( !pLatinName.isEmpty() )
			{
				return realRowNum; 
			}		
		}
		return null;
	}
	
	
	
	private List<GrowthPhase> getPhasesForProblem(Problem problem, int row)
	{
		List<GrowthPhase> phaseList = new ArrayList<GrowthPhase>();
		String phaseCell = ssReader.getCellContent(row, phaseColumn);
		String[] phasePcs = phaseCell.split(",");

		for (String phaseStr : phasePcs) {
			if ( phaseStr.isEmpty() )
				continue;
			try
			{
				String phaseF = "F" + phaseStr.trim();
				GrowthPhase phase = GrowthPhase.findGrowthPhasesByCommentEquals(phaseF).getSingleResult();
				phaseList.add(phase);
			}
			catch(Exception e)
			{}

		}
		return phaseList;
	}
	
	private List<PlantPart> loadAndGetPlantPartsForProblem(int row)
	{
		List<PlantPart> ppList = new ArrayList<PlantPart>();
		
		Integer firstPPColumn = this.getFirstTypeColumn(plantPartOS);
		
		if (firstPPColumn == null) return ppList;
		
		String basePlantPartStr = ssReader.getCellContent(row, firstPPColumn);
		PlantPart existingPP = PlantPart.getSinglePlantPartByName(basePlantPartStr);
		
		if (existingPP != null)
		{
			ppList.add(existingPP);
			return ppList;
		}
		
		// firstone will be root plant
		PlantPart pp = new PlantPart();

		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();
			String plantPartStr = ssReader.getCellContent(row, countryColNum + plantPartOS);	
		
			if (pp.getComment() == null)
			{
				pp.setComment(plantPartStr);
				pp.persist();
				ppList.add(pp);
			}						

			PlantPartTranslation ppTrans = new PlantPartTranslation();
			ppTrans.setPlantPart(pp);
			ppTrans.setName(plantPartStr);

			Languages language = this.getLanguageByCountryCol(countryColNum);
			if ( language != null )
			{				
				ppTrans.setLang(language);
			}
			ppTrans.persist();
		}

		return ppList;
	}
	
	
	private Boolean plantPartExistsByName(String ppName)
	{
		if (ppName.isEmpty())
			return false;
		
		PlantPart existingPP = PlantPart.getSinglePlantPartByName(ppName);
		if (existingPP != null)
		{
			return true;
		}
		else
			return false;
				
	}
	
	
	private Integer getFirstTypeColumn(Integer offSet)
	{
		for (Map.Entry<String, Integer> countryCol : countryCols.entrySet()) 
		{
			Integer countryColNum = countryCol.getValue();		
			return countryColNum + offSet;		
		}
		return null;
	}





	
	private Languages getLanguageByCountryCol(Integer countryCol)
	{
		String languageStr = ssReader.getCellContent(this.countryRowNum + 1, countryCol);
		Languages language = Languages.getSingleLanguageByName(languageStr);
		return language;
	}
	
	
	
	private Country getCountryByCountryCol(Integer countryCol)
	{
		String countryStr = ssReader.getCellContent(this.countryRowNum, countryCol);
		Country country = Country.getSingleCountryByName(countryStr);
		return country;
	}
	
	
	
	
	
	
	
	
	
}
