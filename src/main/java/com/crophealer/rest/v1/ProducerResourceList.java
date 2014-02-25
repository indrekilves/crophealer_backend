package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "producers")
public class ProducerResourceList {

	private List<ProducerResource> producers;

	public ProducerResourceList() {
	}

	public ProducerResourceList(List<ProducerResource> producers) {
		this.setProducers(producers);
	}

}
