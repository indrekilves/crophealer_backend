package com.crophealer.data;

import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.mapping.Map;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientProduct;
import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductReseller;
import com.crophealer.domain.Reseller;

public class DataLoader 
{
	protected String fileName;
	protected SpreadSheetReader ssReader;
	

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	
	
	public void loadFromFile()
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
		this.loadPlantWithPartsAndPhases();
	}

	
	
	private void loadPlantWithPartsAndPhases() 
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
