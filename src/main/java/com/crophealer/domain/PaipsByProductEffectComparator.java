package com.crophealer.domain;

import java.util.Comparator;


public class PaipsByProductEffectComparator implements Comparator<ProblemAIProduct> {

	/* Not comparable values will push o2 always to end */
	@Override
	public int compare(ProblemAIProduct o1, ProblemAIProduct o2) {
		try {
			Double d1 = Double.parseDouble(o1.getProductEffect());
			int ef1 = d1.intValue();

			Double d2 = Double.parseDouble(o2.getProductEffect());
			int ef2 = d2.intValue();

			if (ef1 < ef2) return 1;
			if (ef1 == ef2) return 0;		
			return -1;
		} catch (Exception e) {
			System.out.println("PaipsByProductEffectComparator: Could not compare " + o1.getProductEffect() + " to " + o2.getProductEffect());
			return 1;
		}
	}
}