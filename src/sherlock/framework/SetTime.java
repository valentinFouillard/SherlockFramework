package sherlock.framework;
/**
 * Ensemble d'ensemble de proposition dans le temps
 * @author valentin
 *
 */
public class SetTime 
{
	public int timeLimit;
	
	private PropositionSet[] set;
	
	public SetTime(int timeLimit) 
	{
		this.timeLimit = timeLimit;
		this.set = new PropositionSet[this.timeLimit];
		for(int i=0;i<this.timeLimit;i++) {
			this.set[i] = new PropositionSet();
		}
	}
	
	public PropositionSet[] getSet()
	{
		return this.set;
	}
	
	public PropositionSet getPropositionSet(int time)
	{
		if(time>this.set.length) {
			return new PropositionSet();
		}
		return this.set[time];
	}
	
	public void addPropositionSet(PropositionSet propSet,int time)
	{
		this.set[time] = propSet;
	}
	
	public void addProposition(Proposition prop,int time)
	{
		this.set[time].addProposition(prop);
	}
	
	/**
	 * Union des observations jusqu'à un moment t
	 * @param time
	 * @return
	 */
	public PropositionSet getPropositionSetUnion(int time)
	{
		if(time==0)
			return this.getPropositionSet(0);
		PropositionSet result = new PropositionSet();
		for(int i =0;i<=time;i++) {
			result = PropositionSet.union(result, this.getPropositionSet(i));
		}
		return result;
	}
	
	public Proposition contains(String idPredicat) {
		for(int i =0;i<this.timeLimit;i++) {
			if(this.set[i].contains(idPredicat)) {
				return this.set[i].getProposition(idPredicat);
			}
		}
		return null;
	}
}
