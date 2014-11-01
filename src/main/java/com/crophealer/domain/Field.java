package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.crophealer.security.Users;
import javax.persistence.ManyToOne;

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
}
