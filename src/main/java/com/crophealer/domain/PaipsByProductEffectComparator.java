package com.crophealer.domain;

import java.util.Comparator;

public class PaipsByProductEffectComparator implements Comparator < ProblemAIProduct >
{

    /* Not comparable values will push o2 always to end */
    @Override
    public int compare( ProblemAIProduct o1, ProblemAIProduct o2 )
    {
        int ef1;
        int ef2;

        try
        {
            Double d1 = Double.parseDouble( o1.getProductEffect() );
            ef1 = d1.intValue();
        }
        catch ( Exception e )
        {
            ef1 = Integer.MIN_VALUE;
        }

        try
        {
            Double d2 = Double.parseDouble( o2.getProductEffect() );
            ef2 = d2.intValue();
        }
        catch ( Exception e )
        {
            ef2 = Integer.MIN_VALUE;
        }

        if ( ef1 < ef2 )
            return 1;
        if ( ef1 == ef2 )
            return 0;
        return -1;

    }
}