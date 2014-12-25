package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import com.crophealer.domain.Languages;
import com.crophealer.domain.PlantPartPhaseProblem;

public class PlantPartPhaseProblemResourceAssembler
{

    public PlantPartPhaseProblemResource toResource( PlantPartPhaseProblem pppProblem, Languages language )
    {

        PlantPartPhaseProblemResource pppProblemResource = new PlantPartPhaseProblemResource();
        pppProblemResource.setId( pppProblem.getId() );
        pppProblemResource.setComment( pppProblem.getComment() );

        if ( pppProblem != null && language != null )
        {
            ProblemResourceAssembler pAsm = new ProblemResourceAssembler();
            pppProblemResource.setProblem( pAsm.toResource( pppProblem.getProblem(), language ) );
        }

        return pppProblemResource;
    }

    public PlantPartPhaseProblemResourceList toResource( List < PlantPartPhaseProblem > pl, Languages l )
    {
        if ( pl == null )
            return null;

        List < PlantPartPhaseProblemResource > prl = new ArrayList < PlantPartPhaseProblemResource >();
        for ( PlantPartPhaseProblem p : pl )
        {
            prl.add( toResource( p, l ) );
        }

        return new PlantPartPhaseProblemResourceList( prl );
    }

}
