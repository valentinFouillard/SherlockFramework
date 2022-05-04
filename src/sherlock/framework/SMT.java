package sherlock.framework;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe faisant appel au SMT
 * @author valentin
 *
 */
public class SMT 
{
	public String fileOutput,fileInput;
	public static String PATH="z3";
	//public static String PATH="C:\\\\Z3\\\\z3-4.8.10-x64-win\\\\bin\\\\z3.exe";
	private HashMap<String,Proposition> selectVar;
	private HashMap<Proposition,String> selectVarInverse;
	private String modelSMT;
	
	public SMT(String fileInput,String fileOutput,String model)
	{
		this.fileInput = fileInput;
		this.fileOutput = fileOutput;
		this.selectVar = new HashMap<>();
		this.selectVarInverse = new HashMap<>();
		this.modelSMT=model;
		
		
	}
	
	/**
	 * Retourne les définitions dans le SMT
	 * @return
	 */
	public String getModelSMT()
	{
		return this.modelSMT;
	}
	
	/**
	 * Ajoute une règle au modèle
	 * @param rule
	 */
	public void addRuleSMT(String rule)
	{
		this.modelSMT+= rule;
	}
	/**
	 * Initialise les variables de sélection
	 * @param set
	 */
	private void initSelectVar(PropositionSet set)
	{
		selectVarInverse.clear();
		selectVar.clear();
		
		for(Proposition p : set.set) {
			if(!selectVarInverse.containsKey(p)) {
				String var = "y"+selectVar.size();
				selectVar.put(var, p);
				selectVarInverse.put(p, var);
					
			}
				
		}
		
	}
	
	
	/**
	 * Lance le smt
	 */
	public void launchSMT()
	{
		ProcessBuilder builder = new ProcessBuilder(PATH, this.fileInput);;
		builder.redirectOutput(new File(this.fileOutput));
		try {
			Process p = builder.start();
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Requête sur le SMT pour un système 
	 * @param pSet
	 * @return
	 */
	public boolean requestSat(PropositionSet pSet)
	{
		this.writeModel(pSet);
		this.launchSMT();
		return this.isSat();
	}
	
	public boolean requestSat(PropositionSet pSet,ArrayList<String> assertion)
	{
		this.writeModel(pSet,assertion);
		this.launchSMT();
		return this.isSat();
	}
	
	/**
	 * Calcul les MCS en deux temps : observation puis action
	 * @param action
	 * @param bel
	 * @param obs
	 * @param blocked
	 * @param time
	 * @return
	 */
	public ArrayList<ArrayList<PropositionSet>> getRationnalMCS(PropositionSet action,PropositionSet bel,PropositionSet obs,PropositionSet blocked,PropositionSet keep,int time)
	{
		
		
		ArrayList<ArrayList<PropositionSet>> mcses = new ArrayList<>();
		ArrayList<PropositionSet> allMcses = new ArrayList<>();
		PropositionSet noBlocked = PropositionSet.union(bel, obs);
		//PropositionSet blockedSet = new PropositionSet();
		allMcses = this.getMces(PropositionSet.union(blocked,action), noBlocked, time);
		
		if(allMcses.isEmpty()) {
			ArrayList<PropositionSet> allkeepUpdate = this.frameInconsistencies(blocked, noBlocked, keep, time);
			
			for(PropositionSet allkeep : allkeepUpdate) {
				noBlocked = PropositionSet.union(noBlocked, allkeep);
				this.frameDistortion(mcses, new PropositionSet(), blocked, action, noBlocked, allkeep, time);
			}
			
		}
		else {
			for(PropositionSet mcsObs : allMcses) {
				PropositionSet noBlock = new PropositionSet(noBlocked);
				PropositionSet.retrievePropositionSet(noBlock, mcsObs);
				ArrayList<PropositionSet> allkeepUpdate = this.frameInconsistencies(blocked, noBlock, keep, time);
				
				for(PropositionSet allkeep : allkeepUpdate) {
					noBlock = PropositionSet.union(noBlock, allkeep);
					this.frameDistortion(mcses, mcsObs, blocked, action, noBlock, allkeep, time);
				}
				
			}
		}
		
	   //System.out.println(mcses);
		
		return mcses;
	}
	
	
	private void frameDistortion(ArrayList<ArrayList<PropositionSet>> mcses,PropositionSet mcsObs, PropositionSet blocked,PropositionSet action,PropositionSet noBlocked,PropositionSet allkeep,int time)
	{
		PropositionSet blockedSet = new PropositionSet();
		//ArrayList<PropositionSet> ruleSeparate = this.getRulesPast(noBlocked, time);
		PropositionSet noBlock = new PropositionSet(noBlocked);
		blockedSet = PropositionSet.union(blocked, action);
		//blockedSet = PropositionSet.union(blockedSet, ruleSeparate.get(0));
		
		
		ArrayList<PropositionSet> mcsesUpdate = this.getMces(blockedSet, noBlock, time);
		
		//ArrayList<PropositionSet> mcsTemp = new ArrayList<>();
		if(mcsesUpdate.isEmpty()) {
			
			/*mcsTemp.add(mcsObs);
			mcsTemp.add(allkeep);
			mcses.add(mcsTemp);*/
			this.computeAction(mcses, new PropositionSet(), mcsObs, blockedSet, action, noBlock, allkeep, time);
		}
		else {
			for(PropositionSet mcs : mcsesUpdate) {
				/*mcsTemp.add(mcsObs);
				mcsTemp.add(mcs);
				mcsTemp.add(allkeep);
				mcses.add(mcsTemp);*/
				
				PropositionSet.retrievePropositionSet(noBlock, mcs);
				this.computeAction(mcses, mcs, mcsObs, blockedSet, action, noBlock, allkeep, time);
			}
		}
		
		
		
		
	}
	
	private ArrayList<PropositionSet> getRulesPast(PropositionSet noBlocked,int time)
	{
		ArrayList<PropositionSet> result = new ArrayList<>();
		PropositionSet rulePast = new PropositionSet();
		PropositionSet rulenoPast = new PropositionSet();
		for(Proposition p : noBlocked.set){
			if(p.isRuleStrong || p.isRuleWeak) {
				if(Integer.parseInt(p.id.split("_")[1])>=time){
					rulenoPast.addProposition(p);
				}
				else {
					rulePast.addProposition(p);
				}
			}
		}
		result.add(rulePast);
		result.add(rulenoPast);
		return result;
	}
	private void computeAction(ArrayList<ArrayList<PropositionSet>> mcses,PropositionSet mcsUpdate,PropositionSet mcsObs, PropositionSet blocked,PropositionSet action,PropositionSet noBlocked,PropositionSet allkeep,int time) 
	{
		PropositionSet blockedSet = new PropositionSet(blocked);
		PropositionSet noBlock = new PropositionSet(noBlocked);
		//System.out.println(noBlock);
		noBlock = PropositionSet.union(this.getKnownBlocked(action, noBlock, blocked,allkeep, time),noBlock);
		//blockedSet = PropositionSet.union(blocked, action);
		
		
		ArrayList<PropositionSet> allmcses = this.getMces(blockedSet, noBlock, time);

		
	//	System.out.println(allmcses);
		if(allmcses.isEmpty()) {
			ArrayList<PropositionSet> mcsTemp = new ArrayList<>();
			mcsTemp.add(mcsObs);
			mcsTemp.add(mcsUpdate);
			mcsTemp.add(allkeep);
			mcses.add(mcsTemp);
		}
		else {
			for(PropositionSet mcsAction : allmcses) {
				ArrayList<PropositionSet> mcsTemp = new ArrayList<>();
				mcsTemp.add(mcsObs);
				mcsTemp.add(PropositionSet.union(mcsUpdate, mcsAction));
				mcsTemp.add(allkeep);
				mcses.add(mcsTemp);
			}
		}
		
	}

	private PropositionSet getKnownBlocked(PropositionSet action,PropositionSet noBlocked,PropositionSet blocked,PropositionSet allkeep,int time)
	{
		//PropositionSet knownBlocked = new PropositionSet();
		if(!action.IsEmpty()) {
			PropositionSet known = new PropositionSet();
			for(Proposition rule : noBlocked.set) {
				if(!rule.precArg.isEmpty() && rule.precArg.get(0).substring(6).equals(action.set.get(0).id)) {
					for(int i=1;i<rule.precArg.size();i++) {
						known.addProposition(new Proposition(rule.precArg.get(i),false));
					}
				}
			}
			if(known.IsEmpty()) {
				return new PropositionSet();
			}
			
			
			ArrayList<PropositionSet> mcsKnown = this.getMces(PropositionSet.union(allkeep,PropositionSet.union(blocked, noBlocked)),known, time);
			
			if(mcsKnown.isEmpty()) {
				return known;
			}
			else {
				
				for(PropositionSet mcs : mcsKnown) {
					PropositionSet.retrievePropositionSet(known, mcs);
				}
				
				return known;
			}
		
		}
		return new PropositionSet();
		
		
	}
	
	private PropositionSet getKeepKnown(PropositionSet keep)
	{
		PropositionSet keepKnown = new PropositionSet();
		for(Proposition p : keep.set) {
			if(p.id.contains("keepKnown"))
				keepKnown.addProposition(p);
				
		}
		
		return keepKnown;
	}

	/**
	 * Retourne les MCSes d'un système avec des propositions bloqué et non bloqué
	 * @param blocked impossible à corriger et supossé à vrai
	 * @param noBlocked peut être corrigé mais supposé à vrai
	 * @param time
	 * @return
	 */
	public ArrayList<PropositionSet> getMces(PropositionSet blocked,PropositionSet noBlocked, int time)
	{
		
		ArrayList<PropositionSet> mcses = new ArrayList<>();
		PropositionSet all = PropositionSet.union(blocked, noBlocked);
		this.writeModel(all); // Les variables bloquées et libres sont mises à vrai
	
		this.initSelectVar(all); // Initialisation des variables de sélection pour chaque variables
		this.launchSMT(); // Lance la requête SMT
		if(this.isSat()) {  // Pas de MCS si le système est satisfaisable
			return mcses;
		}
		int k = 1; // Calcul des MCS de taille 1 en premier
		boolean sat = true;
		
		while(sat) { // Tant que le système est satisfaisable
			this.getMcesK(blocked,noBlocked,mcses,time,k); // retourne les mcs de taille k
			this.writeModelWithSelection(blocked,noBlocked,mcses,time, -1);// test du modèle sans les variable de sélection (y'a t il d'autre mcs possible ?)
			this.launchSMT();
			sat = this.isSat(); // Si cest SAT alors il reste des MCS
			k+=1;
		}
		
		// Transforme les variables de sélection par les propositions correspondantes
		ArrayList<PropositionSet> mcsesTransform = new ArrayList<>();
		for(PropositionSet pSet : mcses) {
			PropositionSet pSetTransform = new PropositionSet();
			for(Proposition p : pSet.set) {
				pSetTransform.addProposition(selectVar.get(p.id)); 
			}
			mcsesTransform.add(pSetTransform);
		}
		
		return mcsesTransform;
	}
	
	/**
	 * Retourne l'ensemble des clauses keep qui doivent être gardé suite au changement dans l'inertie du décor
	 * @param blocked
	 * @param noBlocked
	 * @param keep
	 * @param time
	 * @return
	 */
	private ArrayList<PropositionSet> frameInconsistencies(PropositionSet blocked,PropositionSet noBlocked,PropositionSet keep,int time)
	{
		
		ArrayList<PropositionSet> updateModel = new ArrayList<>();
		PropositionSet allKeep = new PropositionSet(keep);
		PropositionSet result = new PropositionSet();
		
		// Seul les incohérences avec les clauses keep value sont regardés
		PropositionSet keepNoBlocked = new PropositionSet();
		PropositionSet keepKnown = new PropositionSet();
		for(Proposition k : keep.set) {
			if(!k.id.contains("keepKnown"))
				keepNoBlocked.addProposition(k);
			else {
				keepKnown.addProposition(k);
			}
		}
		
		
		ArrayList<PropositionSet> mcses = this.getMces(PropositionSet.union(noBlocked, blocked),keepNoBlocked,time);
	    
	
		// S'il n'y a pas de MCS cela veut dire que tout les keep doivent être gardés
		if(mcses.isEmpty()) {
			updateModel.add(allKeep);
			return updateModel;
		}
		
		for(PropositionSet mcs : mcses){
			PropositionSet inverse = new PropositionSet();
			PropositionSet keepNoBlockedTemp = new PropositionSet(keepNoBlocked);
			for(Proposition p : mcs.set) {
				inverse.addProposition(new Proposition(p.id,false));
			}
			
			PropositionSet.retrievePropositionSetInverse(keepNoBlockedTemp, inverse);
		    //result = PropositionSet.union(inverse,keepNoBlockedTemp);
			result = new PropositionSet(keepNoBlockedTemp);
			
		    result = PropositionSet.union(keepKnown, result);
		    updateModel.add(result);
		}
		
		
		return updateModel;
	}
	
	
	/**
	 * Ecris le model dans le SMT
	 * @param model
	 */
	private void writeModel(PropositionSet model)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileInput));
			writer.write("(set-option :produce-models true)\n(set-logic QF_UFLIA)\n");
			writer.write("(declare-fun most (Bool) Int)\n(assert(= (most true) 1))\n(assert (= (most false) 0))\n");// Fonction retournant 1 si la variable est à vrai
			
			this.writeAllDef(writer);
			
			
			this.writeAssume(writer,model);

			writer.write("(check-sat)\n");
			writer.write("(get-model)\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeModel(PropositionSet model,ArrayList<String> assertion)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileInput));
			writer.write("(set-option :produce-models true)\n(set-logic QF_UFLIA)\n");
			writer.write("(declare-fun most (Bool) Int)\n(assert(= (most true) 1))\n(assert (= (most false) 0))\n");// Fonction retournant 1 si la variable est à vrai
			
			this.writeAllDef(writer);
			
			
			this.writeAssume(writer,model);
			for(String asserts : assertion) {
				writer.write("(assert "+asserts+" )\n");
			}
			writer.write("(check-sat)\n");
			writer.write("(get-model)\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecris les définitions
	 * @param writer
	 * @throws IOException
	 */
	private void writeAllDef(BufferedWriter writer) throws IOException
	{
		writer.write(this.modelSMT);
		
		
	}
	
	
	
	/**
	 * Ecrit les assertions pour chaque proposition de props
	 * @param writer
	 * @param props
	 * @throws IOException
	 */
	private void writeAssume(BufferedWriter writer,PropositionSet props) throws IOException
	{
		for(Proposition p : props.set) {
				this.writeAssume(writer, p);
		}
	}
	
	/**
	 * Ecrit l'assertion correspondante à la proposition p
	 * @param writer
	 * @param p
	 * @throws IOException
	 */
	private void writeAssume(BufferedWriter writer,Proposition p) throws IOException
	{
		if(p.instance)
			writer.write("(assert "+p.id+" )\n");
		else
			writer.write("(assert (not "+p.id+"))\n");
		
	}
	
	/**
	 * Retourne vrai si le smt retourne SAT
	 * @return
	 */
	private boolean isSat()
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileOutput));
			String sat = reader.readLine();
			if(sat.equals("sat")) {
				reader.close();
				return true;				
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	/**
	 * Retourne tous les MCS de taille k
	 * @param belief
	 * @param obss
	 * @param rules
	 * @param scenario
	 * @param mcses
	 * @param k
	 */
	private void getMcesK(PropositionSet blocked,PropositionSet noBlocked,ArrayList<PropositionSet> mcses,int t,int k)
	{
		boolean flag = true;
		while(flag) {
			this.writeModelWithSelection(blocked,noBlocked, mcses,t,k);
			this.launchSMT();
			PropositionSet model = this.getVarSelectFalse();
			
			if(model.IsEmpty())
				flag = false;
			else {
				mcses.add(model);
			}
		}
		
	}
	
	/**
	 * Ecrit le modèle avec les variables de sélection
	 * @param blocked
	 * @param noBlocked
	 * @param mcses
	 * @param t
	 * @param k
	 */
	private void writeModelWithSelection(PropositionSet blocked,PropositionSet noBlocked,ArrayList<PropositionSet> mcses,int t,int k)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileInput));
			writer.write("(set-option :produce-models true)\n(set-logic QF_UFLIA)\n");
			writer.write("(declare-fun most (Bool) Int)\n(assert(= (most true) 1))\n(assert (= (most false) 0))\n");
			this.writeAllDef(writer);
			
			this.writeAssume(writer,blocked);
			this.writeAssumeY(writer,noBlocked,t); // écriture des variables de sélection pour les croyances et les observations
			for(PropositionSet mcs : mcses) {
				String block="(assert (or ";
				for(Proposition y : mcs.set) { // écriture des variables bloquantes
					block+=y.id+" ";
				}
				block+="))\n";
				writer.write(block);
			}
			if(k>0)
				this.writeAtMost(writer,noBlocked,mcses, t,k); // Si on cherche les MCS de taille K alors on écrit la requête avec lea fonction at most
			writer.write("(check-sat)\n");
			writer.write("(get-model)\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit les variables de sélection
	 * @param writer
	 * @param props
	 * @param time
	 * @throws IOException
	 */
	private void writeAssumeY(BufferedWriter writer,PropositionSet props,int time) throws IOException
	{
		for(Proposition p : props.set) {
				if(p.instance || p.id.contains("ObsNot")) {
					writer.write("(declare-const "+selectVarInverse.get(p)+" Bool )\n"); // écrit la définition de la variable de sélection
					//writer.write("(assert (xor "+p.id+" (not "+selectVarInverse.get(p)+" ) ) )\n");
					writer.write("(assert (=>  "+selectVarInverse.get(p)+"  "+p.id+" ))\n");
				}
				else {
					writer.write("(declare-const "+selectVarInverse.get(p)+" Bool )\n");
					//writer.write("(assert (xor (not "+p.id+" ) (not "+selectVarInverse.get(p)+" ) ) )\n");
					writer.write("(assert (=> "+selectVarInverse.get(p)+" (not "+p.id+" ) ))\n");
				}
			
		}
			
	}
	
	/**
	 * Ecrit la fonction at most
	 * @param writer
	 * @param noBlocked
	 * @param mcses
	 * @param t
	 * @param k
	 * @throws IOException
	 */
	private void writeAtMost(BufferedWriter writer,PropositionSet noBlocked,ArrayList<PropositionSet> mcses,int t,int k) throws IOException
	{
		String s ="(assert (<= (+ ";
		for(String var : selectVar.keySet()) {
			Proposition  p = selectVar.get(var); 
			if(noBlocked.contains(p)) { // Somme des fonctions the most qui doit être plus petit est égale à k
				s+="(most (not "+var+")) ";
				
			}
		}
		s+=" )"+k+"))\n";
		writer.write(s);
	}
	
	
	/**
	 * Retourne le modèle produit par le smt
	 * @return
	 */
	private PropositionSet getVarSelectFalse()
	{
		PropositionSet set = new PropositionSet();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileOutput));
			String sat = reader.readLine();
			if(sat.equals("sat")) {
				Pattern pattern;
				if(PATH.contains("z3"))
					pattern = Pattern.compile("\\(define-fun (y[0-9]*) \\(\\) Bool");
				else
					pattern = Pattern.compile("\\(= (y[0-9]*) false\\)"); // Match les variables de sélection égale à false 
				String line = "";
				while((line=reader.readLine())!=null) {
					Matcher matcher = pattern.matcher(line);
					if(matcher.find()) {
						if(PATH.contains("z3")){
							if((line=reader.readLine()).contains("false"))
								set.addProposition(new Proposition(matcher.group(1),true));
						}
						else {
							set.addProposition(new Proposition(matcher.group(1),true));
						}
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return set;
	}

}
