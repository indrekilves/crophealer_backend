package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "resellers")
public class ResellerResourceList {

	private List<ResellerResource> resellers;

	public ResellerResourceList() {
	}

	public ResellerResourceList(List<ResellerResource> resellers) {
		this.setResellers(resellers);
	}

}
