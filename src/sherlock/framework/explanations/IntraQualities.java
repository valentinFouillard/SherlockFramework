package sherlock.framework.explanations;

import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public  class IntraQualities extends Qualities
{
	
	public IntraQualities(SMT smt,SMTBuild build) 
	{
	
		super(smt,build);

	}
	
	public boolean notSatisfied(Proposition d, PropositionSet state)
	{
		PropositionSet stateP = new PropositionSet(state);
		Proposition dP = new Proposition(d.id,d.instance);
		stateP.removeProposition(d);
		stateP.addProposition(dP);
		return !this.smt.requestSat(stateP);
	}
	
	public boolean satisfied(Proposition d, PropositionSet state)
	{
		PropositionSet stateP = new PropositionSet(state);
		Proposition dP = new Proposition(d.id,!d.instance);
		stateP.removeProposition(d);
		stateP.addProposition(dP);
		
		return !this.smt.requestSat(stateP);
	}
	
	public boolean noInfer(Proposition d,PropositionSet state)
	{
		return !this.notSatisfied(d, state) && !this.satisfied(d, state);
	}
	
	public boolean inObs(Proposition p,int time)
	{
		return this.obss.getPropositionSet(time).contains(p);
	}
	
	public boolean isRule(Proposition p)
	{
		return this.rules.contains(p);
	}
	
	public boolean isRuleStrong(Proposition p)
	{
		if(this.isRule(p)){
			return p.isRuleStrong;
		}
		return false;
	}
	
	public boolean isRuleWeak(Proposition p)
	{
		if(this.isRule(p)){
			return p.isRuleWeak;
		}
		return false;
	}
	
	public boolean isKeep(Proposition p,int time)
	{
		String[] split = p.id.split("_");
		if(split[0].equals("keep")) {
			if(Integer.parseInt(split[2])==time)
				return true;
		}
		return false;
		
	}
	
	
	  
}
