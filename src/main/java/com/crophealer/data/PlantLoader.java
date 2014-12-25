package com.crophealer.data;

import java.util.List;

import com.crophealer.domain.Country;
import com.crophealer.domain.Languages;
import com.crophealer.domain.Plant;
import com.crophealer.domain.PlantTranslation;

public class PlantLoader extends GenericLoader
{

    protected Integer activeSheetNum = 5;

    public PlantLoader( SpreadSheetReader ssReader )
    {
        super( ssReader );
        this.setActiveSheetNum( this.activeSheetNum );
    }

    public void loadPlants()
    {
        List < String > plants = ssReader.getColumnAsArray( 0, 3 );
        List < String > urls = ssReader.getColumnAsArray( 1, 3 );
        List < String > countries = ssReader.getColumnAsArray( 2, 3 );
        for ( int i = 0; i < plants.size(); i++ )
        {
            if ( !plants.get( i ).isEmpty() )
            {
                try
                {
                    Plant p = new Plant();
                    p.setComment( plants.get( i ) );
                    p.setIconUrl( urls.get( i ) );
                    Country country = Country.findCountrysByNameEquals( countries.get( i ) ).getSingleResult();
                    p.setCountry( country );
                    p.persist();

                    this.loadPlantTranslations( p, i + 3 );

                }
                catch ( Exception e )
                {
                }
            }

        }
    }

    private void loadPlantTranslations( Plant p, int row )
    {
        List < String > plantsHeader = this.ssReader.getRowAsArray( 2 );

        for ( int i = 0; i < plantsHeader.size(); i++ )
        {
            try
            {
                Languages lang = Languages.findLanguagesesByNameEquals( plantsHeader.get( i ) ).getSingleResult();
                String translation = this.ssReader.getCellContent( row, i );

                PlantTranslation pl = new PlantTranslation();
                pl.setName( translation );
                pl.setPlant( p );
                pl.setLang( lang );
                pl.persist();

            }
            catch ( Exception e )
            {
            }
        }

    }
}
