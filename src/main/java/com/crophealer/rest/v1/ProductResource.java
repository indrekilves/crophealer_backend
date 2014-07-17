package com.crophealer.rest.v1;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@JsonRootName("product")
public class ProductResource {

    private Long id;

    private String name;

    private String description;

    private String warning;

    private String pictureUrl;

    private String effect; // Effektiivsus

    private String raintFastness; // Vihmakindlus

    private String type; // toote liik

    private String usageRate; // kulunorm1 - kulunorm 2. (If kulunorm2=0 then just kulunorm1)

    private String activeIngredientRate; // sisaldus (toimeaine)

    private String ppc; //TKT

    private String formulation; //Preparaadi vorm

    private String waterVolume; // veekulu

    private String sprinkleTime; // Pritsimise aeg

    private String workDelay; // Töö-oode

    private String harvestDelay; // Ooteaeg

    private String effectMechanism; // Toimemehhanism

    private String sprinkleTimes;

    private ProducerResource producer;

    private ActiveIngredientResource activeIngredient;
}
