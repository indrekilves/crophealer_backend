package com.crophealer.web;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.crophealer.data.DataCleaner;
import com.crophealer.data.DataLoader;
import com.crophealer.model.upload.ExcelUploadForm;

@RequestMapping("/dataLoader/**")
@Controller
public class DataLoaderController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }
    
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayForm() {
        return "excel_upload_form";
    }
    

    @RequestMapping
    public String index() {
        //return "dataLoader/index";
    	return "excel_upload_form";
    }
    
    @RequestMapping(value = "/truncate", method = RequestMethod.POST)
    public String truncate(@ModelAttribute("truncateForm") ExcelUploadForm truncateForm, Model map) {               
    	DataCleaner dCleaner = new DataCleaner();
		dCleaner.truncateAllTables();
		
    	return "database_truncate_result";
    }
    
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("uploadForm") ExcelUploadForm uploadForm, Model map) {
    	MultipartFile incomingFile = uploadForm.getExcelFile();
    	String comment = uploadForm.getUploadComment();
      
    	//String destinationDir = "d:/PROJECTS/CropHealer/crophealer/src/main/resources/data/temp/";
    	String destinationDir = "/var/excelData/";
    	String tempRunDir = "/data/temp/";
    	
    	String incomingFileName = incomingFile.getOriginalFilename();
    	if(!"".equalsIgnoreCase(incomingFileName)){    	 
    		try {
    			
    			String destFullName = destinationDir + incomingFileName; 			
    			incomingFile.transferTo(new File(destFullName));
    			
    			DataLoader dl = new DataLoader();
    	    	
    			dl.setInputFileName(destFullName);
    			//dl.setInputFileName(tempRunDir + incomingFileName);
    			
    	    	dl.runDataLoad();
    	    	map.addAttribute("result", "Data successfully loaded");
    	    	return "excel_upload_result";
    			
    		} catch (IllegalStateException e) {
    			e.printStackTrace();
    			map.addAttribute("result", e.getMessage());
    		} catch (IOException e) {
    			e.printStackTrace();
    			map.addAttribute("result", e.getMessage());
    		} 
    	}
    	return "excel_upload_result";
    }

    
    
    
}
