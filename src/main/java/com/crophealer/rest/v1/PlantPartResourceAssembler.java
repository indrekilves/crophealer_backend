package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPart;
import com.crophealer.domain.PlantPartTranslation;

public class PlantPartResourceAssembler
{

    public PlantPartResource toResource( PlantPart pp, Languages l )
    {

        PlantPartResource ppr = new PlantPartResource();
        ppr.setId( pp.getId() );
        ppr.setIconUrl( pp.getIconUrl() );
        if ( l != null )
        {
            TypedQuery < PlantPartTranslation > tq = PlantPartTranslation.findPlantPartTranslationsByPlantPartAndLang(
                    pp, l );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                PlantPartTranslation ppt = tq.getSingleResult();
                if ( ppt != null )
                {
                    ppr.setName( ppt.getName() );
                    ppr.setDescription( ppt.getDescription() );
                }
            }
        }
        return ppr;
    }

    public PlantPartResourceList toResource( List < PlantPart > ppl, Languages l )
    {
        if ( ppl == null )
            return null;

        List < PlantPartResource > pprl = new ArrayList < PlantPartResource >();
        for ( PlantPart pp : ppl )
        {
            pprl.add( toResource( pp, l ) );
        }

        return new PlantPartResourceList( pprl );
    }
}
