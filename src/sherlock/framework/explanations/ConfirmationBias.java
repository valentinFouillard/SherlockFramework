package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class ConfirmationBias extends CoExp
{

	public ConfirmationBias(SMTBuild build, SMT smt) {
		super(build, smt);
		// TODO Auto-generated constructor stub
		this.name="confirmation";
	}
	
	public boolean isConfirmation(Proposition p,Node node)
	{
		
		IntraQualities inQuali = new IntraQualities(smt,build);
		LocalQualities localQuali = new LocalQualities(smt,build);
		if(inQuali.inObs(p, node.time) || inQuali.isRule(p)) {
			ArrayList<PropositionSet> choicesC = localQuali.choiceC(node, p);
			//System.out.println(choicesC);
			if(localQuali.lessCoslty(p, node.mcs, choicesC)) {
				for(PropositionSet c : choicesC) {
					PropositionSet alternative =  localQuali.getAlternative(node.mcs, c);
					if(alternative.set.size()>1) {
						boolean conserv = false;
						boolean inObs = false;
						ArrayList<String> idProp = new ArrayList<>();
						for(Proposition a : alternative.set) {
							String ida = a.id.split("_")[1];
							if(!conserv && localQuali.conservatisme(node, p, a) && !idProp.contains(ida)) {
								conserv = true;
								idProp.add(ida);
							}else if(!inObs && inQuali.inObs(a, node.time)&& !idProp.contains(ida)) {
								inObs = true;
								idProp.add(ida);
							}
						}
						if(conserv && inObs)
							return true;
					}
				}
			}
		}
		return false;
		
	}
	
	
	
	

	@Override
	public boolean explain(Node node) 
	{
		//System.out.println(node.mcs);
		for(Proposition p : node.mcs.set) {
			if(this.isConfirmation(p, node)) {
				return true;
			}
		}
		
		return false;
	}
		
	
	@Override
	public boolean explain(Node node, Proposition p) {
		return false;
	}
	
}
