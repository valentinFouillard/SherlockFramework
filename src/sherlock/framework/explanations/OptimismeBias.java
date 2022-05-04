package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class OptimismeBias extends CoExp {

	public OptimismeBias(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name="Optimisme";
	}
	
	
	public boolean optimisme(Proposition p,Proposition desire,Node node)
	{
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		if(this.localQual.inCoherence(p, mcsSep))
			return this.optimismeC(p, desire, node);
		else
			return this.optimismeD(p, desire, node);
	}
	
	public boolean optimismeC(Proposition p,Proposition desire,Node node)
	{
		if(this.inQual.isRule(p)) {
			ArrayList<PropositionSet> choices = this.localQual.choiceC(node, p);
			for(PropositionSet c : choices) {
				PropositionSet state = this.localQual.constructMentalState(node, node.time);
				PropositionSet.retrievePropositionSet(this.scenario.getPropositionSet(node.time), state);
				if(this.localQual.aversionNeg(node.mcs, desire, c, state))
					return true;
			}
		}
		return false;
	}
	
	public boolean optimismeD(Proposition p,Proposition desire,Node node)
	{
		if(this.inQual.isRule(p)) {
			ArrayList<PropositionSet> choices = this.localQual.choiceD(node, p);
			for(PropositionSet c : choices) {
				PropositionSet state = this.localQual.constructMentalState(node, node.time);
				PropositionSet.retrievePropositionSet(this.scenario.getPropositionSet(node.time), state);
				if(this.localQual.aversionNeg(node.mcs, desire, c, state))
					return true;
			}
		}
		return false;
	}
	

	@Override
	public boolean explain(Node node) {
		// TODO Auto-generated method stub
		for(Proposition p : node.mcs.set) {
			for(Proposition d: this.desires.set) {
				if(this.optimisme(p, d, node)) {//this.optimisme(p, node, d)
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
