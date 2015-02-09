package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.ActiveIngredientTranslation;
import com.crophealer.domain.Languages;
import com.crophealer.domain.ProblemAIProduct;

public class ActiveIngredientResourceAssembler
{

    public ActiveIngredientResource toResource( ActiveIngredient ai, Languages language )
    {

        ActiveIngredientResource air = new ActiveIngredientResource();
        air.setId( ai.getId() );
        air.setLatinName( ai.getLatinName() );
        if ( language != null )
        {
            TypedQuery < ActiveIngredientTranslation > tq = ActiveIngredientTranslation
                    .findActiveIngredientTranslationsByActiveIngredientAndLang( ai, language );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                ActiveIngredientTranslation ait = tq.getSingleResult();
                if ( ait != null )
                {
                    air.setName( ait.getName() );
                    air.setDescription( ait.getDescription() );
                    air.setWarning( ait.getWarning() );
                }
            }
        }

        return air;
    }

    public ActiveIngredientResourceList toResource( List < ActiveIngredient > ail, Languages l )
    {
        if ( ail == null )
            return null;

        List < ActiveIngredientResource > airl = new ArrayList < ActiveIngredientResource >();
        for ( ActiveIngredient ai : ail )
        {
            airl.add( toResource( ai, l ) );
        }

        return new ActiveIngredientResourceList( airl );
    }

    public ActiveIngredientResourceList paipsToResource( List < ProblemAIProduct > paips, Languages l )
    {
        if ( paips == null || l == null )
            return null;

        List < ActiveIngredientResource > airl = new ArrayList < ActiveIngredientResource >();

        for ( ProblemAIProduct paip : paips )
        {
            airl.add( paipToResource( paip, l ) );
        }

        return new ActiveIngredientResourceList( airl );
    }

    public ActiveIngredientResource paipToResource( ProblemAIProduct paip, Languages language )
    {
        if ( paip == null || language == null )
            return null;
        ActiveIngredient ai = paip.getActiveIngredient();
        ActiveIngredientResource air = new ActiveIngredientResource();

        if ( ai == null )
            return air;

        air.setId( ai.getId() );
        air.setLatinName( ai.getLatinName() );
        if ( language != null )
        {
            TypedQuery < ActiveIngredientTranslation > tq = ActiveIngredientTranslation
                    .findActiveIngredientTranslationsByActiveIngredientAndLang( ai, language );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                ActiveIngredientTranslation ait = tq.getSingleResult();
                if ( ait != null )
                {
                    air.setName( ait.getName() );
                    air.setDescription( ait.getDescription() );
                    air.setWarning( ait.getWarning() );
                }
            }
        }

        String effect = paip.getAiEffect();
        if ( effect != null )
        {
            air.setEffect( effect );
        }
        return air;
    }
}
