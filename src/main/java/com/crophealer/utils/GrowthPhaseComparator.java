package com.crophealer.utils;

import java.util.Comparator;

import com.crophealer.domain.GrowthPhase;

public class GrowthPhaseComparator implements Comparator < GrowthPhase >
{

    @Override
    public int compare( GrowthPhase gp1, GrowthPhase gp2 )
    {
        return gp1.getComment().compareTo( gp2.getComment() );
        // return o1.getStartDate().compareTo(o2.getStartDate());
    }

}
