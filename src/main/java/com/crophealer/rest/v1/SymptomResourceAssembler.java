package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomTranslation;

public class SymptomResourceAssembler {

	

	public SymptomResource toResource(Symptom s, Languages language) {
		
		SymptomResource sr = new SymptomResource();
		sr.setId(s.getId());
		if (language != null){
			TypedQuery<SymptomTranslation> tq = SymptomTranslation.findSymptomTranslationsBySymptomAndLang(s, language);
			if (tq != null){
				SymptomTranslation st = tq.getSingleResult();
				if (st != null){
					sr.setName(st.getName());
					sr.setDescription(st.getDescription());
					sr.setWarning(st.getWarning());
				}
			}	
		}

		SymptomPictureResourceAssembler spAsm = new SymptomPictureResourceAssembler();
		sr.setPictures(spAsm.toResource(s.getPictures()));

		return sr;
	}

	
	public SymptomResourceList toResource(List<Symptom> sl, Languages l) {
		if (sl == null) return null; 

		List<SymptomResource> srl = new ArrayList<SymptomResource>();
		for (Symptom s : sl) {
			srl.add(toResource(s, l));
		}
		
		return new SymptomResourceList(srl);
	}
}
