package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Producer {

    /**
     */
    private String name;

    /**
     */
    private String email;

    /**
     */
    private String phone;

    /**
     */
    private String contactPerson;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Product> products = new HashSet<Product>();
}
