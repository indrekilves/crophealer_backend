package com.crophealer.data;

import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Reseller;

public class ResellerLoader 
{
	private SpreadSheetReader ssReader;
	
	public ResellerLoader(SpreadSheetReader ssReader)
	{
		this.ssReader = ssReader;
		this.ssReader.setActiveSheetNum(4);
	}
	
	
	public void loadResellers()
	{
        List<String> resellersNames = this.ssReader.getColumnAsArray(0, 2);
		
		for (int i = 0; i < resellersNames.size(); i++) 
		{
			List<String> resellerDetails = this.ssReader.getRowAsArray(3);
			Producer p = new Producer();
			p.setName(resellersNames.get(i));
			p.setEmail(resellerDetails.get(1));
			p.setContactPerson(resellerDetails.get(2));
			p.setPhone(resellerDetails.get(3));
			
			Country c = CountryLoader.loadCountryByName(resellerDetails.get(4));
			p.setCountry(c);
			p.persist();
		}
	}
	
	
	
	public static Reseller loadResellerByName(String resellerName)
	{
		TypedQuery<Reseller> resellerQ = Reseller.findResellersByNameEquals(resellerName);
		
		if (resellerQ.getResultList().size() > 0)
		{
			return resellerQ.getSingleResult();
		}
		else
		{
			Reseller r = new Reseller();
			r.setName(resellerName);
			r.persist();
			return r;
		}
	}
	

}
