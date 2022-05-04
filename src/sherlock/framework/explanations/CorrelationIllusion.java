package sherlock.framework.explanations;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class CorrelationIllusion extends CoExp 
{

	public CorrelationIllusion(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name = "Correlation Illusion";
	}
	
	private boolean correlationIllusion(Proposition p, Node node)
	{
		if(this.inQual.isKeep(p, node.time)) {
			PropositionSet obs = new PropositionSet(this.obss.getPropositionSet(node.time));
			PropositionSet.retrievePropositionSet(obs, node.mcs);
			for(Proposition q : obs.set) {
				if(this.generalQual.correlation(p, q, node)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean explain(Node node) {
		for(Proposition p : node.mcs.set) {
			if(this.correlationIllusion(p, node)) {
				System.out.println("Correlation Illusion : "+node.mcs);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean explain(Node node, Proposition p) {
		// TODO Auto-generated method stub
		return false;
	}

}
