package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class TruthIllusion extends CoExp 
{

	public TruthIllusion(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		this.name = "Truth Illusion";
	}

	private boolean truthIllusion(Proposition p,Node node)
	{
		if(this.inQual.inObs(p, node.time)) {
			ArrayList<PropositionSet> choicesC = this.localQual.choiceC(node, p);
			for(PropositionSet c : choicesC) {
				PropositionSet alt = this.localQual.getAlternative(node.mcs, c);
				for(Proposition q : alt.set) {
					if(this.inQual.inObs(q, node.time)) {
						if(this.generalQual.occureMore(q, p, node)) {
							return true;
						}
					}
				}
			}
		}
		return false;	
	}
	@Override
	public boolean explain(Node node) {
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		for(Proposition p : node.mcs.set) {
			if(mcsSep.get(0).contains(p)) {
				if(this.truthIllusion(p, node)) {
					return true;
				}
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
