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
public class Plant {

    /**
     */
    private String comment;

    /**
     */
    private String iconUrl;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plant")
    private Set<PlantTranslation> translations = new HashSet<PlantTranslation>();
}
