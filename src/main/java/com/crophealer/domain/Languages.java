package com.crophealer.domain;
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
    
    
    public static Languages getSingleLanguageByName(String languageStr)
    {
    	TypedQuery<Languages> languagesQ = Languages.findLanguagesesByNameEquals(languageStr);
		
		if (languagesQ.getResultList().size() > 0)
		{
			return languagesQ.getSingleResult();
		}
		else
		{
			return null;
		}
    }
}
