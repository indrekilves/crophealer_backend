package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Producer;

public class ProducerResourceAssembler {

	

	public ProducerResource toResource(Producer p, Languages language) {
		
		ProducerResource pr = new ProducerResource();
		pr.setId(p.getId());
		pr.setName(p.getName());
		pr.setEmail(p.getEmail());
		pr.setPhone(p.getPhone());
		pr.setContactPerson(p.getContactPerson());
		
		return pr;
	}

	
	public ProducerResourceList toResource(List<Producer> pl, Languages l) {
		if (pl == null) return null;

		List<ProducerResource> prl = new ArrayList<ProducerResource>();
		for (Producer p : pl) {
			prl.add(toResource(p, l));
		}
		
		return new ProducerResourceList(prl);
	}
}
