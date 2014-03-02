package com.crophealer.data;

import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;

public class CountryLoader 
{
	
	private SpreadSheetReader ssReader;
	
	public CountryLoader(SpreadSheetReader ssReader)
	{
		this.ssReader = ssReader;
		this.ssReader.setActiveSheetNum(0);
	}
	
	
	
	public void loadCountries()
	{
		List<String> row = ssReader.getRowAsArray(ssReader.getCountryRow());
		for (String s : row)
		{
			if (!s.isEmpty())
			{
				Country c = new Country();
				c.setName(s);
				c.persist();
			}
		}
	}
	
	public static Country loadCountryByName(String name)
	{
		TypedQuery<Country> cq = Country.findCountrysByNameEquals(name);
		if (cq.getResultList().size() > 0)
		{
			return cq.getSingleResult();
		}
		else
		{
			Country c = new Country();
			c.setName(name);
			c.persist();
			return c;
		}
	}

}
