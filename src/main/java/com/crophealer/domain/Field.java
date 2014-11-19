package com.crophealer.domain;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.crophealer.rest.v1.FieldResource;
import com.crophealer.security.Users;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Field {

    /**
     */
    private String name;

    /**
     */
    private String coordinates;

    /**
     */
    private String priaID;

    /**
     */
    @ManyToOne
    private Users owner;

    /**
     */
    @ManyToOne
    private Company company;

    public static Field createFromResource(FieldResource fr) {
		Field field = new Field();
		field.fillFromResource(fr);
		field.persist();
		return field;
	}
    
	public void updateFromResource(FieldResource fr) {
		fillFromResource(fr);	
		this.persist();
	}

	private void fillFromResource(FieldResource fr) {
		this.setCoordinates(fr.getCoordinates());
		this.setName(fr.getName());
		this.setPriaID(fr.getPriaID());
		
		if (fr.getCompanyID() != null){
			Company company = Company.findCompany(fr.getCompanyID());
			this.setCompany(company);
		}

		if (fr.getOwnerID() != null){
			Users owner = Users.findUsers(fr.getOwnerID());
			this.setOwner(owner);
		}
	}
}
