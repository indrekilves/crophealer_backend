package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPlantPartsByCommentEquals" })
public class PlantPart {

    /**
     */
    private String comment;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPart")
    private Set<PlantPartTranslation> translations = new HashSet<PlantPartTranslation>();

    public static PlantPart getSinglePlantPartByName(String plantPartStr) {
        TypedQuery<PlantPart> ppQ = PlantPart.findPlantPartsByCommentEquals(plantPartStr);
        if (ppQ.getResultList().size() > 0) {
            return ppQ.getSingleResult();
        } else {
            return null;
        }
    }

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPart")
    private Set<PlantPartPhase> phases = new HashSet<PlantPartPhase>();
}
