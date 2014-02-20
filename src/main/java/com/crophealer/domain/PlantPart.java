package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantPart {

    /**
     */
    private String comment;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPart")
    private Set<PlantPartTranslation> translations = new HashSet<PlantPartTranslation>();
}
