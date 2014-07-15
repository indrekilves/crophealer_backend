package com.crophealer.model.upload;

import org.springframework.web.multipart.MultipartFile;


public class ExcelUploadForm {
	
	private MultipartFile excelFile;
	private String uploadComment;
	
	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	public String getUploadComment() {
		return uploadComment;
	}

	public void setUploadComment(String uploadComment) {
		this.uploadComment = uploadComment;
	}

	
}
