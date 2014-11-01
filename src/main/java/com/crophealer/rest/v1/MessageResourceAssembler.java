package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Message;
import com.crophealer.security.Users;

public class MessageResourceAssembler {

	

	public MessageResource toResource(Message m) {
		
		MessageResource mr = new MessageResource();
		mr.setId(m.getId());
		mr.setStatus(m.getStatus());
		mr.setSubject(m.getSubject());
		mr.setComment(m.getComment());
		Users sender = m.getSender(); 
		if (sender != null){
			mr.setSenderID(sender.getId());
		}
		Users receiver = m.getReceiver(); 
		if (receiver != null){
			mr.setReceiverID(receiver.getId());
		}
		DiagnosedProblem dp = m.getDiagnosedProblem();
		if (dp != null) {
			mr.setDiagnosedProblemID(dp.getId());
		}
		Message parent = m.getParent();
		if (parent != null) {
			mr.setParentID(parent.getId());
		}
		mr.setCreatedTimestamp(m.getCreatedTimestamp());
		mr.setModifiedTimestamp(m.getModifiedTimestamp());;
		
		return mr;		
		
	}

	
	
	public MessageResourceList toResource(List<Message> ml) {
		if (ml == null) return null; 

		List<MessageResource> mrl = new ArrayList<MessageResource>();
		for (Message m : ml) {
			mrl.add(toResource(m));
		}
		
		return new MessageResourceList(mrl);
	}
}
