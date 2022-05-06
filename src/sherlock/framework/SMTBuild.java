package sherlock.framework;
import java.util.ArrayList;

import sherlock.framework.parser.Factory;
import sherlock.framework.parser.FactoryBuild;

import sherlock.framework.parser.SMTVisitor;

/**
 * SMTBuild generate the java object for the logic proposition of the model and the SMT definitions
 *
 */
public class SMTBuild 
{
	private SMTVisitor<Object> smtVisitor;
	private String model;
	private PropositionSet facts;
	private SetTime scenario;
	private FactoryBuild factBuild;
	
	
	public SMTBuild(SMTVisitor<Object> smtVisitor)
	{
		this.smtVisitor = smtVisitor;
		this.factBuild = new FactoryBuild();
		this.facts = new PropositionSet();
		this.model=this.generateModel();
		this.scenario = null;
		
		
	}
	
	public SetTime getObservations()
	{
		return this.smtVisitor.observations;
	}
	
	public SetTime getScenario() 
	{
		if(this.scenario == null) {
			this.generateScenario();
		}
		return this.scenario;
	}
	
	/**
	 * Generate the definition of each action
	 */
	public void generateScenario()
	{
		this.scenario = new SetTime(this.smtVisitor.scenario.timeLimit);
		int time = 0;
		for(PropositionSet pSet : this.smtVisitor.scenario.getSet()) {
			PropositionSet temp = new PropositionSet();
			ArrayList<String> actions = new ArrayList<>();
			if(!pSet.IsEmpty()) {
				for(Proposition p : pSet.set) {
					if(!p.id.contains("known")) {
						temp.addProposition(p);
						temp.addProposition(new Proposition("known_"+p.id,true)); // each action of the scenario is known and true
						actions.add(p.id.split("_")[0]);
					}
					
				}
				//for(Proposition p : temp.set){
					//pSet.addProposition(p);
				//}
				for(String act : this.smtVisitor.actions) {
					if(!actions.contains(act)) {
						String name = act + "_"+time;
						temp.addProposition(new Proposition(name,false));
						temp.addProposition(new Proposition("known_"+name,true)); // the other are known but false
					}
				}
				
				this.scenario.addPropositionSet(temp, time);
			}
			
			time+=1;
		}
		
		//System.out.println(this.smtVisitor.scenario.getSet()[0]);
		//return this.smtVisitor.scenario;
	}
	
	public ArrayList<String> getActions()
	{
		return this.smtVisitor.actions;
	}
	public ArrayList<Proposition> getDesires()
	{
		return this.smtVisitor.desires;
	}
	
	/**
	 * Generate all the desires of the scenario
	 * @return
	 */
	public PropositionSet generateDesires()
	{
		PropositionSet allDesires = new PropositionSet();
		
		for(Proposition d : this.getDesires()) {
			for(int i=0;i<this.getScenario().timeLimit+1;i++) {
				allDesires.addProposition(new Proposition(d.id+"_"+i,d.instance)); // Each desire is true at each time step
			}
		}
		return allDesires;
	}
	
	public PropositionSet getBeliefs()
	{
		return this.smtVisitor.beliefs;
	}
	
	public PropositionSet getRules()
	{
		return this.smtVisitor.rules;
	}
	public PropositionSet getCoherence()
	{
		return this.smtVisitor.coherence;
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public PropositionSet getFacts()
	{
		return this.facts;
	}
	
	
	/**
	 * Generate the definition of the rules and proposition for the SMT solver
	 * @return
	 */
	private String generateModel()
	{
		for(Factory f : this.smtVisitor.getFactories()) {
			if(f.isRule()) {
				this.factBuild.addRulesFactory(f);
			}
			else {
				this.factBuild.addPropFactory(f);
			}
		}
		return this.factBuild.generate(this.smtVisitor.limitArgs);
		
	}
	

	
	
	/**
	 * Return all the keep clause for the time step
	 * @param time
	 * @param before
	 * @return
	 */
	public  PropositionSet allKeep(int time,Node before)
	{
		//return new PropositionSet();
		//return PropositionSet.union(this.getKeep(time, before), this.getKeepKnown(time, before));
		return this.getKeepV2(time, before);
	}
	
	private PropositionSet getKeepV2(int time,Node before)
	{
		PropositionSet keepProp = new PropositionSet();
		for(Proposition k : before.keep.set) 
		{
			Proposition newKeep = null;
			if(k.id.contains("keepKnown")) {
				 newKeep = new Proposition("keepKnown_"+this.changeValueTime(k.id.split("keepKnown_")[1], time),true);
			}
			else {
				 newKeep = new Proposition("keep_"+this.changeValueTime(k.id.split("keep_")[1], time),true);
			}
			keepProp.addProposition(newKeep);
		}
		
		for(Proposition obs : this.getObservations().getPropositionSet(time-1).set) 
		{
			if(!before.mcs.contains(obs)) {
				Proposition keep = null;
				int timeObs = Integer.parseInt(obs.id.split("_")[2]);
				if(timeObs < time) {
					if(obs.id.contains("ObsNot")) {
						keep = new Proposition("keepKnown_"+this.changeValueTime(obs.id.split("ObsNot_")[1], time),true);
						keepProp.addProposition(keep);
						keep = new Proposition("keep_"+this.changeValueTime(obs.id.split("ObsNot_")[1], time),true);
						keepProp.addProposition(keep);
					}
					else {
						keep = new Proposition("keepKnown_"+this.changeValueTime(obs.id.split("Obs_")[1], time),true);
						keepProp.addProposition(keep);
						keep = new Proposition("keep_"+this.changeValueTime(obs.id.split("Obs_")[1], time),true);
						keepProp.addProposition(keep);
					}
				}
			}
			
		}
		
		if(time==1) {
			for(Proposition p :this.getBeliefs().set) {
				Proposition keep = null;
				keep = new Proposition("keepKnown_"+this.changeValueTime(p.id, time),true);
				keepProp.addProposition(keep);
				keep = new Proposition("keep_"+this.changeValueTime(p.id, time),true);
				keepProp.addProposition(keep);
			}
		}
		
		
		return keepProp;
	}
	
	
	private String changeValueTime(String predicat,int time) {
		
		String[] pred = predicat.split("_");
		String result ="";
		for(int i=0;i<pred.length;i++) {
			if(i==1) {
				if(time<0) {
					result+="m"+Math.abs(time);
				}
				else{
					result+=time;
				}
			}
			else {
				result+=pred[i];
			}
			if(i+1!=pred.length) {
				result+="_";
			}
		}
		return result;
	}
	
	/*@SuppressWarnings("unused")
	private String getPremisse(String def) {
		
		
		String defTemp = def.split("\n")[1];
		int j=0;
		int count=0;
		while(count<3) {
			if(defTemp.charAt(j)=='(') {
				count+=1;
			}
			j++;
		}
		int i =0;
		count=0;
		defTemp = defTemp.substring(j);
		boolean flag = false;
		while(i<defTemp.length() && (count>0|| flag==false)) {
			if(defTemp.charAt(i)=='(') {
				count+=1;
				flag=true;
			}
			if(defTemp.charAt(i)==')')
				count-=1;
			i++;
		}
		return defTemp.substring(3, i);
	}*/
}
