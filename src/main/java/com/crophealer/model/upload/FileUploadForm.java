package com.crophealer.model.upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {
	
	private String pppProblemId;
	private String location;	
	private List<MultipartFile> files;
	

	
	public String getPppProblemId() {
		return pppProblemId;
	}

	public void setPppProblemId(String pppProblemId) {
		this.pppProblemId = pppProblemId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}
