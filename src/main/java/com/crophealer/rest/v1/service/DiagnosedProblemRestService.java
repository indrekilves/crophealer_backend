package com.crophealer.rest.v1.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.DiagnosedProblemPicture;
import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhaseProblem;
import com.crophealer.model.upload.FileUploadForm;
import com.crophealer.rest.v1.DiagnosedProblemResource;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.security.Users;

@Service
public class DiagnosedProblemRestService  extends GenericRestService {

	
	private FileUploadForm uploadForm;
	private Users user;
	private String pictureDirectory;
	private DiagnosedProblem diagnosedProblem; 
	
	

	public ResponseEntity<DiagnosedProblemResource> getDiagnosedProblemByLanguage(Long id, Languages language) {
		System.out.println("getDiagnosedProblemByLanguage - try to get for id:" + id + " lang:" + language);
		
		ResponseEntity<DiagnosedProblemResource> response; 

		if (id == null || language == null) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}

		DiagnosedProblem dp = DiagnosedProblem.findDiagnosedProblem(id);
		if (dp == null){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}
		
		
		DiagnosedProblemResourceAssembler dprAsm = new DiagnosedProblemResourceAssembler();
		DiagnosedProblemResource dpr = dprAsm.toResource(dp, language);
		
		response = new ResponseEntity<>(dpr, HttpStatus.OK);
		return response;	
	}
	
	
	
	public ResponseEntity<Void> saveDiagnosedProblemByLanguage(FileUploadForm uf, Languages language) {
		System.out.println("saveDiagnosedProblemByLanguage - start");
		uploadForm = uf;
		
		ResponseEntity<Void> response;		
		if (uploadForm == null){
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;	
		}		

		PlantPartPhaseProblem pppProblem = getPlantPartPhaseProblem(); 
		if (pppProblem == null){
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;	
		}		
		
		// Get user
		user = getUserFromAuth();
		if (user == null){
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}
						
	
	    DiagnosedProblem dp = new DiagnosedProblem(); 
	    dp.setUsr(user);
		dp.setPlantPartPhaseProblem(pppProblem);
		dp.setLocation(uploadForm.getLocation());
		dp.persist();
		
	    diagnosedProblem = dp;		
		storePictures();	    
    	
		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(dp.getId().toString()).build().toUri();
		headers.setLocation(location);
		response = new ResponseEntity<>(headers, HttpStatus.CREATED);
		return response;
	}


	private PlantPartPhaseProblem getPlantPartPhaseProblem() {
		String pppProblemIdStr = uploadForm.getPppProblemId();
		if (pppProblemIdStr == null || pppProblemIdStr.isEmpty()){
			return null;
		}
			
		Long pppProblemId = Long.parseLong(pppProblemIdStr);
		if (pppProblemId == null) {
			return null;
		}
			
		return PlantPartPhaseProblem.findPlantPartPhaseProblem(pppProblemId);
	}
	
	
	
	private void storePictures() {		
		pictureDirectory = FileManagementRestService.getUserPictureDirectoryByUsername(user.getId().toString());
		if (pictureDirectory == null) {
			System.out.println("ERROR: Failed to get pictureDirectory.");
			return;
		}
		
		List<MultipartFile> files = uploadForm.getFiles();       
		
		if(null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {	
				storePicture(multipartFile); 
			}
		}				
	}


	private void storePicture(MultipartFile multipartFile) {
		String origName = multipartFile.getOriginalFilename();
		if("".equalsIgnoreCase(origName)){
			return;
		}	

		DiagnosedProblemPicture dpp = new DiagnosedProblemPicture();
		dpp.setDiagnosedProblem(diagnosedProblem);
		dpp.persist();	
		dpp.flush();
		
		String ext = FilenameUtils.getExtension(origName);
		String filePath = pictureDirectory + dpp.getId() + "." + ext;
	
    	try {				
			multipartFile.transferTo(new File(filePath));
		
			dpp.setName(origName);
			dpp.setPictureUrl(filePath);
			dpp.merge();
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


}

	



