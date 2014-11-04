package com.crophealer.rest.v1;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName(value = "messages")
public class MessageResourceList {

	private List<MessageResource> messages;

	public MessageResourceList() {
	}

	public MessageResourceList(List<MessageResource> messages) {
		this.setMessages(messages);
	}

}
