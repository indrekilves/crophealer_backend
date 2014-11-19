package com.crophealer.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.crophealer.rest.v1.CompanyResource;
import com.crophealer.security.Users;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    /**
     */
    private String name;

    /**
     */
    private String address;

    /**
     */
    private String phone;

    /**
     */
    private String email;

    /**
     */
    private String contactPerson;

    /**
     */
    private String fieldSize;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private final Set<Users> users = new HashSet<Users>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private final Set<Field> fields = new HashSet<Field>();

	public static Company createFromResource(CompanyResource cr) {
		if (cr == null) return null;
		
		Company company = new Company();
		company.fillFromResource(cr);
		company.persist();
		
		return company;
	}

	
	public void updateFromResource(CompanyResource cr){
		if (cr == null) return;
		
		this.fillFromResource(cr);
		this.persist();
	}
	
	
	private void fillFromResource(CompanyResource cr) {
		if (cr == null) return;
		
		if (cr.getName()          != null) this.setName(cr.getName());		
		if (cr.getAddress()       != null) this.setAddress(cr.getAddress());
		if (cr.getContactPerson() != null) this.setContactPerson(cr.getContactPerson());
		if (cr.getEmail()         != null) this.setEmail(cr.getEmail());
		if (cr.getPhone()         != null) this.setPhone(cr.getPhone());
		if (cr.getFieldSize()     != null) this.setFieldSize(cr.getFieldSize());
	}
}
