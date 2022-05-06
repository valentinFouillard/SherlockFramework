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
 * 
 * Call the SMT solver
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
	 * Return the model in the SMT
	 * @return
	 */
	public String getModelSMT()
	{
		return this.modelSMT;
	}

	/**
	 * Add a rule in the model
	 * @param rule
	 */
	public void addRuleSMT(String rule)
	{
		this.modelSMT+= rule;
	}
	/**
	 * Initialization of the selection variables
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
	 * Launch the SMT solver
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
	 * Return true if the belief model is SAT
	 * @param pSet belief state
	 * @return
	 */
	public boolean requestSat(PropositionSet pSet)
	{
		this.writeModel(pSet);
		this.launchSMT();
		return this.isSat();
	}

	/**
	 * Return true if the belief state and the assertions are sat
	 * @param pSet belief state
	 * @param assertion
	 * @return
	 */
	public boolean requestSat(PropositionSet pSet,ArrayList<String> assertion)
	{
		this.writeModel(pSet,assertion);
		this.launchSMT();
		return this.isSat();
	}

	/**
	 * Sherlock main algorithm, return the proposition that must be ignored because of a local inconsistency, frame inconsistency or frame distortion
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

		allMcses = this.getMces(PropositionSet.union(blocked,action), noBlocked, time); // Compute the MCS with the action blocked to true

		if(allMcses.isEmpty()) {
			ArrayList<PropositionSet> allkeepUpdate = this.frameInconsistencies(blocked, noBlocked, keep, time); // Return all the keep clause removed from a frame inconsistency

			if(allkeepUpdate.isEmpty()) {
				this.frameDistortion(mcses, new PropositionSet(), blocked, action, noBlocked, new PropositionSet(), time); // Return the distortion of the frame
			}
			else {
				for(PropositionSet allkeep : allkeepUpdate) {
					noBlocked = PropositionSet.union(noBlocked, allkeep);
					this.frameDistortion(mcses, new PropositionSet(), blocked, action, noBlocked, allkeep, time);
				}
			}

		}
		else {
			for(PropositionSet mcsObs : allMcses) {
				PropositionSet noBlock = new PropositionSet(noBlocked);
				PropositionSet.retrievePropositionSet(noBlock, mcsObs);
				ArrayList<PropositionSet> allkeepUpdate = this.frameInconsistencies(blocked, noBlock, keep, time);
				if(allkeepUpdate.isEmpty()) {
					this.frameDistortion(mcses, new PropositionSet(), blocked, action, noBlocked, new PropositionSet(), time);
				}
				else {
					for(PropositionSet allkeep : allkeepUpdate) {
						noBlock = PropositionSet.union(noBlock, allkeep);
						this.frameDistortion(mcses, mcsObs, blocked, action, noBlock, allkeep, time);
					}
				}

			}
		}

		//System.out.println(mcses);

		return mcses;
	}


	/**
	 * Compute all the MCSes that correspond to a frame distortion
	 * @param mcses
	 * @param mcsObs
	 * @param blocked
	 * @param action
	 * @param noBlocked
	 * @param allkeep
	 * @param time
	 */
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

	
	/*private ArrayList<PropositionSet> getRulesPast(PropositionSet noBlocked,int time)
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
	}*/
	
	/**
	 * Compute the MCSes for an inconsistency with an action and his preconditions
	 * @param mcses
	 * @param mcsUpdate
	 * @param mcsObs
	 * @param blocked
	 * @param action
	 * @param noBlocked
	 * @param allkeep
	 * @param time
	 */
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

	/**
	 * Return the proposition that are known
	 * @param action
	 * @param noBlocked
	 * @param blocked
	 * @param allkeep
	 * @param time
	 * @return
	 */
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

	@SuppressWarnings("unused")
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
	 * Return the MCSes of a system with proposition that are blocked or free
	 * @param blocked
	 * @param noBlocked
	 * @param time
	 * @return
	 */
	public ArrayList<PropositionSet> getMces(PropositionSet blocked,PropositionSet noBlocked, int time)
	{

		ArrayList<PropositionSet> mcses = new ArrayList<>();
		PropositionSet all = PropositionSet.union(blocked, noBlocked);
		this.writeModel(all); // Blocked propositions are set to true

		this.initSelectVar(all); // Initialization of selection var
		this.launchSMT(); // Launch the SMT solver
		if(this.isSat()) {  // If SAT there is no MCSes
			return mcses;
		}
		int k = 1; // Starting with the MCSes of length 1
		boolean sat = true;

		while(sat) { // There exist an MCS if the system is still SAT
			this.getMcesK(blocked,noBlocked,mcses,time,k); // return the MCS of length k
			this.writeModelWithSelection(blocked,noBlocked,mcses,time, -1);// Launch a sat request without the selection var
			this.launchSMT();
			sat = this.isSat(); //
			k+=1; // next length
		}

		// Transform the selection var to the real proposition
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
	 * Return the MCSes that correspond to an update of the frame
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

		// Only the inconsistencies with the keep values are hold
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
	 * Write the model in the SMT
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
	 * Write the definition of the propositions
	 * @param writer
	 * @throws IOException
	 */
	private void writeAllDef(BufferedWriter writer) throws IOException
	{
		writer.write(this.modelSMT);


	}



	/**
	 * Weite the assertions
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
	 * Write the assertion for p
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
	 * Return true if the SMT return SAT
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
	 * Return all the MCSes of lenght k
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
	 * Write the model with selection var
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
	 * Write selection var
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
	 * Write the atMost function (to compute the lenght of a MCS)
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
	 * Return the model of the SMT
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
