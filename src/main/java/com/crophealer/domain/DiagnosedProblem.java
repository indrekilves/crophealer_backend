package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.crophealer.security.Users;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DiagnosedProblem {

    /**
     */
    @Size(max = 10000)
    private String comment;

    /**
     */
    private String location;

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

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date closedTimestamp;

    /**
     */
    @ManyToOne
    private Users usr;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosedProblem")
    private Set<DiagnosedProblemPicture> pictures = new HashSet<DiagnosedProblemPicture>();

    /**
     */
    @ManyToOne
    private PlantPartPhaseProblem plantPartPhaseProblem;

    /**
     */
    private String symptomIDsCSV;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosedProblem")
    private Set<Message> messages = new HashSet<Message>();

    /**
     */
    @ManyToOne
    private Field field;
}
