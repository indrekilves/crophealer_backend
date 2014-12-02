package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.crophealer.security.Users;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
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
    private Set<Message> children = new HashSet<Message>();

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
}
