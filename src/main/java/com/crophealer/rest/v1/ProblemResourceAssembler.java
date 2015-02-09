package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Languages;
import com.crophealer.domain.Problem;
import com.crophealer.domain.ProblemTranslation;

public class ProblemResourceAssembler
{

    public ProblemResource toResource( Problem p, Languages language )
    {

        ProblemResource pr = new ProblemResource();
        pr.setId( p.getId() );
        pr.setLatinName( p.getLatinName() );
        if ( language != null )
        {
            TypedQuery < ProblemTranslation > tq = ProblemTranslation.findProblemTranslationsByProblemAndLang( p,
                    language );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                ProblemTranslation pt = tq.getSingleResult();
                if ( pt != null )
                {
                    pr.setName( pt.getName() );
                    pr.setDescription( pt.getDescription() );
                    pr.setWarning( pt.getWarning() );
                }
            }
        }

        ProblemPictureResourceAssembler spAsm = new ProblemPictureResourceAssembler();
        pr.setPictures( spAsm.toResource( p.getPictures() ) );

        return pr;
    }

    public ProblemResourceList toResource( List < Problem > pl, Languages l )
    {
        if ( pl == null )
            return null;

        List < ProblemResource > prl = new ArrayList < ProblemResource >();
        for ( Problem p : pl )
        {
            prl.add( toResource( p, l ) );
        }

        return new ProblemResourceList( prl );
    }
}
