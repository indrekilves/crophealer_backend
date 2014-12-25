package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crophealer.domain.SymptomPicture;

public class SymptomPictureResourceAssembler
{

    public SymptomPictureResource toResource( SymptomPicture sp )
    {

        SymptomPictureResource spr = new SymptomPictureResource();
        spr.setId( sp.getId() );
        spr.setName( sp.getName() );
        spr.setPictureUrl( sp.getPictureUrl() );

        return spr;
    }

    public SymptomPictureResourceList toResource( Set < SymptomPicture > spl )
    {
        if ( spl == null )
            return null;

        List < SymptomPictureResource > prl = new ArrayList < SymptomPictureResource >();
        for ( SymptomPicture sp : spl )
        {
            prl.add( toResource( sp ) );
        }

        return new SymptomPictureResourceList( prl );
    }
}
