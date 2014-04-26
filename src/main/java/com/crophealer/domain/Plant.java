package com.crophealer.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantsByCommentEquals", "findPlantsByTranslations" })
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plant", orphanRemoval = true)
    private final Set<PlantTranslation> translations = new HashSet<PlantTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plant", orphanRemoval = true)
    private final Set<PlantPartPhase> plantPartPhases = new HashSet<PlantPartPhase>();

    public static Plant getSinglePlantByName(String plantStr) {
        try {
            TypedQuery<Plant> plantQ = Plant.findPlantsByCommentEquals(plantStr);
            if (plantQ.getResultList().size() > 0) return plantQ.getSingleResult(); else return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public boolean isOSR()
    {
    	if (this.getComment().equals("Spring OSR")) return true;
    	if (this.getComment().equals("Winter OSR")) return true;
    	return false;
    }
}
