package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findProblemsByLatinNameEquals" })
public class Problem {

    /**
     */
    private String latinName;

    /**
     */
    private String comment;

    /**
     */
    @ManyToOne
    private Country country;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private Set<ProblemTranslation> translations = new HashSet<ProblemTranslation>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private Set<ProblemPicture> pictures = new HashSet<ProblemPicture>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private Set<PlantPartPhaseSymptom> plantPartPhaseProblems = new HashSet<PlantPartPhaseSymptom>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problem")
    private Set<ProblemActiveIngredient> problemActiveIngredients = new HashSet<ProblemActiveIngredient>();
}
