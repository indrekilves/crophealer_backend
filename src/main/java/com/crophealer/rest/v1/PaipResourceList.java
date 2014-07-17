package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "paips")
public class PaipResourceList {

	private List<PaipResource> paips;

	public PaipResourceList() {
	}

	public PaipResourceList(List<PaipResource> paips) {
		this.setPaips(paips);
	}

}
