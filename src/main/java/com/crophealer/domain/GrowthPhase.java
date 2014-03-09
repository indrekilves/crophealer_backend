package com.crophealer.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findGrowthPhasesByCommentEquals" })
public class GrowthPhase {

    /**
     */
    private String comment;

    /**
     */
    private String iconUrl;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "growthPhase")
    private Set<GrowthPhaseTranslation> translations = new HashSet<GrowthPhaseTranslation>();
}
