package com.crophealer.domain;

import java.util.Comparator;


public class PaipsByAiEffectComparator implements Comparator<ProblemAIProduct> {

	@Override
	public int compare(ProblemAIProduct o1, ProblemAIProduct o2) {
		Double d1 = Double.parseDouble(o1.getAiEffect());
		int ef1 = d1.intValue();
		
		Double d2 = Double.parseDouble(o2.getAiEffect());
		int ef2 = d2.intValue();
		
		if (ef1 < ef2) return 1;
		if (ef1 == ef2) return 0;		
		return -1;
	}
}