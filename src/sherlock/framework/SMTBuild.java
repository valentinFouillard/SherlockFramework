package sherlock.framework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sherlock.framework.parser.Factory;
import sherlock.framework.parser.FactoryBuild;
import sherlock.framework.parser.PropositionFactory;
import sherlock.framework.parser.SMTVisitor;

/**
 * SMTBuild se charge de la génération de la déinfition des règles et des propositions
 * au format SMT
 * @author valentin
 *
 */
public class SMTBuild 
{
	private SMTVisitor<Object> smtVisitor;
	private PropositionSet rulesCoherent;
	private PropositionSet rules;
	private String model;
	private PropositionSet facts;
	private SetTime scenario;
	private FactoryBuild factBuild;
	
	
	public SMTBuild(SMTVisitor<Object> smtVisitor)
	{
		this.smtVisitor = smtVisitor;
		this.factBuild = new FactoryBuild();
		this.rulesCoherent = new PropositionSet();
		this.rules = new PropositionSet();
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
						temp.addProposition(new Proposition("known_"+p.id,true));
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
						temp.addProposition(new Proposition("known_"+name,true));
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
	
	public PropositionSet generateDesires()
	{
		PropositionSet allDesires = new PropositionSet();
		
		for(Proposition d : this.getDesires()) {
			for(int i=0;i<this.getScenario().timeLimit+1;i++) {
				allDesires.addProposition(new Proposition(d.id+"_"+i,d.instance));
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
	
	/*private String generateAllValueFactory(PropositionFactory factory,HashMap<String,Integer> limit)
	{
		String result ="";
		ArrayList<String> args = new ArrayList<>(factory.getPreds().get(0).idArgs.keySet());
		String model = this.stepGenerate(factory, new HashMap<String,Integer>(), args, 0, limit, result);
		return model;
	}*/
	
	/*private String stepGenerate(PropositionFactory factory,HashMap<String,Integer> value,ArrayList<String> args,int index,HashMap<String,Integer> limit,String result)
	{
		String arg = args.get(index);
		int minLimit = 0;
		int maxLimit = limit.get(arg);
		if(!factory.isRule()) {
			minLimit = factory.getMinLimit().get(arg);
			maxLimit = factory.getMaxLimit().get(arg) + maxLimit;
		}
		
		for(int i=minLimit;i<maxLimit;i++) {
			value.put(arg, i);
			if(index+1 == args.size()) {
				String tempResult = factory.createInstance(value);
				 String namePred = factory.getPreds().get(0).getPredToSMT(value, false);
				 
				 if(factory.getCoherence()) {
					 this.rulesCoherent.addProposition(new Proposition(namePred,true));
				 }
				 if(factory.isRule()) {
					 Proposition r = new Proposition(namePred,true,factory.getMaxTime(value.get("t")));
					 r.setPremisse(this.getPremisse(tempResult));
					 if(factory.isStrong()) {
						r.isRuleStrong = true;
					}
					else {
						r.isRuleWeak = true;
					}
					 if(factory.precondition!=null) {
						 ArrayList<String> prec = new ArrayList<>();
						 final String regex = "known_[^\\s\\)]*";
						 final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
						 final Matcher matcher = pattern.matcher(tempResult);
						 while(matcher.find()) {
							 prec.add(matcher.group(0));
						 }
						 
					     r.precArg = prec;   
					 }
			
					 this.rules.addProposition(r);
					 
				 }
				 else {
					 if(!factory.isCoherence) {
						     this.facts.addProposition(new Proposition(namePred,true));
						 	 tempResult = this.addKnown(namePred)+ tempResult;
							 tempResult += this.addMetaRule(namePred,minLimit);
					 }
				 }
				 
				result+=tempResult; 
			}
			else {
				 result+=this.stepGenerate(factory, value, args, index+1, limit,"");
			}
		}
		return result;
	}*/
	
	private String addKnown(String idPredicat) {
		return "(declare-const known_"+idPredicat+" Bool)\n";
	}
	private String addMetaRule(String idPredicat,int minlimit) {
		String result="";
		String timeS = idPredicat.split("_")[1];
		int timePred = 0;
		if(timeS.contains("m")) {
			timePred = -Integer.parseInt(timeS.substring(1));
		}
		else{
			timePred= Integer.parseInt(timeS);
		}
		if(timePred > minlimit && !timeS.contains("m")) {
			result+="(declare-const keep_"+idPredicat+" Bool)\n";
			result+="(declare-const keepKnown_"+idPredicat+" Bool)\n";
			String newname = timePred<0?this.changeValueTime(idPredicat, timePred+1):this.changeValueTime(idPredicat, timePred-1);
			result+="(assert (= keep_"+idPredicat+" (= "+newname+" "+idPredicat+")))\n";
			
			result+="(assert (= keepKnown_"+idPredicat+" (= known_"+newname+" known_"+idPredicat+" )))\n";
		}
		
		Proposition p = this.smtVisitor.observations.contains(idPredicat);
		if(p!=null) {
			//if(p.instance) {
				result += "(declare-const Obs_"+idPredicat+" Bool)\n";
				result+= "(assert (=> Obs_"+idPredicat+" (and "+idPredicat+" known_"+idPredicat+") ))\n";
			//}
			//else {
				result += "(declare-const ObsNot_"+idPredicat+" Bool)\n";
				result+= "(assert (=> ObsNot_"+idPredicat+" (and (not "+idPredicat+") known_"+idPredicat+") ))\n";
			//}
		}
		return result;
	}
	/**
	 * Retourne toute les clauses keep d'un temps t
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
	
	/**
	 * Retourne les clauses keep value
	 * @param time
	 * @param before
	 * @return
	 */
	/*private PropositionSet getKeep(int time,Node before)
	{
		PropositionSet keepProp = new PropositionSet();
		for(Proposition p : this.getObservations().getPropositionSetUnion(time-1).set) {
			if(!before.mcs.contains(p)) {
				int timeP= 0;
				if(p.id.split("_")[2].contains("m")) {
					timeP = -Integer.parseInt(p.id.split("_")[2].substring(1));
				}
				else{
					timeP = Integer.parseInt(p.id.split("_")[2]);
				}
				String id = p.id.split("_")[1] +"_"+ p.id.split("_")[2];
				if(timeP<time) {
					String newId= "keep_"+this.changeValueTime(id, time);
					keepProp.addProposition(new Proposition(newId,true));
				}
			}
		}
		
		for(Proposition p : this.getBeliefs().set) {
			if(p.id.charAt(0) != SMTVisitor.RULEID) {
				int timeP= 0;
				if(p.id.split("_")[1].contains("m")) {
					timeP = -Integer.parseInt(p.id.split("_")[1].substring(1));
				}
				else{
					timeP = Integer.parseInt(p.id.split("_")[1]);
				}
				if(timeP<time) {
					String newId= "keep_"+this.changeValueTime(p.id,time);
					keepProp.addProposition(new Proposition(newId,true));
				}
			}
		}
		
		return keepProp;
			
	}*/
	
	/**
	 * Retourne les clauses keep known
	 * @param time
	 * @param before
	 * @return
	 */
	/*private PropositionSet getKeepKnown(int time,Node before)
	{
		PropositionSet keepProp = new PropositionSet();
		for(Proposition p : this.getObservations().getPropositionSetUnion(time-1).set) {
			if(!before.mcs.contains(p)) {
				int timeP= 0;
				if(p.id.split("_")[2].contains("m")) {
					timeP = -Integer.parseInt(p.id.split("_")[2].substring(1));
				}
				else{
					timeP = Integer.parseInt(p.id.split("_")[2]);
				}
				
				String id = p.id.split("_")[1] +"_"+ p.id.split("_")[2];
				if(timeP<time) {
					String newId= "keepKnown_"+this.changeValueTime(id,time);
					keepProp.addProposition(new Proposition(newId,true));
				}
			}
		}
		
		for(Proposition p : this.getBeliefs().set) {
			if(p.id.charAt(0) != SMTVisitor.RULEID) {
				int timeP= 0;
				if(p.id.split("_")[1].contains("m")) {
					timeP = -Integer.parseInt(p.id.split("_")[1].substring(1));
				}
				else{
					timeP = Integer.parseInt(p.id.split("_")[1]);
				}
				if(timeP<time) {
					String newId= "keepKnown_"+this.changeValueTime(p.id,time);
					keepProp.addProposition(new Proposition(newId,true));
				}
			}
		}
		
		return keepProp;
			
	}*/
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
	}
}
