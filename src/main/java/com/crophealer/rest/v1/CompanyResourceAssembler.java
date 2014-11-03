package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Company;

public class CompanyResourceAssembler {

	

	public CompanyResource toResource(Company c) {
		
		CompanyResource cr = new CompanyResource();
		cr.setId(c.getId());
		cr.setName(c.getName());
		cr.setAddress(c.getAddress());
		cr.setPhone(c.getPhone());
		cr.setEmail(c.getEmail());
		cr.setContactPerson(c.getContactPerson());
		cr.setFieldSize(c.getFieldSize());
		return cr;
	}

	
	public CompanyResourceList toResource(List<Company> cl) {
		if (cl == null) return null; 

		List<CompanyResource> crl = new ArrayList<CompanyResource>();
		for (Company c : cl) {
			crl.add(toResource(c));
		}
		
		return new CompanyResourceList(crl);
	}
}
