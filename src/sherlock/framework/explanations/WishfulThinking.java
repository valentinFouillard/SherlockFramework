package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class WishfulThinking extends CoExp 
{

	public WishfulThinking(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		this.name="Wishful Thinking";
	
	}

	
	
	public boolean wishfulThinking(Proposition p,Proposition d,Node node)
	{
		if(this.inQual.isKeep(p, node.time)) {
			PropositionSet state = this.localQual.constructMentalState(node, node.time);
			PropositionSet act = this.scenario.getPropositionSet(node.time);
			PropositionSet notAct = new PropositionSet(act);
			notAct.set.get(0).instance = false;
			return this.localQual.appealPos(notAct, d, act, state);
		}
		return false;
	}
	@Override
	public boolean explain(Node node) {
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		for(Proposition p : node.mcs.set) {
			if(mcsSep.get(1).contains(p)) {
				for(Proposition d : this.desires.set) {
					if(this.wishfulThinking(p, d, node)) {
						return true;
					}
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
