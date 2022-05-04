package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class RiskAversion extends CoExp 
{

	public RiskAversion(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name = "Risk Aversion";
	}

	private boolean riskAversion(Proposition p,Proposition d,Node node)
	{
		if(this.inQual.isRule(p)) {
			PropositionSet state = this.localQual.constructMentalState(node, node.time);
			if(this.localQual.strongSatisfied(d, state)) {
				PropositionSet currentAct = this.scenario.getPropositionSet(node.time);
				PropositionSet.retrievePropositionSet(state,currentAct);
				if(this.inQual.noInfer(d, state)) {
					state.addProposition(p);
					for(Proposition d2 : this.desires.set) {
							for(String act : this.build.getActions()) {
								PropositionSet a = this.localQual.buildAction(act, node.time);
								if(!a.set.get(0).id.equals(currentAct.set.get(0).id)) {
									state = PropositionSet.union(state, a);
									if(this.localQual.weakSatisified(d2, state))
										return true;
									PropositionSet.retrievePropositionSet(state, a);
								}
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
			if(mcsSep.get(1).contains(p)) {
				for(Proposition d : this.desires.set) {
					if(this.riskAversion(p, d, node)) {
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
