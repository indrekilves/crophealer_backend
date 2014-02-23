package com.crophealer.rest;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Symptom;
import com.crophealer.domain.SymptomTranslation;

public class SymptomResourceAssembler {

	

	public SymptomResource toResource(Symptom s, Languages language) {
		
		SymptomResource sr = new SymptomResource();
		sr.setId(s.getId());
		if (language != null){
			SymptomTranslation st = SymptomTranslation.findSymptomTranslationsByLang(language).getSingleResult();
			if (st != null){
				sr.setName(st.getName());
				sr.setDescription(st.getDescription());
				sr.setWarning(st.getWarning());
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
