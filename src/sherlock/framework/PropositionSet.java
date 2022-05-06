package sherlock.framework;
import java.util.ArrayList;

/**
 * Set of logic proposition
 *
 */
public class PropositionSet
{
	public ArrayList<Proposition> set;
	
	public PropositionSet()
	{
		this.set = new ArrayList<Proposition>();
	}
	
	public PropositionSet(PropositionSet set)
	{
		this.set = new ArrayList<Proposition>();
		for(Proposition p : set.set) {
			Proposition pTemp = new Proposition(p.id,p.instance);
			pTemp.isRuleStrong = p.isRuleStrong;
			pTemp.isRuleWeak = p.isRuleWeak;
			this.set.add(pTemp);
		}
	}
	
	public PropositionSet(ArrayList<Proposition> set)
	{
		this.set = set;
	}
	
	public boolean contains(Proposition p)
	{
		return this.set.contains(p);
	}
	
	/**
	 * Return true if the current set is a subset of p
	 * @param p
	 * @return
	 */
	public boolean contains(PropositionSet p)
	{
		return this.set.containsAll(p.set);
	}
	
	
	/**
	 * Return all the proposition that are in the list and the current set
	 * @param list
	 * @return
	 */
	public ArrayList<PropositionSet> allContains(ArrayList<PropositionSet> list)
	{
		ArrayList<PropositionSet> result = new ArrayList<>();
		for(PropositionSet p : list) {
			if(this.contains(p)) {
				result.add(p);
			}
		}
		return result;
	}
	
	/**
	 * Return true if a proposition has the id
	 * @param id
	 * @return
	 */
	public boolean contains(String id)
	{
		for(Proposition p : this.set){
			if(p.id.equals(id))
				return true;
		}
		return false;
	}
	
	/**
	 * Return true if a proposition has the id and that proposition have the same truth value
	 * @param id
	 * @param instance
	 * @return
	 */
	public boolean containsId(String id,boolean instance) 
	{
		for(Proposition p : this.set) {
			if(p.id.split("_")[0].equals(id) && p.instance == instance) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsId(String id)
	{
		for(Proposition p : this.set) {
			if(p.id.split("_")[0].contains("Obs")) {
				if(p.id.split("_")[1].equals(id)) {
					return true;
				}
			}
			
			else if(p.id.split("_")[0].equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Proposition> getPropositionById(String id)
	{
		ArrayList<Proposition> prop = new ArrayList<>();
		for(Proposition p : this.set) {
			
			if(p.id.split("_")[0].contains("Obs")) {
				if(p.id.split("_")[1].equals(id)) {
					prop.add(p);
				}
			}
			else if(p.id.split("_")[0].equals(id)) {
				prop.add(p);
			}
		}
		return prop;
	}
	
	public void changeValueProposition(String id,boolean value) 
	{
		this.getProposition(id).instance = value;
	}
	
	
	/**
	 * Return the proposition with the id
	 * @param id
	 * @return
	 */
	public Proposition getProposition(String id) {
		for(Proposition p : this.set){
			if(p.id.equals(id))
				return p;
		}
		return null;
	}
	
	public void addProposition(Proposition p)
	{
		if(!this.contains(p))
			this.set.add(p);
	}
	/**
	 * Union of two set
	 * @param pS
	 * @param ps2
	 * @return new set
	 */
	public static PropositionSet union(PropositionSet pS, PropositionSet ps2)
	{
		PropositionSet result = new PropositionSet(pS);
		for(Proposition inst : ps2.set) {
			if(!result.contains(inst)) {
				result.addProposition(inst);
			}
		}
		
		return result;
	}
	/**
	 * Intersection of two set
	 * @param pS
	 * @param ps2
	 * @return new set
	 */
	public static PropositionSet intersec(PropositionSet pS,PropositionSet ps2)
	{
		PropositionSet result = new PropositionSet();
		for(Proposition p : pS.set) {
			if(ps2.contains(p))
				result.addProposition(p);
		}
		return result;
	}

	public boolean IsEmpty() {
		return set.isEmpty();
	}
	
	public void removeProposition(Proposition p)
	{
		this.set.remove(p);
	}
	
	public void removePropositionById(String id)
	{
		ArrayList<Proposition> remove = new ArrayList<>();
		for(Proposition p : this.set) {
			String pId = p.id.split("_")[0];
			if(pId.equals(id)) {
				remove.add(p);
			}
		}
		this.set.removeAll(remove);
	}
	
	
	/**
	 * Remove ps2 from ps
	 * @param pS
	 * @param ps2 the set removed from pS
	 */
	public static void retrievePropositionSet(PropositionSet pS, PropositionSet ps2)
	{	
		for(Proposition inst : ps2.set) {
			if(pS.contains(inst)) {
				pS.removeProposition(inst);
			}
		}
	}
	
	
	/**
	 * Remove not ps2 from pS
	 * @param pS
	 * @param ps2 the not set removed
	 */
	public static void retrievePropositionSetInverse(PropositionSet pS, PropositionSet ps2)
	{	
		for(Proposition inst : ps2.set) {
			Proposition inv = new Proposition(inst.id,!inst.instance);
			if(pS.contains(inv)) {
				pS.removeProposition(inv);
			}
		}
	}
	
	
	

	@Override
	public String toString() {
		String result="( ";
		for(Proposition p : set) {
			result+=p.toString()+",";
		}
		result = result.substring(0,result.length()-1);
		result+=" )";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropositionSet other = (PropositionSet) obj;
		if (set == null) {
			if (other.set != null)
				return false;
		} else {
			for(Proposition p : this.set) {
				for(Proposition pP : other.set) {
					if(!p.equals(pP))
						return false;
				}
			}
		}
		return true;
	}
	
	
}
