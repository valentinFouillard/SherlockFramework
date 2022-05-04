package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class FacilitationBias extends CoExp {
	
	
	
	public FacilitationBias(SMTBuild build, SMT smt) {
		super(build, smt);
		this.name = "Facilitation";
		// TODO Auto-generated constructor stub
	}
	
	public boolean facilitation(Proposition p,Proposition desire ,Node node)
	{
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		
		if(this.localQual.inCoherence(p, mcsSep)) 
			return facilitationC(p,desire,node);
		else
			return facilitationD(p,desire,node);
	}
	public boolean facilitationC(Proposition p,Proposition desire,Node node)
	{
		if(this.inQual.inObs(p, node.time)) {
			ArrayList<PropositionSet> choice = this.localQual.choiceC(node, p);
			for(PropositionSet c : choice) {
				for(Proposition q : c.set) {
					if(this.inQual.inObs(q, node.time)) {
						PropositionSet state = this.localQual.constructMentalState(node, node.time);
						PropositionSet.retrievePropositionSet(state,scenario.getPropositionSet(node.time));
						if(this.localQual.appealNeg(node.mcs, desire, c, state)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private Proposition constructNotObs(Proposition p)
	{
		String[] split = p.id.split("_");
		String p2ID = "ObsNot_";
		for(int i=1;i<split.length;i++) {
			p2ID+=split[i];
			if(i+1<split.length)
				p2ID+="_";
		}
		return new Proposition(p2ID,true);
	}
	            
	public boolean facilitationD(Proposition p, Proposition desire, Node node)
	{
		if(this.inQual.inObs(p, node.time)) {
			PropositionSet state = this.localQual.constructMentalState(node, node.time);
			PropositionSet.retrievePropositionSet(state,this.scenario.getPropositionSet(node.time));
			Proposition pTemp = this.constructNotObs(p);
			state.addProposition(pTemp);
			PropositionSet temp = new PropositionSet();
			temp.addProposition(pTemp);
			return this.localQual.appealNeg(node.mcs, desire,temp, state);
			
		}
		return false;
	}
	

	/**
	 * 
	 * @param node
	 * @param time
	 * @param valence => true = positive, false = negative
	 * @return
	 */
	@Override
	public boolean explain(Node node) 
	{
		//PropositionSet notDesire = pCheck.getDesires(node, false);
		for(Proposition p : node.mcs.set) {
			for(Proposition d : this.desires.set) {
				if(this.facilitation(p, d, node)) {//this.facilitation(p, node, d)
					return true;
				}
					
			}
		}
		return false;
			
	}

	@Override
	public boolean explain(Node node, Proposition p) 
	{
		return false;
	}
	
	

}
