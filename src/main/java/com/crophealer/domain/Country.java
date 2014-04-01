package com.crophealer.domain;

import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCountrysByNameEquals" })
public class Country {

	/**
	 * java doc
	 */
	private String name;

	public static Country getSingleCountryByName(String countryStr) {
		TypedQuery<Country> countryQ = Country
				.findCountrysByNameEquals(countryStr);

		if (countryQ.getResultList().size() > 0) {
			return countryQ.getSingleResult();
		} else {
			return null;
		}
	}
}
