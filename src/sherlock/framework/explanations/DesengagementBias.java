package sherlock.framework.explanations;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class DesengagementBias extends CoExp {

	public DesengagementBias(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		this.name = "Desengagment";
	}

	
	
	public boolean desengagement(Proposition p,Node node)
	{
		FacilitationBias facilBias = new FacilitationBias(build,smt);
		for(Proposition d : this.desires.set) {
			if(facilBias.facilitation(p, d, node)){
				for(Proposition d2 : this.desires.set) {
					if(this.generalQual.sameDesire(d, d2)) {
						Node parent = node.parent;
						for(Proposition p2 : parent.mcs.set) {
							if(facilBias.facilitation(p2, d2, parent)) {
								return true;
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
		
		for(Proposition p : node.mcs.set) {
			if(this.desengagement(p, node)) {
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
