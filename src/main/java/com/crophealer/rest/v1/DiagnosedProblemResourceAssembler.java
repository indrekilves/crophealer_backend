package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Languages;
import com.crophealer.security.Users;

public class DiagnosedProblemResourceAssembler {

	

	public DiagnosedProblemResource toResource(DiagnosedProblem dp, Languages language) {
		
		DiagnosedProblemResource dpr = new DiagnosedProblemResource();
		dpr.setId(dp.getId());
		dpr.setComment(dp.getComment());
		dpr.setLocation(dp.getLocation());
		dpr.setSymptopmIDsCSV(dp.getSymptomIDsCSV());
		dpr.setCreatedTimestamp(dp.getCreatedTimestamp());
		dpr.setModifiedTimestamp(dp.getModifiedTimestamp());
		Users u = dp.getUsr();
		if (u != null){
			dpr.setUserId(u.getId().toString());
		}
			
		DiagnosedProblemPictureResourceAssembler dppAsm = new DiagnosedProblemPictureResourceAssembler();
		dpr.setPictures(dppAsm.toResource(dp.getPictures()));

		return dpr;
	}

	
	public DiagnosedProblemResourceList toResource(List<DiagnosedProblem> dpl, Languages l) {
		if (dpl == null) return null;

		List<DiagnosedProblemResource> dprl = new ArrayList<DiagnosedProblemResource>();
		for (DiagnosedProblem dp : dpl) {
			dprl.add(toResource(dp, l));
		}
		
		return new DiagnosedProblemResourceList(dprl);
	}


	public DiagnosedProblemResourceList toResource(
			Set<DiagnosedProblem> diagnosedProblems, Languages language) {
		if (diagnosedProblems == null) return null;

		List<DiagnosedProblemResource> dprl = new ArrayList<DiagnosedProblemResource>();
		for (DiagnosedProblem dp : diagnosedProblems) {
			dprl.add(toResource(dp, language));
		}
		
		return new DiagnosedProblemResourceList(dprl);
	}
}
