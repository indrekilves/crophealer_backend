package com.crophealer.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantPartTranslationsByLang", "findPlantPartTranslationsByPlantPartAndLang" })
public class PlantPartTranslation
{

    /**
     */
    private String name;

    /**
     */
    @ManyToOne
    private PlantPart plantPart;

    /**
     */
    @ManyToOne
    private Languages lang;

    /**
     */
    @Size(max = 1000)
    private String description;
}
