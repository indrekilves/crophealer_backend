package com.crophealer.rest.v1.service;


import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crophealer.domain.Company;
import com.crophealer.domain.Field;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Message;
import com.crophealer.domain.UserAdvisor;
import com.crophealer.rest.v1.CompanyResourceAssembler;
import com.crophealer.rest.v1.CompanyResourceList;
import com.crophealer.rest.v1.DiagnosedProblemResourceAssembler;
import com.crophealer.rest.v1.DiagnosedProblemResourceList;
import com.crophealer.rest.v1.FieldResourceAssembler;
import com.crophealer.rest.v1.FieldResourceList;
import com.crophealer.rest.v1.MessageResourceAssembler;
import com.crophealer.rest.v1.MessageResourceList;
import com.crophealer.rest.v1.RequestError;
import com.crophealer.rest.v1.UserAdvisorResourceAssembler;
import com.crophealer.rest.v1.UserAdvisorResourceList;
import com.crophealer.rest.v1.UserResource;
import com.crophealer.rest.v1.UserResourceAssembler;
import com.crophealer.rest.v1.UserResourceList;
import com.crophealer.rest.v1.UserRoleResourceAssembler;
import com.crophealer.rest.v1.UserRoleResourceList;
import com.crophealer.rest.v1.controller.est.BadRequestException;
import com.crophealer.rest.v1.controller.est.ConflictException;
import com.crophealer.rest.v1.controller.est.ResourceNotFoundException;
import com.crophealer.security.Assignments;
import com.crophealer.security.Authorities;
import com.crophealer.security.Users;
import com.crophealer.utils.ResponseEntityUtil;

@Service
public class UserRestService extends GenericRestService {

	
	


	public ResponseEntity<UserResource> getUserByName(String userName) {
		System.out.println("getUserByName - try to get for name: " + userName);
		
		if (userName == null) throw new BadRequestException("Username is missing");

		Users user = null;
		TypedQuery<Users> tq = Users.findUsersesByUsernameEquals(userName);
		if (tq != null && tq.getResultList().size() > 0){
			user = tq.getSingleResult();
		}
		
		if (user == null) throw new ResourceNotFoundException("User not found for username: " + userName);
		
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResource ur = asm.toResource(user);

		return new ResponseEntity<>(ur, HttpStatus.OK);
	}


	
	public ResponseEntity<UserResource> getUser(Long id) {
		System.out.println("getUser - try to get for id:" + id);
		if (id == null) throw new BadRequestException("ID is missng");
		
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found for ID: " + id);		
		
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResource ur = asm.toResource(user);
		// ur.setPassword(user.getPassword());// ??? is really needed ???

		return new ResponseEntity<>(ur, HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<Boolean> isLoginAllowed(String userName) {
		System.out.println("getUser - try to get for id:" + userName);
		
		if (userName == null) throw new BadRequestException("Username is missing");
		
		Users user = null;
		TypedQuery<Users> tq = Users.findUsersesByUsernameEquals(userName);
		if (tq != null && tq.getResultList().size() > 0){
			user = tq.getSingleResult();
		}
		
		if (user == null) throw new ResourceNotFoundException("User not found for username: " + userName);
				
		return new ResponseEntity<>(user.getEnabled(), HttpStatus.OK);
	}


	public ResponseEntity<String> createUser(UserResource ur, String role) {
		if (ur == null) throw new BadRequestException("User resource is missing");
		
		RequestError re = Users.validateUserResource(ur);
		if (re != null) throw new ConflictException(re.getValue());
				
		Users user = Users.createFromResource(ur);
		Authorities authority = getRole(role);				
		Assignments.assignAuthorityToUser(authority, user);

		return ResponseEntityUtil.CreatedFromCurrentResourceId(user.getId());

	}
	

	public ResponseEntity<String> updateUser(Long id, UserResource ur) {
		if (id == null) throw new BadRequestException("ID is missng");
		if (ur == null) throw new BadRequestException("User resource is missing");

		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found for ID: " + id);		
		
		RequestError re = Users.validateUserResource(ur);
		if (re != null) throw new ConflictException(re.getValue());
		
		user.updateFromResource(ur);
		
		return ResponseEntityUtil.AcceptedWithCurrentUri();
	}



	
	
	private Authorities getRole(String role) {
		TypedQuery<Authorities> tq = Authorities.findAuthoritiesesByAuthorityEquals(role);
		if (tq != null && tq.getResultList().size() > 0){
	 		return tq.getSingleResult();
		}
		return createRole(role);
	}


	private Authorities createRole(String authority) {
		Authorities role = new Authorities();
		role.setAuthority(authority);
		role.persist();
		return role;
	}

	
	
	// diags 
	

	public ResponseEntity<DiagnosedProblemResourceList> getDiagnosedProblemsForUserByLanguage(Long id, Languages language) {
		System.out.println("getDiagnosedProblemsForUserByLanguage - try to get for id:" + id + " lang:" + language);

		if (id == null) throw new BadRequestException("ID is missng");
		if (language == null) throw new BadRequestException("Language is missng");
		
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found for ID: " + id);		

		
		
		DiagnosedProblemResourceAssembler dprAsm = new DiagnosedProblemResourceAssembler();
		DiagnosedProblemResourceList dprl = dprAsm.toResource(user.getDiagnosedProblems(), language);
		
		return new ResponseEntity<>(dprl, HttpStatus.OK);
	}

	// messages
	


	public ResponseEntity<MessageResourceList> getAllMessagesForUser(Long id, String type, String status) {
		return this.getMessagesForUser(id, type, status, 0, Integer.MAX_VALUE);
	}


	public ResponseEntity<MessageResourceList> getMessagesForUser(Long id, String type, String status, Integer offset, Integer limit) {
		System.out.println("getMessagesByUserID - try to get for id:" + id);

		if (id == null) throw new BadRequestException("ID is missing");
		if (type == null) throw new BadRequestException("Type is missing");
		
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found for ID: " + id);		
		
		List<Message> messages = findMessages(type, status, user, offset, limit);
			
		MessageResourceAssembler asm = new MessageResourceAssembler();
		MessageResourceList mrl = asm.toResource(messages);
		
		return new ResponseEntity<>(mrl, HttpStatus.OK);
	}
	
	private List<Message> findMessages(String type, String status, Users user, Integer offset, Integer limit) {
		List<Message> messages = new ArrayList<Message>();
		Set<Message> msgSet = new HashSet<Message>();
		switch (type.toUpperCase()) {
		case "ALL":
			Set<Message> msgSentSet = user.getSentMessages();
			Set<Message> msgReceivedSet = user.getReceivedMessages();
			if (msgSentSet != null && !msgSentSet.isEmpty()){
				msgSet.addAll(msgSentSet);
			}
			if (msgReceivedSet != null && !msgReceivedSet.isEmpty()){
				msgSet.addAll(msgReceivedSet);
			}
			break;
			
		case "SENT":
			msgSet = user.getSentMessages();			
			break;
			
		default:
            // Received
			msgSet = user.getReceivedMessages();
			
			break;
		}
		if (msgSet != null && !msgSet.isEmpty()){
			messages = new ArrayList<Message>(msgSet);
		}
		if (status != null && ! status.isEmpty()){
			messages = filterMessagesByStatus(messages, status);
		}
	
		return messages;
	}
/*

	private List<Message> getSentMessages(Users user, Integer offset, Integer limit) {
		int firstResult = offset == null ? 0 : offset;
		int maxResults  = limit  == null ? Integer.MAX_VALUE : limit;
		TypedQuery<Message> tq = Message.findMessagesBySender(user).setFirstResult(firstResult).setMaxResults(maxResults);
		if (tq != null && tq.getResultList().size() > 0){
			return tq.getResultList();			
		}
		return null;
	}


	private List<Message> getReceivedMessages(Users user, Integer offset, Integer limit) {
		int firstResult = offset == null ? 0 : offset;
		int maxResults  = limit  == null ? Integer.MAX_VALUE : limit;
		TypedQuery<Message> tq = Message.findMessagesByReceiver(user).setFirstResult(firstResult).setMaxResults(maxResults);
		if (tq != null && tq.getResultList().size() > 0){
			return tq.getResultList();			
		}
		return null;
	}
*/

	private List<Message> filterMessagesByStatus(List<Message> origMessages, String status) {
		List<Message> filteredMessages = new ArrayList<Message>();
		for (Message message : origMessages) {
			if (message != null && message.getStatus().equalsIgnoreCase(status)){
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	// roles / advisors
	
	public ResponseEntity<UserResourceList> getUsersByRole(String role) {
		System.out.println("getUsersByRole - try to get for role:" + role);

		if (role == null) throw new BadRequestException("Role is missng");
		
		Authorities auth = null;
		TypedQuery<Authorities> authTQ = Authorities.findAuthoritiesesByAuthorityEquals(role);
		if (authTQ != null && authTQ.getResultList().size() > 0){
			auth = authTQ.getSingleResult();
		}
		if (auth == null) throw new ResourceNotFoundException("Authority not found for role: " + role);		

		List<Users> users = new ArrayList<Users>();

		TypedQuery<Assignments> assignementsTQ = Assignments.findAssignmentsesByAuthority(auth);
		if (assignementsTQ != null && assignementsTQ.getResultList().size() > 0){
			List<Assignments> assignments = assignementsTQ.getResultList();
			if (assignments != null){
				for (Assignments assignment : assignments) {
					if (assignment != null){
						users.add(assignment.getUsr());
					}
				}
			}
		}

		if (users == null || users.isEmpty()){
			throw new ResourceNotFoundException("User not found for role: " + role);		
		}
		
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResourceList url = asm.toResource(users);
		
		return new ResponseEntity<>(url, HttpStatus.OK);	
	}


	public ResponseEntity<UserResourceList> getAdvisorsForUser(Long id) {
		System.out.println("getAdvisorsForUser - try to get for id:" + id);

		if (id == null) throw new BadRequestException("ID is missing");
		
	
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + id);
		
		TypedQuery<UserAdvisor> tq = UserAdvisor.findUserAdvisorsByClientAndStatusEquals(user, "ACCEPTED");
		if (tq != null && tq.getResultList().isEmpty()) throw new ResourceNotFoundException("User Advisors not found for user: " + id);	

		List<Users> advisors = new ArrayList<Users>();
		List<UserAdvisor> userAdvisors = tq.getResultList();
		
		for (UserAdvisor userAdvisor : userAdvisors) {
			if (userAdvisor != null){
				Users advisor = userAdvisor.getAdvisor();
				if (advisor != null){
					advisors.add(advisor);
				}
			}
		}
				
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResourceList url = asm.toResource(advisors);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}


	public ResponseEntity<String> addAdvisorsToUser(Long userId, Long advisorId) {
		System.out.println("getAdvisorsForUser - try to get for id: " + userId + " and advisor: " + advisorId);

		if (userId == null) throw new BadRequestException("User ID is missing");
		if (advisorId == null) throw new BadRequestException("Advisor ID is missing");
			
		Users user = Users.findUsers(userId);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + userId);		

		Users advisor = Users.findUsers(advisorId);
		if (advisor == null) throw new ResourceNotFoundException("Advisor not found by ID: " + advisorId);		
		
		UserAdvisor ua = new UserAdvisor();
		ua.setAdvisor(advisor);
		ua.setClient(user);
		ua.setStatus("PENDING");
		ua.persist();

		HttpHeaders headers = new HttpHeaders();
		URI location = URI.create("/rest/v1/est/userAdvisors/" + ua.getId().toString());
		headers.setLocation(location);

		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}

	

	public ResponseEntity<UserAdvisorResourceList> getUserAdvisorsForUserAndAdvisor(Long userId, Long advisorId) {
		if (userId == null) throw new BadRequestException("User ID is missing");
		if (advisorId == null) throw new BadRequestException("Advisor ID is missing");

		Users user = Users.findUsers(userId);		
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + userId);

		Users advisor = Users.findUsers(advisorId);		
		if (advisor == null) throw new ResourceNotFoundException("Advisor not found by ID: " + advisorId);

		TypedQuery<UserAdvisor> tq = UserAdvisor.findUserAdvisorsByAdvisorAndClient(advisor, user);
		if (tq != null && tq.getResultList().isEmpty()) throw new ResourceNotFoundException("User Advisors not found for user: " + userId + " advisor: " + advisorId);	

		List<UserAdvisor> userAdvisors = tq.getResultList();

		UserAdvisorResourceAssembler asm = new UserAdvisorResourceAssembler();
		UserAdvisorResourceList list = asm.toResource(userAdvisors);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	


	public ResponseEntity<UserResourceList> getClientsForUser(Long id, String status) {
		System.out.println("getClientsForUser - try to get for id:" + id);

		if (id == null) throw new BadRequestException("ID is missing");
			
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + id);
	
		TypedQuery<UserAdvisor> tq = null;
		if (status.isEmpty() || status.equals("ALL")){
			tq = UserAdvisor.findUserAdvisorsByAdvisor(user);					
		} else {
			tq = UserAdvisor.findUserAdvisorsByAdvisorAndStatusEquals(user, status.toUpperCase());							
		}
		
		if (tq != null && tq.getResultList().isEmpty()) throw new ResourceNotFoundException("User Advisors not found for user: " + id + " by status: " + status);	

		List<Users> clients = new ArrayList<Users>();
		List<UserAdvisor> userAdvisors = tq.getResultList();
		
		for (UserAdvisor userAdvisor : userAdvisors) {
			if (userAdvisor != null){
				Users client = userAdvisor.getClient();
				if (client != null){
					clients.add(client);
				}
			}
		}
				
		UserResourceAssembler asm = new UserResourceAssembler();
		UserResourceList url = asm.toResource(clients);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}


	


	public ResponseEntity<FieldResourceList> getFieldsForUser(Long id) {
		if (id == null) throw new BadRequestException("ID is missing");
			
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + id);
		
		Set<Field> fieldsSet = user.getFields();
		if (fieldsSet.isEmpty()) throw new ResourceNotFoundException("Fields not found for user: " + id);	

		List<Field> fields = new ArrayList<Field>();
		fields.addAll(fieldsSet);
		
		FieldResourceAssembler asm = new FieldResourceAssembler();
		FieldResourceList frl = asm.toResource(fields);

		return new ResponseEntity<>(frl, HttpStatus.OK);
	}



	public ResponseEntity<CompanyResourceList> getCompaniesForUser(Long id) {
		if (id == null) throw new BadRequestException("ID is missing");
		
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + id);
		
		Company company = user.getCompany();
		if (company == null) throw new ResourceNotFoundException("Companies not found for user: " + id);	

		List<Company> companies = new ArrayList<Company>();
		companies.add(company);
		
		CompanyResourceAssembler asm = new CompanyResourceAssembler();
		CompanyResourceList crl = asm.toResource(companies);

		return new ResponseEntity<>(crl, HttpStatus.OK);
	}



	public ResponseEntity<UserRoleResourceList> getRolesForUser(Long id) {
		if (id == null) throw new BadRequestException("ID is missing");
		
		Users user = Users.findUsers(id);
		if (user == null) throw new ResourceNotFoundException("User not found by ID: " + id);
		
		List <Authorities> roles = new ArrayList<Authorities>();
		
		TypedQuery<Assignments> assignmentsTQ = Assignments.findAssignmentsesByUsr(user);
		if (assignmentsTQ != null && assignmentsTQ.getResultList().size() > 0){
			List<Assignments> assignments = assignmentsTQ.getResultList();
			if (assignments != null){
				for (Assignments assignment : assignments) {
					if (assignment != null){
						roles.add(assignment.getAuthority());
					}
				}
			}
		}	
		
		if (roles.isEmpty()) throw new ResourceNotFoundException("Roles not found for user: " + id);

		UserRoleResourceAssembler asm = new UserRoleResourceAssembler();
		UserRoleResourceList urrl = asm.toResource(roles);

		return new ResponseEntity<>(urrl, HttpStatus.OK);
	}




 }
