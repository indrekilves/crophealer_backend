package com.crophealer.data;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.GrowthPhaseTranslation;
import com.crophealer.domain.Languages;

public class GrowthPhaseLoader extends GenericLoader
{
	protected Integer activeSheetNum = 2;
	
	public GrowthPhaseLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
	}
	
	
	public void loadPhaseCodes() 
	{
		List<String> codes = ssReader.getColumnAsArray(0);
		
		for (int i = 2; i < codes.size(); i++) 
		{
			GrowthPhase gp = new GrowthPhase();
			gp.setComment(codes.get(i));
			gp.persist();
		}
		
	}
	
	public void loadPhaseTranslations()
	{
		List<String> languageRow = ssReader.getRowAsArray(0);

		
		for (int i = 0; i < languageRow.size(); i++) 
		{
			if ( !languageRow.get(i).isEmpty() )
			{
				try
				{
					Languages lang = Languages.findLanguagesesByNameEquals(languageRow.get(i)).getSingleResult();
					List<String> names = ssReader.getColumnAsArray(i, 2);
					List<String> descs = ssReader.getColumnAsArray(i + 1, 2);
					this.loadTranslationsForLanguage(lang, names, descs);
				}
				catch(Exception e)
				{
					//
				}								
			}
		}
		
	}


	private void loadTranslationsForLanguage(Languages lang, List<String> names, List<String> descs) 
	{
		List<GrowthPhase> phases = GrowthPhase.findAllGrowthPhases();
		for (int i = 0; i < phases.size(); i++) 
		{
			GrowthPhaseTranslation gpt = new GrowthPhaseTranslation();
			gpt.setLang(lang);
			gpt.setGrowthPhase(phases.get(i));
			gpt.setName(names.get(i));
			gpt.setDescription(descs.get(i));
			gpt.persist();			
		}
		
	}

	

}
