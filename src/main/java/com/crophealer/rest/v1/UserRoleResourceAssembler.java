package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.security.Authorities;

public class UserRoleResourceAssembler {

	

	public UserRoleResource toResource(Authorities a) {
		
		UserRoleResource urr = new UserRoleResource();
		urr.setId(a.getId());
		urr.setRolename(a.getAuthority());
		return urr;
	}

	
	public UserRoleResourceList toResource(List<Authorities> al) {
		if (al == null) return null; 

		List<UserRoleResource> urrl = new ArrayList<UserRoleResource>();
		for (Authorities a : al) {
			urrl.add(toResource(a));
		}
		
		return new UserRoleResourceList(urrl);
	}
}
