package sherlock.framework;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import sherlock.framework.explanations.*;
import sherlock.framework.parser.SMTVisitor;

/**
 * 
 * Class that build the scenario tree and search possible explanation
 *
 */
public class Diagnostic 
{
	private SMTBuild build;
	public SetTime scenario,obss;
	public PropositionSet belief,rules,actions,coherence,desires;
	public SMT smt;
	public Node racine;
	private ArrayList<CoExp> explanations;

	/**
	 * Constructor
	 * @param build model builder
	 * @param smt the smt solver
	 * @param explanations a list of possible explanations
	 */
	public Diagnostic(SMTBuild build,SMT smt,ArrayList<CoExp> explanations)
	{

		this.build = build;
		this.obss = this.build.getObservations();
		this.belief = this.build.getBeliefs();
		this.scenario = this.build.getScenario();

		this.smt = smt;
		this.racine = new Node(new PropositionSet(),-1);

		this.coherence = this.build.getCoherence();
		this.rules = this.build.getRules();
		this.desires = this.build.generateDesires();
		this.belief = PropositionSet.union(belief, rules);
		this.belief = PropositionSet.union(belief, desires);
		this.explanations = explanations;
		
	}

	/**
	 * Launch the diagnostic
	 */
	public void launchDiag()
	{
		this.tradObservation();// add libelle on the observations
		this.computeProbableScenarios(); // compute the scenario tree
	}

	/**
	 * Add libelle on the observations
	 */
	private void tradObservation()
	{
		for(int i =0 ; i<this.obss.timeLimit;i++) {
			for(Proposition p : this.obss.getPropositionSet(i).set) {
				if(p.instance) 
					p.id = "Obs_"+p.id;
				else {
					p.id = "ObsNot_"+p.id;
					p.instance = true;
				}
			}
		}

	}



	/**
	 * Return and add the node for the mcs to the node following the node before
	 * @param before parent of the current node
	 * @param mcs 
	 * @return node next to before
	 */
	private Node addNext(Node before,ArrayList<PropositionSet> mcs) 
	{
		Node node = new Node(mcs,before,(before.time+1));
		node.keep = this.removeKeepMCS(node.keep, node.mcs);
		before.addNext(node);
		return node;
	}

	/**
	 * Compute the next MCSes after the node before
	 * @param before the node before the current step
	 * @return next MCSes
	 */
	private ArrayList<ArrayList<PropositionSet>> nextMCS(Node before)
	{
		PropositionSet newBelief = new PropositionSet(); // newBelief will contain the state of the node before with the observation and action of the next step
		PropositionSet keepClause = new PropositionSet();
		PropositionSet blockedRules = new PropositionSet();

		newBelief = new PropositionSet(this.belief); 
		
		int time = before.time+1; // Current step is the time after before

		if(time>0) {
			keepClause = this.allKeep(time, before); // return the keep clause of the time step
		}
		else {
			this.addknowInit(); // At step 0, the agent know the initial belief

		}

		newBelief = this.constructBelief(newBelief, obss, before,time,blockedRules); // Add all the observations and actions from step 0 to the node before and remove the MCS at each step

		PropositionSet actions = scenario.getPropositionSetUnion(time-1); // All the actions from the start

		PropositionSet blocked = PropositionSet.union(before.getKeepClauseFromNode(),
				PropositionSet.union(PropositionSet.union(actions, blockedRules),this.coherence)); // The coherence set, the actions and the keep clause before the current step are blocked



		ArrayList<ArrayList<PropositionSet>> mces = smt.getRationnalMCS(scenario.getPropositionSet(time),newBelief,obss.getPropositionSet(time),blocked,keepClause,time); // Compute all MCSes

		return mces;
	}

	/**
	 * Compute all the scenarios
	 */
	public void computeProbableScenarios()
	{
		int threshold = 0;
		//ArrayList<Node> noExplore = new ArrayList<>();
		ArrayList<Node> racineList = new ArrayList<>();
		racineList.add(racine);
		//threshold =this.computeThreshold(racineList, threshold, noExplore);
		threshold = 0;
		for(Node node : racineList) {
			if(((scenario.timeLimit - node.time)>= threshold) && (node.time+1 < scenario.timeLimit)) {
				int nbrExp = 0;
				this.explore(node,nbrExp,threshold);
			}
		}
	}


	@SuppressWarnings("unused")
	private int computeThreshold(ArrayList<Node> nodes,int threshold,ArrayList<Node> noExplore)
	{

		ArrayList<Node> haveExp = new ArrayList<>();
		ArrayList<Node> nextNodes = new ArrayList<>();
		for(Node node : nodes) {
			for(ArrayList<PropositionSet> mcs : this.nextMCS(node)) {
				nextNodes.add(this.addNext(node, mcs));
			}
		}

		for(Node next : nextNodes) {
			//boolean isExplained = false;
			int j = 0;
			while(j < this.explanations.size()){//&& !isExplained {
				CoExp exp = this.explanations.get(j);
				if(exp.explain(next)) {
					haveExp.add(next);
					//isExplained = true;
					next.bias = exp.getName();
				}
				j++;

			}

		}

		if(haveExp.isEmpty()) {
			haveExp = nextNodes;
		}
		else {
			nextNodes.removeAll(haveExp);
			noExplore.addAll(nextNodes);
			threshold ++;
		}
		if(nextNodes.isEmpty()) {
			return threshold;
		}

		if((nextNodes.get(0).time+1) != this.scenario.timeLimit) {

			return this.computeThreshold(haveExp, threshold, noExplore);
		}
		else {
			return threshold;
		}

	}

	/**
	 * Compute the next nodes from the current node
	 * @param node
	 * @param nbrExp number of explanations
	 * @param threshold of explanations
	 */
	private void explore(Node node,int nbrExp,int threshold)
	{
		for(ArrayList<PropositionSet> nextMcs : this.nextMCS(node)) {
			Node next = this.addNext(node, nextMcs);
			System.out.println(next);
			//boolean isExplained = false;
			int j = 0;
			while(j < this.explanations.size() ) {//&& !isExplained
				CoExp exp = this.explanations.get(j);
				if(exp.explain(next)) {
					//isExplained = true;
					System.out.println(exp.getName() + " : "+next.mcs);
					nbrExp++;
					next.bias = exp.getName();
				}
				j++;
			}
			if(((nbrExp >= threshold) || 
					((this.scenario.timeLimit - next.time >= threshold))) &&
					(next.time+1 < this.scenario.timeLimit)){
				this.explore(next, nbrExp, threshold);
			}
		}
	}


	/**
	 * Return all keep clauses from the node before to the time step time
	 * @param time
	 * @param before
	 * @return
	 */
	private PropositionSet allKeep(int time, Node before)
	{
		return build.allKeep(time, before);
	}

	/**
	 * Remove the keep clause that are used to update the inertia 
	 * @param keepClause
	 * @param mcs
	 * @return
	 */
	private PropositionSet removeKeepMCS(PropositionSet keepClause, PropositionSet mcs)
	{
		PropositionSet keepClone = new PropositionSet(keepClause);
		for(Proposition p : mcs.set) {
			if(keepClone.contains(p)) {
				keepClone.removeProposition(p);
			}
		}
		return keepClone;
	}


	

	/**
	 * Build the belief state of the next step after the node before
	 * @param belief the initial belief
	 * @param obss the observation of the current step
	 * @param before the node before the step
	 * @return belief state
	 */
	private PropositionSet constructBelief(PropositionSet belief,SetTime obss,Node before,int time,PropositionSet blockedRules)
	{

		ArrayList<PropositionSet> mcses = before.getMCSFromNode();

		PropositionSet keep = before.getKeepClauseFromNode();

		PropositionSet newBelief = new PropositionSet(belief);
		newBelief = PropositionSet.union(keep, newBelief);
		

		for(int i =0;i<mcses.size();i++) {
			PropositionSet mcs = mcses.get(i);

			newBelief = PropositionSet.union(newBelief, obss.getPropositionSet(i));
			PropositionSet.retrievePropositionSet(newBelief, mcs);
		}

		return newBelief;
	}

	
	@SuppressWarnings("unused")
	private void removeAllRule(PropositionSet newbelief,Node before)
	{
		ArrayList<PropositionSet> mcses = before.getMCSFromNode();
		for(int i =0;i<mcses.size();i++) {
			PropositionSet mcs = mcses.get(i);
			for(Proposition p : mcs.set) {
				String pId = p.id.split("_")[0];
				if(pId.contains("B")) {
					newbelief.removePropositionById(pId);
				}
			}
		}

	}


	/**
	 * Add inital known predicate
	 */
	private void addknowInit()
	{
		for(Proposition p : this.belief.set) {
			if(p.id.charAt(0)!= SMTVisitor.RULEID) {
				if(!this.smt.getModelSMT().contains("(assert known_"+p.id+")\n")) {
					this.smt.addRuleSMT("(assert known_"+p.id+")\n");
				}
			}
		}
	}
	
	/**
	 * Build output file
	 * @param fileOutput
	 */
	public void getOutputXML(String fileOutput) 
	{
		
		String out ="<?xml version=\"1.0\"?>\n";
		out+="<model>\n";
		out+=this.outputModel();
		out+=this.outputNode(racine.next.get(0));
		out+="</model>\n";

		try {
			FileWriter writer = new FileWriter(fileOutput);
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	
	/**
	 * Output the model used in Sherlock
	 * @return
	 */
	private String outputModel()
	{
		String rslt = "<Rules>\n";
		ArrayList<String> rulesString = new ArrayList<>();
		for(Proposition r : this.rules.set) {
			String id = r.id.split("_")[0];
			if(!rulesString.contains(id)) {
				rslt+=id+",";
				rulesString.add(id);
			}
		}
		rslt = rslt.substring(0, rslt.length()-1);
		rslt+="</Rules>\n";

		rslt+="<Predicat>\n";
		for(Proposition p : build.getFacts().set) {
			String id = p.id.split("_")[0];
			if(!rulesString.contains(id)) {
				rslt+=id+",";
				rulesString.add(id);
			}
		}
		rslt = rslt.substring(0, rslt.length()-1);
		rslt+="</Predicat>\n";

		rslt+="<Actions>\n";
		for(PropositionSet pS : build.getScenario().getSet()) {
			for(Proposition p : pS.set) {
				String id = p.id.split("_")[0];
				if(!id.contains("known")&&p.instance) {
					rslt+=id+",";
				}
			}
		}
		rslt = rslt.substring(0, rslt.length()-1);

		rslt+="</Actions>\n";
		return rslt;



	}
	
	/**
	 * Return XML output for the node
	 * @param node
	 * @return
	 */
	private String outputNode(Node node)
	{

		//InfoNode info  = bResearch.getInfo(node, node.time);
		String rslt = "<State>\n";
		rslt+="<MCS> "+(node.mcs.IsEmpty()?" ":node.mcs)+" </MCS>\n";
		//rslt+="<Keep>"+(node.keep.IsEmpty()?" ":node.keep)+" </Keep>\n";
		//rslt+=this.addInfo("Emotions", "Emotion", info.getEmotions());
		//rslt+=this.addInfo("Families", "Family", info.getFamilyBias());
		//rslt+=this.addInfo("Biases", "Bias", info.getBias());
		ArrayList<String> biasList = new ArrayList<>();
		biasList.add(node.bias);
		rslt+=this.addInfo("Bias", "Bias",biasList);
		if(node.hasNext()) {
			rslt+="<Step time=\""+(node.time+1)+"\">\n";
			for(Node next : node.next) {
				rslt+=this.outputNode(next);
			}
			rslt+="</Step>\n";
		}
		rslt+="</State>\n";

		return rslt;
	}

	private String addInfo(String typeFamily,String type,ArrayList<String> instance)
	{
		String rslt = "";
		if(instance.isEmpty())
			return rslt;
		rslt+="<"+typeFamily+">\n";
		/*for(String inst : instance) {
			rslt+="<"+type+"> "+inst+" </"+type+">\n";
		}*/
		for(String inst : instance) {
			rslt+=inst +",";
		}
		rslt = rslt.substring(0, rslt.length()-1);
		rslt+="</"+typeFamily+">\n";
		return rslt;
	}



}
