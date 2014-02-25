package com.crophealer.rest.v1.service;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;

public class GenericRestService {
	
	public Languages getEstonian() {
		TypedQuery<Languages> languages = Languages.findLanguagesesByNameEquals("Estonian");
		return languages != null ? languages.getSingleResult() : null;
	}

}
