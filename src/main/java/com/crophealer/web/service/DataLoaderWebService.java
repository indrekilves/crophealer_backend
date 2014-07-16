package com.crophealer.web.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.crophealer.data.DataLoader;

public class DataLoaderWebService {
	
	
	public String uploadDataFileAndStartDataLoad(MultipartFile incomingFile, String comment) {
		String incomingFileName = incomingFile.getOriginalFilename();
    	if(!"".equalsIgnoreCase(incomingFileName)){    	 
    		try {
    	    	String rootPath = System.getProperty("catalina.home");
    	    	System.out.println("rootpath is " + rootPath);
    	        File dir = new File(rootPath + File.separator + "tmpFiles");
    	        if (!dir.exists())
    	            dir.mkdirs();
    			
    			String destFullName = dir.getAbsolutePath() + "/" + incomingFileName;
    	    	System.out.println("destFullName is " + destFullName);
    			File f = new File(destFullName);
    			
    			incomingFile.transferTo(new File(destFullName));
    			
    			String absPath = f.getAbsolutePath();
    			System.out.println("absPath is " + absPath);
    			DataLoader dl = new DataLoader();
    			
    			dl.setInputFileName(absPath);   			
    	    	dl.runDataLoad();  	    	
    			
    		} catch (IllegalStateException e) {
    			e.printStackTrace();
    			return  e.getMessage();
    		} catch (IOException e) {
    			e.printStackTrace();
    			return e.getMessage();
    		} 
    	}
    	
    	return "Data successfully loaded";
	}

}
