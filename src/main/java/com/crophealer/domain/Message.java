package com.crophealer.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.crophealer.rest.v1.MessageResource;
import com.crophealer.rest.v1.RequestError;
import com.crophealer.security.Users;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findMessagesByStatusEquals" })
public class Message {

    /**
     */
    private String subject;

    /**
     */
    private String comment;

    /**
     */
    private String status;

    /**
     */
    @ManyToOne
    private Users sender;

    /**
     */
    @ManyToOne
    private Users receiver;

    /**
     */
    @ManyToOne
    private DiagnosedProblem diagnosedProblem;

    /**
     */
    @ManyToOne
    private Message parent;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private final Set<Message> children = new HashSet<Message>();

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdTimestamp;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modifiedTimestamp;

    // from resource
    public static RequestError validateResource(MessageResource mr) {
        if (mr.getSubject().isEmpty()) return RequestError.E0009;
        if (mr.getSenderID() == null) return RequestError.E0007;
        if (mr.getReceiverID() == null) return RequestError.E0008;
        Users sender = Users.findUsers(mr.getSenderID());
        if (sender == null) return RequestError.E0010;
        Users receiver = Users.findUsers(mr.getSenderID());
        if (receiver == null) return RequestError.E0011;
        return null;
    }

    public static Message createFromResource(MessageResource mr) {
        if (mr == null) return null;
        Message m = new Message();
        m.fillFromRecource(mr);
        m.setStatus("Received");
        m.setCreatedTimestamp(new Date());
        m.persist();
        return m;
    }

    public void updateFromResource(MessageResource mr) {
        fillFromRecource(mr);
        this.setStatus("Updated");
        this.persist();
    }

    private void fillFromRecource(MessageResource mr) {
        if (mr.getSubject() != null) this.setSubject(mr.getSubject());
        if (mr.getComment() != null) this.setComment(mr.getComment());
        Users sender = Users.findUsers(mr.getSenderID());
        if (sender != null) {
            this.setSender(sender);
        }
        Users receiver = Users.findUsers(mr.getReceiverID());
        if (receiver != null) {
            this.setReceiver(receiver);
        }
        Long dpID = mr.getDiagnosedProblemID();
        if (dpID != null) {
            DiagnosedProblem dp = DiagnosedProblem.findDiagnosedProblem(dpID);
            if (dp != null) {
                this.setDiagnosedProblem(dp);
            }
        }
        Long parentID = mr.getParentID();
        if (parentID != null) {
            Message parent = Message.findMessage(parentID);
            if (parent != null) {
                this.setParent(parent);
            }
        }
        this.setModifiedTimestamp(new Date());
    }

    /**
     */
    @ManyToOne
    private Field field;
}
