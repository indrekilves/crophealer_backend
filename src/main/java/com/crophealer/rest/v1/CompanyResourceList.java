package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "companies")
public class CompanyResourceList {

	private List<CompanyResource> companies;

	public CompanyResourceList() {
	}

	public CompanyResourceList(List<CompanyResource> companies) {
		this.setCompanies(companies);
	}

}
