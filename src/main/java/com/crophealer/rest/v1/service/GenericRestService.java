package com.crophealer.rest.v1.service;

import javax.persistence.TypedQuery;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.crophealer.domain.Languages;
import com.crophealer.security.Users;

public class GenericRestService {
	
	public Languages getEstonian() {
		TypedQuery<Languages> languages = Languages.findLanguagesesByNameEquals("Estonian");
		return languages != null ? languages.getSingleResult() : null;
	}
	
	
	public Users getUserFromAuth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
				
		Users user = null;
	
		TypedQuery<Users> result = Users.findUsersesByUsernameEquals(name);
		if (result != null){
			user = result.getSingleResult();
		}
		return user;
	}

}
