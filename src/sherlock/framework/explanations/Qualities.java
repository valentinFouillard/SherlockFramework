package sherlock.framework.explanations;

import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;
import sherlock.framework.SetTime;

public abstract class Qualities 
{
	protected SMT smt;
	protected SetTime scenario,obss;
	protected PropositionSet belief,rules,actions,coherence,facts,desires;
	
	public Qualities(SMT smt,SMTBuild build) 
	{
		this.obss = build.getObservations();
		this.scenario = build.getScenario();
		this.belief = build.getBeliefs();
		this.rules = build.getRules();
		this.coherence = build.getCoherence();
		this.facts = build.getFacts();
		this.smt = smt;
		this.desires = build.generateDesires();

	}
	
	
}
