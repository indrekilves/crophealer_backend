package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Company;
import com.crophealer.domain.Field;
import com.crophealer.security.Users;

public class FieldResourceAssembler {

	

	public FieldResource toResource(Field f) {
		
		FieldResource fr = new FieldResource();
		fr.setId(f.getId());
		fr.setName(f.getName());
		fr.setCoordinates(f.getCoordinates());
		fr.setPriaID(f.getPriaID());
		
		Users owner = f.getOwner();
		if (owner != null){
			fr.setOwnerID(owner.getId());
		}
		
		Company company = f.getCompany();
		if (company != null) {
			fr.setCompanyID(company.getId());
		}
			
		return fr;
	}
	

	
	public FieldResourceList toResource(List<Field> fl) {
		if (fl == null) return null; 

		List<FieldResource> frl = new ArrayList<FieldResource>();
		for (Field f : fl) {
			frl.add(toResource(f));
		}
		
		return new FieldResourceList(frl);
	}
}
