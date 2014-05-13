package com.crophealer.domain;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemTranslationsByLang", "findProblemTranslationsByProblemAndLang", "findProblemTranslationsByNameEquals" })
public class ProblemTranslation {

    /**
     */
    private String name;

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
    private Problem problem;

    /**
     */
    @ManyToOne
    private Languages lang;
    
    public static ProblemTranslation getSingleProblemTranslationByName(String name) {
        try {
            
            List<ProblemTranslation> allProbTrans = ProblemTranslation.findAllProblemTranslations();
            for (ProblemTranslation problemTranslation : allProbTrans) {
				if (problemTranslation.getName().trim().equalsIgnoreCase(name))
					return problemTranslation;
			}
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
