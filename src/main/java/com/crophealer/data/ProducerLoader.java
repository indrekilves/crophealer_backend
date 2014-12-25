package com.crophealer.data;

import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.Country;
import com.crophealer.domain.Producer;

public class ProducerLoader extends GenericLoader
{

    protected Integer activeSheetNum = 3;

    public ProducerLoader( SpreadSheetReader ssReader )
    {
        super( ssReader );
        this.setActiveSheetNum( this.activeSheetNum );
    }

    public void loadProducers()
    {
        List < String > producerNames = this.ssReader.getColumnAsArray( 0, 3 );

        for ( int i = 0; i < producerNames.size(); i++ )
        {
            if ( !producerNames.get( i ).isEmpty() )
            {
                List < String > producerDetails = this.ssReader.getRowAsArray( 3 + i );
                Producer p = new Producer();
                p.setName( producerNames.get( i ) );
                p.setEmail( producerDetails.get( 1 ) );
                p.setContactPerson( producerDetails.get( 2 ) );
                p.setPhone( producerDetails.get( 3 ) );

                Country c = CountryLoader.loadCountryByName( producerDetails.get( 4 ) );
                p.setCountry( c );
                p.persist();
            }
        }
    }

    public static Producer loadProducerByName( String producerName )
    {
        TypedQuery < Producer > producerQ = Producer.findProducersByNameEquals( producerName );

        if ( producerQ.getResultList().size() > 0 )
        {
            return producerQ.getSingleResult();
        }
        else
        {
            Producer p = new Producer();
            p.setName( producerName );
            p.persist();
            return p;
        }
    }

}
