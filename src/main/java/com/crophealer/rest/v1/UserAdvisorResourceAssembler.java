package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.UserAdvisor;
import com.crophealer.security.Users;

public class UserAdvisorResourceAssembler {

	

	public UserAdvisorResource toResource(UserAdvisor ua) {
		
		UserAdvisorResource uar = new UserAdvisorResource();
		uar.setId(ua.getId());
		Users client = ua.getClient();
		if (client != null){
			uar.setClientId(client.getId());
		}
		Users advisor = ua.getClient();
		if (advisor != null){
			uar.setAdvisorId(advisor.getId());
		}
		uar.setStatus(ua.getStatus());
		return uar;
	}

	
	public UserAdvisorResourceList toResource(List<UserAdvisor> ual) {
		if (ual == null) return null; 

		List<UserAdvisorResource> url = new ArrayList<UserAdvisorResource>();
		for (UserAdvisor ua : ual) {
			url.add(toResource(ua));
		}
		
		return new UserAdvisorResourceList(url);
	}
}
