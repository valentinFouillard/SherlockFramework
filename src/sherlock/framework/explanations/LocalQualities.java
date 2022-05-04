package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class LocalQualities extends Qualities 
{
	private IntraQualities intQuali;
	
	
	public LocalQualities(SMT smt,SMTBuild build) 
	{
		super(smt,build);
		this.intQuali = new IntraQualities(smt,build);
		
	}
	
	public boolean aversionNeg(PropositionSet m,Proposition d,PropositionSet choice,PropositionSet state)
	{
		if(this.intQuali.satisfied(d, state) || this.intQuali.noInfer(d, state)) {
			PropositionSet stateP = new PropositionSet(state);
			PropositionSet.retrievePropositionSet(stateP, choice);
			stateP = PropositionSet.union(stateP, m);
			return this.intQuali.notSatisfied(d, stateP);
		}
		return false;
	}
	
	public boolean aversionPos(PropositionSet m,Proposition d,PropositionSet choice,PropositionSet state)
	{
		if(this.intQuali.notSatisfied(d, state) || this.intQuali.noInfer(d, state)) {
			PropositionSet stateP = new PropositionSet(state);
			PropositionSet.retrievePropositionSet(stateP, choice);
			stateP = PropositionSet.union(stateP, m);
			return this.intQuali.satisfied(d, stateP);
		}
		return false;
	}
	
	public boolean appealPos(PropositionSet m,Proposition d,PropositionSet choice,PropositionSet state)
	{
		if(this.intQuali.satisfied(d, state)) {
			PropositionSet stateP = new PropositionSet(state);
			PropositionSet.retrievePropositionSet(stateP, choice);
			stateP = PropositionSet.union(stateP, m);
			return (this.intQuali.notSatisfied(d, stateP) || this.intQuali.noInfer(d, stateP));
		}
		return false;
	}
	
	public boolean appealNeg(PropositionSet m,Proposition d,PropositionSet choice,PropositionSet state)
	{
		if(this.intQuali.notSatisfied(d, state)) {
			PropositionSet stateP = new PropositionSet(state);
			PropositionSet.retrievePropositionSet(stateP, choice);
			stateP = PropositionSet.union(stateP, m);
			return (this.intQuali.satisfied(d, stateP) || this.intQuali.noInfer(d, stateP));
		}
		return false;
	}
	
	public boolean lessCoslty(Proposition p,PropositionSet mcs,ArrayList<PropositionSet> choices)
	{
		for(PropositionSet choice : choices) {
			PropositionSet interSec = PropositionSet.intersec(choice, mcs);
			PropositionSet choiceTemp = new PropositionSet(choice);
			PropositionSet.retrievePropositionSet(choiceTemp, interSec);
			if(choiceTemp.set.size()>1)
				return true;
		}
		return false;
	}
	
	public boolean conservatisme(Node node,Proposition p,Proposition q)
	{
		return this.time(q) < this.time(p);
	}
	
	private int time (Proposition p)
	{
		if(p.id.contains("Obs") || p.id.contains("keep"))
			return Integer.parseInt(p.id.split("_")[2]);
		else
			return Integer.parseInt(p.id.split("_")[1]);
	}
	
	public boolean avoidAction(PropositionSet m,Proposition a,PropositionSet choice,PropositionSet state)
	{
		if(this.intQuali.noInfer(a, state)) {
			PropositionSet stateP = new PropositionSet(state);
			PropositionSet.retrievePropositionSet(stateP, choice);
			stateP = PropositionSet.union(stateP, m);
			return this.intQuali.satisfied(a, stateP);
		}
		return false;
	}
	
	
	public boolean weakOverStrong(Proposition p ,PropositionSet choice)
	{
		
		if(this.intQuali.isRuleStrong(p)) {
			for(Proposition c : choice.set) {
				if(this.intQuali.isRuleWeak(c))
					return true;
			}
		}
		return false;
	}
	
	public boolean strongOverWeak(Proposition p ,PropositionSet choice)
	{
		if(this.intQuali.isRuleWeak(p)) {
			for(Proposition c : choice.set) {
				if(this.intQuali.isRuleStrong(c))
					return true;
			}
		}
		return false;
	}
	
	
	protected PropositionSet constructMentalState(Node node,int time)
	{
		ArrayList<PropositionSet> mcses = node.getMCSFromNode();
		PropositionSet newBelief = new PropositionSet(this.belief);
		if(time>0) {
			newBelief = PropositionSet.union(newBelief,node.keep);
			newBelief = PropositionSet.union(newBelief, node.getKeepClauseFromNode());
		}
		newBelief = PropositionSet.union(newBelief,this.rules);
		for(int i =0;i<mcses.size();i++) {
			PropositionSet mcs = mcses.get(i);
			newBelief = PropositionSet.union(newBelief, obss.getPropositionSet(i));
			newBelief = PropositionSet.union(newBelief, scenario.getPropositionSet(i));
			PropositionSet.retrievePropositionSet(newBelief, mcs);
		}
		newBelief = PropositionSet.union(newBelief, coherence);
		
		return newBelief;
	}
	
	private ArrayList<PropositionSet> getChoices(Proposition p,PropositionSet m,ArrayList<PropositionSet> choices)
	{
		ArrayList<PropositionSet> result = new ArrayList<>();
		PropositionSet marked = new PropositionSet();
		for(PropositionSet mcs : choices) {
			if(!m.equals(mcs)) {
				if(mcs.contains(p)) {
					for(Proposition p2 : mcs.set) {
						if(p!=p2) {
							marked.addProposition(p2);
						}
					}
				}
			}
		}
		
		for(PropositionSet mcs : choices){
			if(!m.equals(mcs)) {
				PropositionSet inter = PropositionSet.intersec(mcs, marked);
				if(inter.IsEmpty()) {
					result.add(mcs);
					
				}
				
			}
		}
		return result;
	}
	
	public ArrayList<PropositionSet> getMCSSepare(Node node)
	{
		ArrayList<PropositionSet> result = new ArrayList<>();
		PropositionSet state = this.constructMentalState(node, node.time);
		PropositionSet.retrievePropositionSet(state, this.scenario.getPropositionSet(node.time));
		PropositionSet blocked = new PropositionSet();
		blocked = PropositionSet.union(state, blocked);
		ArrayList<PropositionSet> mcses = this.smt.getMces(blocked, node.mcs, node.time);
		PropositionSet intersec = new PropositionSet();
		if(!mcses.isEmpty()) {
			intersec = PropositionSet.intersec(mcses.get(0), node.mcs);
		}
		result.add(intersec);
		PropositionSet mcsTemp = new PropositionSet(node.mcs);
		PropositionSet.retrievePropositionSet(mcsTemp, intersec);
		result.add(mcsTemp);
		return result;
		
	}
	
	public boolean inCoherence(Proposition p,ArrayList<PropositionSet> mcs) 
	{
		return mcs.get(0).contains(p);
	}
	
	public boolean getDecision(Proposition p,ArrayList<PropositionSet> mcs)
	{
		return mcs.get(1).contains(p);
	}
	
	public ArrayList<PropositionSet> choiceC(Node node, Proposition p)
	{
		PropositionSet state = this.constructMentalState(node, node.time);
		PropositionSet.retrievePropositionSet(state, this.scenario.getPropositionSet(node.time));
		PropositionSet blocked = new PropositionSet();
		blocked = PropositionSet.union(scenario.getPropositionSetUnion(node.time-1), blocked);
		blocked =PropositionSet.union(this.coherence, blocked);
		state = PropositionSet.union(state, node.mcs);
		
		return this.getChoices(p, node.mcs, this.smt.getMces(blocked, state, node.time));
	}
	
	public ArrayList<PropositionSet> choiceD(Node node, Proposition p)
	{
		
		return this.getChoices(p, node.mcs,node.getChoices());
	}
	
	public PropositionSet getAlternative(PropositionSet mcs,PropositionSet choice)
	{
		PropositionSet result = new PropositionSet(choice);
		PropositionSet interSec = PropositionSet.intersec(mcs, choice);
		PropositionSet.retrievePropositionSet(result, interSec);
		return result;
	}
	
	public boolean strongSatisfied(Proposition d,PropositionSet state)
	{
		PropositionSet rulesWeak = new PropositionSet();
		for(Proposition p : state.set) {
			if(this.intQuali.isRuleWeak(p)) {
				rulesWeak.addProposition(p);
			}
		}
		PropositionSet stateTemp = new PropositionSet(state);
		PropositionSet.retrievePropositionSet(stateTemp, rulesWeak);
		stateTemp.removeProposition(d);
		return this.intQuali.satisfied(d, stateTemp);
	}
	
	public boolean weakSatisified(Proposition d,PropositionSet state)
	{
		return !this.strongSatisfied(d, state) && this.intQuali.satisfied(d, state);
	}
	
	public PropositionSet buildAction(String act,int time)
	{
		String actId = act+"_"+time;
		Proposition a = new Proposition(actId,true);
		Proposition ka = new Proposition("known_"+actId,true);
		PropositionSet result = new PropositionSet();
		result.addProposition(a);
		result.addProposition(ka);
		return result;
	}
	
}
