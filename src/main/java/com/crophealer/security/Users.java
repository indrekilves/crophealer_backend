package com.crophealer.security;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.rest.v1.RequestError;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.utils.EmailValidator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsersesByUsernameEquals" })
public class Users {

    /**
     */
    private String username;

    /**
     */
    private String password;

    /**
     */
    private Boolean enabled;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usr")
    private final Set<DiagnosedProblem> diagnosedProblems = new HashSet<DiagnosedProblem>();

    /**
     */
    private String email;
    
    
    



	public static RequestError validateUserResource(UserResource ur) {
		if (ur.getUsername().isEmpty())           return RequestError.E0001;
		if (doesUsernameExists(ur.getUsername())) return RequestError.E0002;
		if (ur.getPassword().isEmpty())           return RequestError.E0003;
		if (isPasswordToWeak(ur.getPassword()))   return RequestError.E0004;
		if (ur.getEmail().isEmpty())              return RequestError.E0005;
		if (isBadEmailFormat(ur.getEmail()))      return RequestError.E0006;
		
		return null;
	}
    

	private static boolean doesUsernameExists(String un) {
		TypedQuery<Users> result = Users.findUsersesByUsernameEquals(un);
		List<Users> userList = result.getResultList();
		return userList.isEmpty() ? false : true;
	}

	
	private static boolean isPasswordToWeak(String pw) {
		// TODO: isPasswordToWeak isn't implemented
		return false;
	}
	
	
	private static boolean isBadEmailFormat(String email2) {
		// TODO: isEmailFormatFaulty isn't implemented
		EmailValidator eValidator = new EmailValidator();
		return !eValidator.validate(email2);
	}
	

	public static Users createFromResource(UserResource ur) {
		if (ur == null) return null;
		
		Users u = new Users();
		u.username = ur.getUsername();
		u.password = ur.getPassword();
		u.email    = ur.getEmail();
		u.enabled  = false;
		u.persist();
		
		// TODO: Send verification email.
		return u;	
		
	}


}
