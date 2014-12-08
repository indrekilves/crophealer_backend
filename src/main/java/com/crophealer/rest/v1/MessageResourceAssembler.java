package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.crophealer.domain.DiagnosedProblem;
import com.crophealer.domain.Field;
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
			mr.setSenderName(sender.getUsername());
		}
		Users receiver = m.getReceiver(); 
		if (receiver != null){
			mr.setReceiverID(receiver.getId());
			mr.setReceiverName(receiver.getUsername());
		}
		DiagnosedProblem dp = m.getDiagnosedProblem();
		if (dp != null) {
			mr.setDiagnosedProblemID(dp.getId());
		}
		
		Field field = m.getField();
		if (field != null){
			mr.setFieldID(field.getId());
		}
		
		Message parent = m.getParent();
		if (parent != null) {
			mr.setParentID(parent.getId());
		}
		
		Date created = m.getCreatedTimestamp();
		if (created != null) {
			mr.setCreatedTimestamp(DateFormatUtils.format(created, "yyyy-MM-dd HH:mm:SS"));			
		}
		
		Date modified = m.getModifiedTimestamp();
		if (modified != null) {
			mr.setModifiedTimestamp(DateFormatUtils.format(modified, "yyyy-MM-dd HH:mm:SS"));			
		}
		
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

