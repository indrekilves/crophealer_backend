package com.crophealer.rest.v1;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean 
@JsonRootName(value = "message")
public class MessageResource {

	private Long   id; 
	private String status;  
	private String subject;
	private String comment;
    private Long   senderID;  
    private Long   receiverID;
    private Long   diagnosedProblemID;
    private Long   parentID;
    private String createdTimestamp;
    private String modifiedTimestamp;
        
  
}
