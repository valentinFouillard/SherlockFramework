package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class LossAversion extends CoExp {

	public LossAversion(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		// TODO Auto-generated constructor stub
		this.name = "Loss Aversion";
	}
	
	public boolean lossAversionD(Proposition p,Proposition d,Node node)
	{
		if(this.inQual.isRule(p)) {
			PropositionSet act = this.scenario.getPropositionSet(node.time);
			PropositionSet notAct = new PropositionSet(act);
			notAct.set.get(0).instance = false;
			PropositionSet state = this.localQual.constructMentalState(node, node.time);
			state.addProposition(p);
			return this.localQual.aversionNeg(notAct, d,act,state);
		}
		return false;
	}
	
	
	
	public boolean lossAversionC(Proposition p,Proposition d,Node node)
	{
		PropositionSet actNode = this.scenario.getPropositionSet(node.time);
		if(this.inQual.isRule(p)) {
			for(String act : this.build.getActions()) {
				PropositionSet a = this.localQual.buildAction(act, node.time);
				if(a.set.get(0)!=actNode.set.get(0)) {
					PropositionSet state = this.localQual.constructMentalState(node, node.time);
					PropositionSet.retrievePropositionSet(state, actNode);
					ArrayList<PropositionSet> choiceC = this.localQual.choiceC(node, p);
					for(PropositionSet c : choiceC) {
						if(this.localQual.avoidAction(node.mcs, a.set.get(0), c, state)) {
							if(this.localQual.aversionNeg(node.mcs, d, c, state)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean lossAversion(Proposition p ,Proposition d, Node node)
	{
		if(this.scenario.getPropositionSet(node.time).IsEmpty())
			return false;
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		if(mcsSep.get(1).contains(p)) {
			if(this.lossAversionD(p, d, node))
				return true;
		}
		else {
			if(this.lossAversionC(p, d, node))
				return true;
		}
		return false;
	}

	@Override
	public boolean explain(Node node) {
		for(Proposition p : node.mcs.set) {
			for(Proposition d : this.desires.set) {
				if(this.lossAversion(p, d, node)) {
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
