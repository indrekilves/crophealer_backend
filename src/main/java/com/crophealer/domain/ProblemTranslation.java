package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemTranslationsByLang" })
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
}
