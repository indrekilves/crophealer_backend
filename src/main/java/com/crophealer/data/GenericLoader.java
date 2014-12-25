package com.crophealer.data;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class GenericLoader
{
    protected SpreadSheetReader ssReader;

    protected Integer activeSheetNum = 0;

    protected FileHandler fileTxt;

    protected static Logger LOGGER = Logger.getLogger( GenericLoader.class.getName() );

    public GenericLoader()
    {
    }

    public GenericLoader( SpreadSheetReader ssReader )
    {
        this.ssReader = ssReader;
        this.ssReader.setActiveSheetNum( this.getActiveSheetNum() );
        try
        {

            DataLoaderLogger.setup();

        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        LOGGER.info( "Kapsauss" );
        LOGGER.warning( "Kapsauss" );
    }

    public void setSSReader( SpreadSheetReader ssr )
    {
        this.ssReader = ssr;
    }

    public void setActiveSheetNum( Integer actvieSheetNum )
    {
        this.activeSheetNum = actvieSheetNum;
        this.ssReader.setActiveSheetNum( actvieSheetNum );
    }

    public Integer getActiveSheetNum()
    {
        return this.activeSheetNum;
    }

}
