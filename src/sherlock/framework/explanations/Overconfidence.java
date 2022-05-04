package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class Overconfidence extends CoExp {

	public Overconfidence(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name = "overconfidence";
	}

	
	public boolean overconfidenceC(Proposition p,Proposition desire,Node node)
	{
		ArrayList<PropositionSet> choices = this.localQual.choiceC(node, p);
		for(PropositionSet c : choices) {
			if(this.localQual.weakOverStrong(p, c))
				return true;
		}
		return false;
	}
	
	public boolean overconfidenceD(Proposition p,Proposition desire,Node node)
	{
		ArrayList<PropositionSet> choices = this.localQual.choiceD(node, p);
		for(PropositionSet c : choices) {
			if(this.localQual.weakOverStrong(p, c))
				return true;
		}
		return false;
	}
	
	public boolean overconfidence(Proposition p,Proposition desire,Node node)
	{
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		if(this.localQual.inCoherence(p, mcsSep))
			return this.overconfidenceC(p, desire, node);
		else
			return this.overconfidenceD(p, desire, node);
	}
	
	
	 
	
	@Override
	public boolean explain(Node node) {
		//PropositionSet desireNoInfer = pCheck.getDesiresNoInfer(node,false);
		for(Proposition p : node.mcs.set) {
			for(Proposition desire : this.desires.set) {
				if(this.overconfidence(p, desire, node)) {//this.overconCo(p, node, desire)
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
