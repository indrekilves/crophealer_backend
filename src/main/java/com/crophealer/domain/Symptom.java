package com.crophealer.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;


@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findSymptomsByCommentEquals" })
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
    private final Set<SymptomTranslation> translations = new HashSet<SymptomTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "symptom")
    private final Set<SymptomPicture> pictures = new HashSet<SymptomPicture>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "symptom")
    private final Set<PlantPartPhaseSymptom> plantPartPhaseSymptoms = new HashSet<PlantPartPhaseSymptom>();
    
    
    public static Symptom getSingleSymptomByComment(String comment) {
    	TypedQuery<Symptom> pQ = Symptom.findSymptomsByCommentEqualsCustom(comment);
		
		if (pQ.getResultList().size() > 0) {
			return pQ.getSingleResult();
		} else {
			return null;
		}
    }
    
    
    /**  CUSTOM FINDERS  **/  
    
    
    public static TypedQuery<Symptom> findSymptomsByCommentEqualsCustom(String comment) {
        if (comment == null || comment.length() == 0) throw new IllegalArgumentException("The comment argument is required");
        EntityManager em = Symptom.entityManager();
        TypedQuery<Symptom> q = em.createQuery("SELECT o FROM Symptom AS o WHERE LOWER(o.comment) = LOWER(:comment)", Symptom.class);
        q.setParameter("comment", comment);
        return q;
    }
    
}
