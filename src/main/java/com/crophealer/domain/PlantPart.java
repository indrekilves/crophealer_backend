package com.crophealer.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;


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
    
    private final Set<PlantPartTranslation> translations = new HashSet<PlantPartTranslation>();

    public static PlantPart getSinglePlantPartByName(String plantPartStr) {
        TypedQuery<PlantPart> ppQ = PlantPart.findPlantPartsByCommentEqualsCustom(plantPartStr);
        if (ppQ.getResultList().size() > 0) {
            return ppQ.getSingleResult();
        } else {
            return null;
        }
    }
    
    
    public static TypedQuery<PlantPart> findPlantPartsByCommentEqualsCustom(String comment) {
        if (comment == null || comment.length() == 0) throw new IllegalArgumentException("The comment argument is required");
        EntityManager em = PlantPart.entityManager();
        TypedQuery<PlantPart> q = em.createQuery("SELECT o FROM PlantPart AS o WHERE LOWER(o.comment) = LOWER(:comment)", PlantPart.class);
        q.setParameter("comment", comment);
        return q;
    }

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantPart")
    private final Set<PlantPartPhase> phases = new HashSet<PlantPartPhase>();
}
