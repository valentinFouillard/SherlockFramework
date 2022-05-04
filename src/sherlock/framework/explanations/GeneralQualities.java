package sherlock.framework.explanations;
import java.util.ArrayList;

import sherlock.framework.Node;
import sherlock.framework.Proposition;
import sherlock.framework.PropositionSet;
import sherlock.framework.SMT;
import sherlock.framework.SMTBuild;

public class GeneralQualities extends Qualities
{
	private LocalQualities local;
	private IntraQualities intra;
	
	public GeneralQualities(SMT smt, SMTBuild build) 
	{
		super(smt, build);
		this.intra = new IntraQualities(smt,build);
		this.local = new LocalQualities(smt,build);
		
	}
	
	
	public boolean occureMore(Proposition p,Proposition q , Node node)
	{
		return occure(p,node) > occure(q,node);
	}
	
	private int occure(Proposition p,Node node)
	{
		int time = Integer.parseInt(p.id.split("_")[2]);
		if(time== node.time) 
			return this.occureInertia(p, node);
		else 
			return this.occureNoInertia(p, node);
		
	}
	
	private int occureInertia(Proposition p, Node node)
	{
		int count = 0;
		String pId = p.id.split("_")[1];
		int i = node.time-1;
		boolean hasInertia = false;
		do{
			PropositionSet observ = this.obss.getPropositionSet(i);
			for(Proposition p2 : observ.set) {
				if(p2.id.split("_")[1].equals(pId) && p2.instance == p.instance) {
					count+=1;
					hasInertia = true;
				}
			}
			i--;
		}while(i>=0 && hasInertia);
		return count;
	}
	
	private int occureNoInertia(Proposition p,Node node)
	{
		int count = 0;
		for(int i=node.time-1;i>=0;i--) {
			PropositionSet obs = this.obss.getPropositionSet(i);
			for(Proposition p2 : obs.set) {
				if(p == p2) {
					count+=1;
				}
			}
		}
		return count;
	}
	
	
	public boolean correlation(Proposition p,Proposition q,Node node)
	{
		Node current = node.getParent();
		String idP = p.id.split("_")[1];
		String idQ = q.id.split("_")[1];
		int timeP = Integer.parseInt(p.id.split("_")[2]);
		int timeQ = Integer.parseInt(p.id.split("_")[2]);
		int interval = timeP - timeQ;
		boolean dif = false;
		if(p.instance!=q.instance)
			dif = true;
		while(current.time>=0) {
			PropositionSet obs = new PropositionSet(this.obss.getPropositionSet(current.time));
			PropositionSet.retrievePropositionSet(obs, current.mcs);
			
			ArrayList<Proposition> p2S = new ArrayList<>();
			ArrayList<Proposition> q2S = new ArrayList<>();
	
			for(Proposition prop : obs.set) {
				if(prop.id.split("_")[1].equals(idP)) {
					
					p2S.add(prop);
				}
				if(prop.id.split("_")[1].equals(idQ)) {
					
					q2S.add(prop);
				}
			}
			
			if(!p2S.isEmpty()&&!q2S.isEmpty()) {
				for(Proposition p2 : p2S) {
					for(Proposition q2 : q2S) {
						if(this.haveCorrelation(p2, q2, interval, dif))
							return true;
					}
				}
				return false;
			}
			current = current.getParent();
		}
		return false;
	}
	
	private boolean haveCorrelation(Proposition p,Proposition q,int interval,boolean dif)
	{
		int timeP = Integer.parseInt(p.id.split("_")[2]);
		int timeQ = Integer.parseInt(p.id.split("_")[2]);
		if(!dif)
			return timeP-timeQ == interval && p.instance == q.instance;
		else
			return timeP-timeQ == interval && p.instance != q.instance;
	}
	
	
	public boolean sameDesire(Proposition d1,Proposition d2)
	{
		String idD1 = d1.id.split("_")[0];
		String idD2 = d2.id.split("_")[0];
		return idD1.equals(idD2);
	}
	
	public boolean sameAction(int time1, int time2)
	{
		return this.scenario.getPropositionSet(time1).set.get(0).id.split("_")[0].equals(this.scenario.getPropositionSet(time2).set.get(0).id.split("_")[0]);
	}
	
	public boolean sameProp(Proposition p,Proposition q) {
		return p.id.split("_")[1].equals(q.id.split("_")[1]);
	}
	
	private boolean failureInertia(Proposition d,Node node)
	{
		Proposition prevD = this.changeTime(d, node.time-1);
		Proposition nextD = this.changeTime(d, node.time);
		if(this.intra.satisfied(prevD, this.local.constructMentalState(node.parent, node.parent.time))) {
			if(this.intra.notSatisfied(nextD, this.local.constructMentalState(node, node.time))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean failureRevision(Proposition d,Node node)
	{
		if(this.intra.satisfied(d, this.local.constructMentalState(node.parent, node.parent.time))) {
			if(this.intra.notSatisfied(d, this.local.constructMentalState(node, node.time))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean failureAction(Proposition d,Node node)
	{
		if(node.time>1) {
			Node current = node.parent;
			while(current.time>0) {
				if(this.failureInertia(d, current) || this.failureRevision(d, current)) {
					if(this.sameAction(current.time-1, node.time)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	private boolean succesInertia(Proposition d,Node node)
	{
		Proposition prevD = this.changeTime(d, node.time-1);
		Proposition nextD = this.changeTime(d, node.time);
		if(this.intra.notSatisfied(prevD, this.local.constructMentalState(node.parent, node.parent.time))) {
			if(this.intra.satisfied(nextD, this.local.constructMentalState(node, node.time))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean succesRevision(Proposition d,Node node)
	{
		if(this.intra.notSatisfied(d, this.local.constructMentalState(node.parent, node.parent.time))) {
			if(this.intra.satisfied(d, this.local.constructMentalState(node, node.time))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean succesAction(Proposition d,Node node)
	{

		if(node.time>1) {
			Node current = node.parent;
			while(current.time>0) {
				if(this.succesInertia(d, current) || this.succesRevision(d, current)) {
					if(this.sameAction(current.time, node.time)) {
						
						return true;
					}
				}
				current = current.parent;
			}
		}
		return false;
	}
	
	private Proposition changeTime(Proposition d, int time)
	{
		String[] split = d.id.split("_");
		String newId  = "";
		for(int i=0;i<split.length;i++) {
			if(i==1) 
				newId+=Integer.toString(time);
			else
				newId+=split[i];
			if(i+1<split.length)
				newId+="_";
		}
		return new Proposition(newId,d.instance);
	}
	
}
