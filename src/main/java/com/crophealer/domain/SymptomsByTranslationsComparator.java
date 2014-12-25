package com.crophealer.domain;

import java.util.Comparator;

import javax.persistence.TypedQuery;

public class SymptomsByTranslationsComparator implements Comparator < Symptom >
{

    private Languages language = null;

    public SymptomsByTranslationsComparator( Languages language )
    {
        this.language = language;
    }

    @Override
    public int compare( Symptom o1, Symptom o2 )
    {
        try
        {
            SymptomTranslation t1 = getSymptomTranslationBySymptom( o1 );
            SymptomTranslation t2 = getSymptomTranslationBySymptom( o2 );

            if ( t1 != null && t2 != null )
            {
                return t1.getName().compareTo( t2.getName() );
            }
            return 1;
        }
        catch ( Exception e )
        {
            return 1;
        }
    }

    private SymptomTranslation getSymptomTranslationBySymptom( Symptom symptom )
    {
        TypedQuery < SymptomTranslation > tq = SymptomTranslation.findSymptomTranslationsBySymptomAndLang( symptom,
                this.language );
        if ( tq != null && tq.getResultList().size() > 0 )
        {
            return tq.getSingleResult();
        }
        return null;
    }

}
