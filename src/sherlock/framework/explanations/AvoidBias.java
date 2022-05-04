package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class AvoidBias extends CoExp {

	
	public AvoidBias(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name = "Avoid";
	}
	
	public boolean isAvoid(Proposition p,Proposition desire,Node node)
	{
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		if(this.localQual.inCoherence(p, mcsSep)) {
			return this.avoidC(p, desire, node);
		}
		else 
			return this.avoidD(p, desire, node);
	}
	
	public boolean avoidC(Proposition p,Proposition desire,Node node)
	{
		if(this.inQual.inObs(p, node.time)) {
			ArrayList<PropositionSet> choices = this.localQual.choiceC(node, p);
			for(PropositionSet c : choices) {
				for(Proposition q : c.set) {
					if(this.inQual.inObs(q, node.time)) {
						PropositionSet state = this.localQual.constructMentalState(node, node.time);
						PropositionSet.retrievePropositionSet(state,this.scenario.getPropositionSet(node.time));;
					    if(this.localQual.aversionNeg(node.mcs, desire, c, state))
					    	return true;
					}
				}
			}
		}
		
	    return false;
	}
	
	public boolean avoidD(Proposition p,Proposition desire,Node node)
	{
		if(this.inQual.inObs(p, node.time)) {
			PropositionSet state = this.localQual.constructMentalState(node, node.time);
			PropositionSet.retrievePropositionSet(state,this.scenario.getPropositionSet(node.time));
			Proposition pTemp = new Proposition(p.id,!p.instance);
			state.addProposition(pTemp);
			PropositionSet temp = new PropositionSet();
			temp.addProposition(pTemp);
			return this.localQual.aversionNeg(node.mcs, desire,temp, state);
			
		}
		return false;
	}

	
	
	@Override
	public boolean explain(Node node) {
		//PropositionSet notDesire = pCheck.getDesiresNoInfer(node, false);
		//notDesire = PropositionSet.union(notDesire, pCheck.getDesires(node, true));
		for(Proposition p : node.mcs.set) {
			for(Proposition d : this.desires.set) {
				if(this.isAvoid(p, d, node)) {//this.isAvoid(p, node, d)
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
