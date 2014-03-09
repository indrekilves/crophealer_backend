package com.crophealer.data;

import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Reseller;

public class ResellerLoader extends GenericLoader 
{
	protected Integer activeSheetNum = 4;
	
	public ResellerLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
	}
	
	
	public void loadResellers()
	{
        List<String> resellersNames = this.ssReader.getColumnAsArray(0, 3);
		
		for (int i = 0; i < resellersNames.size(); i++) 
		{
			if ( !resellersNames.get(i).isEmpty() )
			{
				List<String> resellerDetails = this.ssReader.getRowAsArray(3);
				Reseller r = new Reseller();
				r.setName(resellersNames.get(i));
				r.setEmail(resellerDetails.get(1));
				r.setContactPerson(resellerDetails.get(2));
				r.setPhone(resellerDetails.get(3));
				
				Country c = CountryLoader.loadCountryByName(resellerDetails.get(4));
				r.setCountry(c);
				r.persist();
			}
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
