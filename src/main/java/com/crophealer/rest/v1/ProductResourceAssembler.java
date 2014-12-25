package com.crophealer.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.ActiveIngredient;
import com.crophealer.domain.Languages;
import com.crophealer.domain.ProblemAIProduct;
import com.crophealer.domain.Producer;
import com.crophealer.domain.Product;
import com.crophealer.domain.ProductTranslation;

public class ProductResourceAssembler
{

    public ProductResource toResource( Product p, Languages language )
    {

        ProductResource pr = new ProductResource();
        pr.setId( p.getId() );
        pr.setPictureUrl( p.getPictureUrl() );

        if ( language != null )
        {
            TypedQuery < ProductTranslation > tq = ProductTranslation.findProductTranslationsByProductAndLang( p,
                    language );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                ProductTranslation pt = tq.getSingleResult();
                if ( pt != null )
                {
                    pr.setName( pt.getName() );
                    pr.setDescription( pt.getDescription() );
                    pr.setWarning( pt.getWarning() );

                    pr.setType( pt.getType() );
                    pr.setUsageRate( pt.getUsageRate() );
                    pr.setActiveIngredientRate( pt.getActiveIngredientRate() );
                    pr.setPpc( pt.getPpc() );
                    pr.setRaintFastness( pt.getRaintFastness() );
                    pr.setFormulation( pt.getFormulation() );
                    pr.setWaterVolume( pt.getWaterVolume() );
                    pr.setSprinkleTime( pt.getSprinkleTime() );
                    pr.setWorkDelay( pt.getWorkDelay() );
                    pr.setHarvestDelay( pt.getHarvestDelay() );
                    pr.setEffectMechanism( pt.getEffectMechanism() );
                    pr.setSprinkleTimes( pt.getSprinkleTimes() );
                }
            }
        }
        Producer producer = p.getProducer();
        if ( producer != null )
        {
            ProducerResourceAssembler asm = new ProducerResourceAssembler();
            pr.setProducer( asm.toResource( producer ) );
        }

        return pr;
    }

    public ProductResourceList toResource( List < Product > pl, Languages l )
    {
        if ( pl == null )
            return null;

        List < ProductResource > prl = new ArrayList < ProductResource >();
        for ( Product p : pl )
        {
            prl.add( toResource( p, l ) );
        }

        return new ProductResourceList( prl );
    }

    public ProductResourceList paipsToResource( List < ProblemAIProduct > paips, Languages l )
    {
        if ( paips == null || l == null )
            return null;

        List < ProductResource > prl = new ArrayList < ProductResource >();

        for ( ProblemAIProduct paip : paips )
        {
            prl.add( paipToResource( paip, l ) );
        }

        return new ProductResourceList( prl );
    }

    private ProductResource paipToResource( ProblemAIProduct paip, Languages language )
    {
        Product p = paip.getProduct();
        ProductResource pr = new ProductResource();
        pr.setId( p.getId() );
        pr.setPictureUrl( p.getPictureUrl() );
        // pr.setRaintFastness(p.getRaintFastness());

        if ( language != null )
        {
            TypedQuery < ProductTranslation > tq = ProductTranslation.findProductTranslationsByProductAndLang( p,
                    language );
            if ( tq != null && tq.getResultList().size() > 0 )
            {
                ProductTranslation pt = tq.getSingleResult();
                if ( pt != null )
                {
                    pr.setName( pt.getName() );
                    pr.setDescription( pt.getDescription() );
                    pr.setWarning( pt.getWarning() );
                    pr.setType( pt.getType() );
                    pr.setUsageRate( pt.getUsageRate() );
                    pr.setActiveIngredientRate( pt.getActiveIngredientRate() );
                    pr.setPpc( pt.getPpc() );
                    pr.setRaintFastness( pt.getRaintFastness() );
                    pr.setFormulation( pt.getFormulation() );
                    pr.setWaterVolume( pt.getWaterVolume() );
                    pr.setSprinkleTime( pt.getSprinkleTime() );
                    pr.setWorkDelay( pt.getWorkDelay() );
                    pr.setHarvestDelay( pt.getHarvestDelay() );
                    pr.setEffectMechanism( pt.getEffectMechanism() );
                    pr.setSprinkleTimes( pt.getSprinkleTimes() );
                }
            }
        }
        Producer producer = p.getProducer();
        if ( producer != null )
        {
            ProducerResourceAssembler prodResAsm = new ProducerResourceAssembler();
            pr.setProducer( prodResAsm.toResource( producer ) );
        }

        String effect = paip.getProductEffect();
        if ( effect != null )
        {
            pr.setEffect( effect );
        }

        ActiveIngredient ai = paip.getActiveIngredient();
        if ( ai != null )
        {
            ActiveIngredientResourceAssembler aiResAsm = new ActiveIngredientResourceAssembler();
            pr.setActiveIngredient( aiResAsm.paipToResource( paip, language ) );
        }

        return pr;
    }
}
