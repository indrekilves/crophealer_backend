package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantTranslation {

    /**
     */
    private String name;

    /**
     */
    @Size(max = 1000)
    private String description;

    /**
     */
    @ManyToOne
    private Plant plant;

    /**
     */
    @ManyToOne
    private Languages lang;
}
