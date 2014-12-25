package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crophealer.domain.ProblemPicture;

public class ProblemPictureResourceAssembler
{

    public ProblemPictureResource toResource( ProblemPicture pp )
    {

        ProblemPictureResource spr = new ProblemPictureResource();
        spr.setId( pp.getId() );
        spr.setName( pp.getName() );
        spr.setPictureUrl( pp.getPictureUrl() );

        return spr;
    }

    public ProblemPictureResourceList toResource( Set < ProblemPicture > ppl )
    {
        if ( ppl == null )
            return null;

        List < ProblemPictureResource > prl = new ArrayList < ProblemPictureResource >();
        for ( ProblemPicture sp : ppl )
        {
            prl.add( toResource( sp ) );
        }

        return new ProblemPictureResourceList( prl );
    }
}
