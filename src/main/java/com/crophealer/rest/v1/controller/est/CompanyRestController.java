package com.crophealer.rest.v1.controller.est;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crophealer.domain.Company;
import com.crophealer.rest.v1.CompanyResource;
import com.crophealer.rest.v1.CompanyResourceAssembler;
import com.crophealer.rest.v1.CompanyResourceList;
import com.crophealer.utils.ResponseEntityUtil;


@Controller
@RequestMapping("/rest/v1/est/companies")

public class CompanyRestController extends GenericController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CompanyResourceList> getCompanies()
	{   	
		List<Company> companies = Company.findAllCompanys();		
		CompanyResourceAssembler asm = new CompanyResourceAssembler();
		CompanyResourceList crl = asm.toResource(companies);

		return new ResponseEntity<>(crl, HttpStatus.OK);		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<String> createCompany(@RequestBody CompanyResource cr)
	{   	
		if (cr == null) throw new BadRequestException("CompanyResource is missng");
		Company company = Company.createFromResource(cr);
				
		return ResponseEntityUtil.CreatedFromCurrentResourceId(company.getId());	
	}

	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<CompanyResource> getCompany(@PathVariable("id") Long id)
	{   	
		if (id == null) throw new BadRequestException("ID is missng");
		
		Company company = Company.findCompany(id);
		if (company == null) throw new ResourceNotFoundException("Company not found for ID: " + id);		
		
		CompanyResourceAssembler asm = new CompanyResourceAssembler();
		CompanyResource cr = asm.toResource(company);

		return new ResponseEntity<>(cr, HttpStatus.OK);		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/{id}", consumes="application/json")
	public ResponseEntity<String> updateField(@PathVariable("id") Long id, @RequestBody CompanyResource cr)
	{   	
		if (id == null) throw new BadRequestException("ID is missng");
		if (cr == null) throw new BadRequestException("CompanyResource is missng");
		
		Company company = Company.findCompany(id);
		if (company == null) throw new ResourceNotFoundException("Company not found for ID: " + id);		

		company.updateFromResource(cr);		
		
		return ResponseEntityUtil.AcceptedWithCurrentUri();		
	}
	
}
