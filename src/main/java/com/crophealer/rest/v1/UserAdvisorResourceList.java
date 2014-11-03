package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "userAdvisors")
public class UserAdvisorResourceList {

	private List<UserAdvisorResource> userAdvisors;

	public UserAdvisorResourceList() {
	}

	public UserAdvisorResourceList(List<UserAdvisorResource> userAdvisors) {
	}

}
