package com.crophealer.domain;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findLanguagesesByNameEquals" })
public class Languages {

    /**
     */
    private String name;

    /**
     */
    private String nativeName;
    
    
    public static Languages getSingleLanguageByName(String languageStr) {
    	TypedQuery<Languages> languagesQ = Languages.findLanguagesesByNameEqualsCustom(languageStr);
		
		if (languagesQ.getResultList().size() > 0) {
			return languagesQ.getSingleResult();
		} else {
			return null;
		}
    }
    
    
    public static TypedQuery<Languages> findLanguagesesByNameEqualsCustom(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Languages.entityManager();
        TypedQuery<Languages> q = em.createQuery("SELECT o FROM Languages AS o WHERE LOWER(o.name) = LOWER(:name)", Languages.class);
        q.setParameter("name", name);
        return q;
    }
}
