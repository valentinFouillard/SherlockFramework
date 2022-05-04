package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class ControlIllusion extends CoExp {

	public ControlIllusion(SMTBuild build, SMT smt) 
	{
		super(build, smt);
		this.name = "Control Illusion";
	}

	
	public boolean controlIllusion(Proposition p,Proposition d, Node node)
	{
		if(this.inQual.isRule(p)) {
			//PropositionSet desires = this.desires;
			//for(Proposition desire : desires.set) {
				//if(this.generalQual.sameDesire(d, desire)) {
					if(this.inQual.notSatisfied(d,this.localQual.constructMentalState(node, node.time))) {
						if(this.generalQual.succesAction(d, node)) {
							return true;
						}
					//}
				//}
			}
		}
		return false;
	}
	
	/*public boolean controlIllusion(Proposition p, Node node, Proposition desire)
	{
		if(pCheck.illogisme(p)) {
			if(node.parent!=null) {
				Node next = node;
				Node current = node.parent;
				while(current.parent!=null){
					if(current.mcs.containsId(p.id.split("_")[0],p.instance)) {
						PropositionSet desireNotCurrent = pCheck.getDesires(current, false);
						String idDesire = desire.id.split("_")[0];
						if(desireNotCurrent.containsId(idDesire,desire.instance)) {
							PropositionSet desireNext = pCheck.getDesires(next, true);
							if(desireNext.containsId(idDesire,desire.instance)) {
								return true;
							}
						}
					}
					 next = current;
					 current = current.parent;
					
					 
				}
			}
			
		}
		return false;
	}*/
	@Override
	public boolean explain(Node node) {
		ArrayList<PropositionSet> mcsSep = this.localQual.getMCSSepare(node);
		for(Proposition p : node.mcs.set) {
			if(mcsSep.get(1).contains(p)) {
				for(Proposition desire : this.desires.set) {
					if(this.controlIllusion(p, desire, node)) {
						return true;
					}
				}
			}
		}
		return false;
		
		
		/*PropositionSet desires = pCheck.getDesires(node, false);
		for(Proposition p : node.mcs.set) {
			for(Proposition desire : desires.set) {
				if(this.controlIllusion(p, node, desire)) {
					System.out.println("Illusion Controle :" + node.mcs);
					return true;
				}
	
			}
		}
		return false;*/
	}

	@Override
	public boolean explain(Node node, Proposition p) {
		// TODO Auto-generated method stub
		return false;
	}

}
