package com.crophealer.rest.v1;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;


@RooJavaBean 
@JsonRootName(value = "diagnosedProblem")
public class DiagnosedProblemResource {

	private Long   id; 
	private String comment;
	private String location;
	private String symptopmIDsCSV;
	private String userId; 
    private Date   createdTimestamp;   
    private Date   modifiedTimestamp;   
    private DiagnosedProblemPictureResourceList pictures;
}
