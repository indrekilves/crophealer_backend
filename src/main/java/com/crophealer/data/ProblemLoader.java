package com.crophealer.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.crophealer.domain.GrowthPhase;
import com.crophealer.domain.Problem;
import com.crophealer.domain.Reseller;

public class ProblemLoader extends GenericLoader
{
	protected Integer activeSheetNum = 0;
	private Integer latinNameColNum = 7;
	private Integer problemsStartRow = 5; 
	private Integer phaseColumn = 8;
	
	
	public ProblemLoader(SpreadSheetReader ssReader)
	{
		super(ssReader);
		this.setActiveSheetNum(this.activeSheetNum);
	}
	
	
	
	public void loadProblems()
	{
		List<String> latinNames = this.ssReader.getColumnAsArray(this.latinNameColNum, this.problemsStartRow);
		
		Problem problem = new Problem();
		
		for (int i = 0; i < latinNames.size(); i++) 
		{
			//Problem problem;
			String pLatinName = latinNames.get(i);
			
			if ( !pLatinName.isEmpty() )
			{
				problem = this.loadAndGetProblemByLatin(pLatinName);				
			}
			
			// this will go left-right on the row
			this.processProblemSector(problem, i);
			problem.persist();			
		}
	}
	
	
	
	private void processProblemSector(Problem problem, int row) 
	{
		if(problem.getLatinName().isEmpty())
			return;
		List<GrowthPhase> phases = this.getPhasesForProblem(problem, row);
		
		// get plant parts for problem (should add parts as well)
		// get symptoms for problem (add symptom with AI)
		// get 
		
	}
	
	
	private List<GrowthPhase> getPhasesForProblem(Problem problem, int row)
	{
		List<GrowthPhase> phaseList = new ArrayList<GrowthPhase>();
		String phaseCell = ssReader.getCellContent(row, phaseColumn);
		String[] phasePcs = phaseCell.split(",");

		for (String phaseStr : phasePcs) {
			if ( phaseStr.isEmpty() )
				continue;
			try
			{
				GrowthPhase phase = GrowthPhase.findGrowthPhasesByCommentEquals(phaseStr).getSingleResult();
				phaseList.add(phase);
			}
			catch(Exception e)
			{}

		}
		return phaseList;
	}



	private Problem loadAndGetProblemByLatin(String latinName)
	{
		TypedQuery<Problem> problemQ = Problem.findProblemsByLatinNameEquals(latinName);
		
		if (problemQ.getResultList().size() > 0)
		{
			return problemQ.getSingleResult();
		}
		else
		{
			Problem p = new Problem();
			p.setLatinName(latinName);
			p.persist();
			return p;
		}
	}
	
	
	
	
	
}
