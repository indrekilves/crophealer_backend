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
public class Product {

    /**
     */
    private String comment;

    /**
     */
    private String pictureUrl;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @ManyToOne
    private Producer producer;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductTranslation> translations = new HashSet<ProductTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductReseller> productResellers = new HashSet<ProductReseller>();
}
