package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.GrowthPhaseTranslation;
import com.crophealer.domain.Languages;

public class GrowthPhaseLoader extends GenericLoader
{
    protected Integer activeSheetNum = 2;

    public GrowthPhaseLoader( SpreadSheetReader ssReader )
    {
        super( ssReader );
        this.setActiveSheetNum( this.activeSheetNum );
    }

    public void loadPhaseCodes()
    {
        List < String > codes = ssReader.getColumnAsArray( 0 );
        List < String > urls = ssReader.getColumnAsArray( 1 );

        for ( int i = 2; i < codes.size(); i++ )
        {
            if ( !codes.get( i ).isEmpty() )
            {
                GrowthPhase gp = new GrowthPhase();
                gp.setComment( codes.get( i ) );
                gp.setIconUrl( urls.get( i ) );
                gp.persist();
            }
        }

    }

    public void loadPhaseTranslations()
    {
        List < String > languageRow = ssReader.getRowAsArray( 0 );

        for ( int i = 0; i < languageRow.size(); i++ )
        {
            if ( !languageRow.get( i ).isEmpty() )
            {
                try
                {
                    Languages lang = Languages.findLanguagesesByNameEqualsCustom( languageRow.get( i ) )
                            .getSingleResult();
                    List < String > names = ssReader.getColumnAsArray( i, 2 );
                    List < String > descs = ssReader.getColumnAsArray( i + 1, 2 );
                    this.loadTranslationsForLanguage( lang, names, descs );
                }
                catch ( Exception e )
                {
                    System.out.println( e.getMessage() );
                }
            }
        }

    }

    private void loadTranslationsForLanguage( Languages lang, List < String > names, List < String > descs )
    {
        List < GrowthPhase > phases = GrowthPhase.findAllGrowthPhases();
        for ( int i = 0; i < phases.size(); i++ )
        {
            GrowthPhaseTranslation gpt = new GrowthPhaseTranslation();
            gpt.setLang( lang );
            gpt.setGrowthPhase( phases.get( i ) );
            gpt.setName( names.get( i ) );
            gpt.setDescription( descs.get( i ) );
            gpt.persist();
        }

    }

}
