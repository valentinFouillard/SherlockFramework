package sherlock.framework;
import java.util.ArrayList;

/**
 * Node of the scenario tree
 *
 */
public class Node 
{
	
	public PropositionSet mcs; // All the proposition that need to be removed
	public ArrayList<Node> next; // The next nodes
	public Node parent; // The node before
	public int time;
	public PropositionSet keep; // the keep updated 
	public ArrayList<PropositionSet> mcsSepare; // each proposition set removed at each step of the algorithm
	public String bias = "";
	private ArrayList<String> expl;
	
	public Node(PropositionSet mcs,ArrayList<Node> next,Node parent,int time)
	{
		this.mcs = mcs;
		this.next = next;
		this.parent = parent;
		this.time = time;
		this.keep = new PropositionSet();
		this.expl = new ArrayList<>();
	}
	
	public Node(PropositionSet mcs,ArrayList<Node> next,int time)
	{
		this.mcs = mcs;
		this.next = next;
		this.parent = null;
		this.time = time;
		this.keep = new PropositionSet();
		this.expl = new ArrayList<>();
	}
	
	public Node(PropositionSet mcs,Node parent,int time)
	{
		this.mcs = mcs;
		this.next = new ArrayList<>();
		this.parent = parent;
		this.time = time;
		this.keep = new PropositionSet();
		this.expl = new ArrayList<>();
	}
	
	
	public Node(ArrayList<PropositionSet> mcs,Node parent,int time) 
	{
		this.mcsSepare = new ArrayList<>();
		mcsSepare.add(mcs.get(0));
		mcsSepare.add(mcs.get(1));
		this.next = new ArrayList<>();
		this.parent = parent;
		this.time = time;
		this.keep = mcs.get(2);
		this.mcs = new PropositionSet();
		for(int i= 0;i<2;i++) {
			PropositionSet m = mcs.get(i);
			this.mcs = PropositionSet.union(this.mcs, m);
		}
		this.expl = new ArrayList<>();
	}
	
	public Node(PropositionSet mcs,int time)
	{
		this.mcs = mcs;
		this.next = new ArrayList<>();
		this.parent = null;
		this.time = time;
		this.keep = new PropositionSet();
		this.expl = new ArrayList<>();
	}
	
	public ArrayList<PropositionSet> getMcsSepare()
	{
		return this.mcsSepare;
	}
	
	public Node getParent()
	{
		return this.parent;
	}
	
	public ArrayList<Node> getNext()
	{
		return this.next;
	}
	
	
	public void addNext(Node next)
	{
		this.next.add(next);
	}
	
	
	public boolean hasNext()
	{
		return !this.next.isEmpty();
	}

	@Override
	public String toString() {
		return mcs.toString();
	}
	
	/**
	 * Return the union of the MCS from the racine to the current node
	 * @return
	 */
	public PropositionSet mcsUnion()
	{
		if(this.parent==null)
			return new PropositionSet();
		else {
			return PropositionSet.union(this.mcs, this.parent.mcsUnion());
		}
	}
	
	/**
	 * Return a list of the MCS from the racine to the current node
	 * @return
	 */
	public ArrayList<PropositionSet> getMCSFromNode()
	{
		ArrayList<PropositionSet> result = new ArrayList<>();
		this.getPrevMCs(result);
		return result;
	}
	
	/**
	 * Return all the keep clause from the racine to the current node
	 * @return
	 */
	public PropositionSet getKeepClauseFromNode()
	{
		PropositionSet result = new PropositionSet();
		this.getPrevKeep(result);
		return result;
	}
	
	/**
	 * Add the current keep to the previous keep
	 * @param result
	 */
	private void getPrevKeep(PropositionSet result) {
		if(this.parent!=null) {
			this.parent.getPrevKeep(result);
			for(Proposition p : this.keep.set) {
				result.addProposition(p);
			}
		}
	}
	
	/**
	 * Add the current MCS to the previous MCSes
	 * @param result
	 */
	private void getPrevMCs(ArrayList<PropositionSet> result)
	{
		if(this.parent!=null) {
			this.parent.getPrevMCs(result);
			result.add(mcs);
		}
	}
	
	/**
	 * Return the neighbors of the current node
	 * @return
	 */
	public ArrayList<PropositionSet> getChoices()
	{
		ArrayList<PropositionSet> choices = new ArrayList<>();
		if(this.parent!=null) {
			for(Node node : this.parent.next) {
				choices.add(node.mcs);
			}
			
			return choices;
		}
		else {
			return choices;
		}
	}
	
	
	public ArrayList<String> getExplanations()
	{
		return this.expl;
	}
	
	public void addExpl(String expl)
	{
		this.expl.add(expl);
	}
}
