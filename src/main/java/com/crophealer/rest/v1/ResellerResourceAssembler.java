package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Reseller;

public class ResellerResourceAssembler {

	

	public ResellerResource toResource(Reseller r, Languages language) {
		
		ResellerResource rr = new ResellerResource();
		rr.setId(r.getId());
		rr.setName(r.getName());
		rr.setEmail(r.getEmail());
		rr.setPhone(r.getPhone());
		rr.setContactPerson(r.getContactPerson());
		
		return rr;
	}

	
	public ResellerResourceList toResource(List<Reseller> rl, Languages l) {
		if (rl == null) return null;

		List<ResellerResource> rrl = new ArrayList<ResellerResource>();
		for (Reseller r : rl) {
			rrl.add(toResource(r, l));
		}
		
		return new ResellerResourceList(rrl);
	}
}
