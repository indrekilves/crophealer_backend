package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.GrowthPhaseTranslation;
import com.crophealer.domain.Languages;

public class GrowthPhaseResourceAssembler
{

    public GrowthPhaseResource toResource( GrowthPhase gp, Languages l )
    {

        GrowthPhaseResource gpr = new GrowthPhaseResource();
        gpr.setId( gp.getId() );
        gpr.setIconUrl( gp.getIconUrl() );
        if ( l != null )
        {
            TypedQuery < GrowthPhaseTranslation > tq = GrowthPhaseTranslation
                    .findGrowthPhaseTranslationsByGrowthPhaseAndLang( gp, l );
            if ( tq != null )
            {
                GrowthPhaseTranslation gpt = tq.getSingleResult();
                if ( gpt != null )
                {
                    gpr.setName( gpt.getName() );
                    gpr.setDescription( gpt.getDescription() );
                }
            }
        }
        return gpr;
    }

    public GrowthPhaseResourceList toResource( List < GrowthPhase > gpl, Languages l )
    {
        if ( gpl == null )
            return null;

        List < GrowthPhaseResource > gprl = new ArrayList < GrowthPhaseResource >();
        for ( GrowthPhase gp : gpl )
        {
            gprl.add( toResource( gp, l ) );
        }

        return new GrowthPhaseResourceList( gprl );
    }
}
