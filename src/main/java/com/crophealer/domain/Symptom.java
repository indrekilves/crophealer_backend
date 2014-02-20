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
public class Symptom {

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "symptom")
    private Set<SymptomTranslation> translations = new HashSet<SymptomTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "symptom")
    private Set<SymptomPicture> pictures = new HashSet<SymptomPicture>();
}
