package com.crophealer.web;
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
import com.crophealer.model.upload.ExcelUploadForm;
import com.crophealer.web.service.DataLoaderWebService;

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

    	DataLoaderWebService loaderService = new DataLoaderWebService();
   		String result = loaderService.uploadDataFileAndStartDataLoad(incomingFile, comment);
    	
    	map.addAttribute("result", result);  	 
    	return "excel_upload_result";
    }

    
    
    
}
