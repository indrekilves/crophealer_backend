package com.crophealer.domain;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProductTranslationsByLang", "findProductTranslationsByNameEquals", "findProductTranslationsByProductAndLang" })
public class ProductTranslation {

    /**
     */
    private String name; // toode

    /**
     */
    @Size(max = 1000)
    private String description;

    /**
     */
    @Size(max = 1000)
    private String warning;

    /**
     */
    @ManyToOne
    private Product product;

    /**
     */
    @ManyToOne
    private Languages lang;

    /**
     */
    private String type; // toote liik

    /**
     */
    private String usageRate; // kulunorm1 - kulunorm 2. (If kulunorm2=0 then just kulunorm1)

    /**
     */
    private String activeIngredientRate; // sisaldus (toimeaine)

    /**
     */
    private String ppc; //TKT

    /**
     */
    private String raintFastness; // vihmakindlus

    /**
     */
    private String formulation; //Preparaadi vorm

    /**
     */
    private String waterVolume; // veekulu

    /**
     */
    private String sprinkleTime; // Pritsimise aeg

    /**
     */
    private String workDelay; // Töö-oode

    /**
     */
    private String harvestDelay; // Ooteaeg

    /**
     */
    private String effectMechanism; // Toimemehhanism

    /**
     */
    private String sprinkleTimes;
}
