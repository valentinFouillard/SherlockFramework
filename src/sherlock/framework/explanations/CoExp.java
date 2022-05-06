package sherlock.framework.explanations;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;
import sherlock.framework.SetTime;

public abstract class CoExp 
{
	public static String RULEID="R";
	public static String OBSID = "Obs";
	protected SetTime scenario,obss;
	protected PropositionSet belief,rules,actions,coherence,facts;
	protected PropositionSet desires;
	protected SMT smt;
	protected SMTBuild build;
	protected String name;
	protected IntraQualities inQual;
	protected LocalQualities localQual;
	protected GeneralQualities generalQual;
	
	
	public CoExp(SMTBuild build,SMT smt) 
	{
		this.obss = build.getObservations();
		this.scenario = build.getScenario();
		this.belief = build.getBeliefs();
		this.rules = build.getRules();
		this.coherence = build.getCoherence();
		this.facts = build.getFacts();
		this.desires = build.generateDesires();
		this.smt = smt;
		this.build = build;
		this.inQual = new IntraQualities(smt,build);
		this.localQual = new LocalQualities(smt,build);
		this.generalQual = new GeneralQualities(smt,build);
		
	}
	
	/**
	 * Return true if an explanation is found with the class coexp for the node   
	 * @param node
	 * @return
	 */
	public abstract boolean explain(Node node);
	
	public abstract boolean explain(Node node,Proposition p);
	
	public String getName() {return this.name;}
}


