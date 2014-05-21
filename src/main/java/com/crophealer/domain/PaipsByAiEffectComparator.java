package com.crophealer.domain;

import java.util.Comparator;


public class PaipsByAiEffectComparator implements Comparator<ProblemAIProduct> {

	@Override
	public int compare(ProblemAIProduct o1, ProblemAIProduct o2) {
		if (o1.getAiEffect() < o2.getAiEffect()) return 1;
		if (o1.getAiEffect() == o2.getAiEffect()) return 0;		
		return -1;
	}
}