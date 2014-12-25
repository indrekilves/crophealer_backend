package com.crophealer.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findGrowthPhaseTranslationsByNameEquals", "findGrowthPhaseTranslationsByLang",
        "findGrowthPhaseTranslationsByGrowthPhaseAndLang" })
public class GrowthPhaseTranslation
{

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
    private GrowthPhase growthPhase;

    /**
     */
    @ManyToOne
    private Languages lang;
}
