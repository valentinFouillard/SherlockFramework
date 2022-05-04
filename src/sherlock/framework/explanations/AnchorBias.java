package sherlock.framework.explanations;

import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;



public class AnchorBias extends CoExp {

	
	
	public AnchorBias(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		this.name = "Anchor Bias";
	}

	public boolean anchor(Proposition p,Node node)
	{
		ArrayList<PropositionSet> choicesC = this.localQual.choiceC(node, p);
		for(PropositionSet c : choicesC) {
			for(Proposition q : c.set) {
				if(this.inQual.inObs(q, node.time)) {
					if(this.localQual.conservatisme(node, p, q)) {
						return true;
					}	
				}
			}
		}
		return false;
	}
	@Override
	public boolean explain(Node node) {
		for(Proposition p : node.mcs.set) {
			if(this.anchor(p, node)) {
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
