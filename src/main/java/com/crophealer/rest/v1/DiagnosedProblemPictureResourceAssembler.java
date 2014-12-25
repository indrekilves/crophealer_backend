package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.crophealer.domain.DiagnosedProblemPicture;

public class DiagnosedProblemPictureResourceAssembler
{

    public DiagnosedProblemPictureResource toResource( DiagnosedProblemPicture dpp )
    {

        DiagnosedProblemPictureResource dppr = new DiagnosedProblemPictureResource();
        dppr.setId( dpp.getId() );
        dppr.setName( dpp.getName() );
        dppr.setPictureUrl( dpp.getPictureUrl() );

        return dppr;
    }

    public DiagnosedProblemPictureResourceList toResource( Set < DiagnosedProblemPicture > dppl )
    {
        if ( dppl == null )
            return null;

        List < DiagnosedProblemPictureResource > dpprl = new ArrayList < DiagnosedProblemPictureResource >();
        for ( DiagnosedProblemPicture dpp : dppl )
        {
            dpprl.add( toResource( dpp ) );
        }

        return new DiagnosedProblemPictureResourceList( dpprl );
    }
}
