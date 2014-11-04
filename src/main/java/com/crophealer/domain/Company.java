package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.crophealer.security.Users;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    /**
     */
    private String name;

    /**
     */
    private String address;

    /**
     */
    private String phone;

    /**
     */
    private String email;

    /**
     */
    private String contactPerson;

    /**
     */
    private String fieldSize;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Users> users = new HashSet<Users>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Field> fields = new HashSet<Field>();
}
