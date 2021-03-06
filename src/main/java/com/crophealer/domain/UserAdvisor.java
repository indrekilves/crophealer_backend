package com.crophealer.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.crophealer.security.Users;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUserAdvisorsByAdvisor", "findUserAdvisorsByAdvisorAndStatusEquals",
        "findUserAdvisorsByClientAndStatusEquals", "findUserAdvisorsByAdvisorAndClient" })
public class UserAdvisor
{

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date effectiveFrom;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date effectiveTo;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modifiedDate;

    /**
     */
    @ManyToOne
    private Users client;

    /**
     */
    @ManyToOne
    private Users advisor;

    /**
     */
    private String status;
}
