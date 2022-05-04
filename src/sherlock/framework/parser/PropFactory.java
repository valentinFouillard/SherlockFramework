package sherlock.framework.parser;

public class PropFactory extends Factory
{

	public PropFactory(String name)
	{
		super(name);
	}

	public String getID(int time) 
	{
		return name+"_"+Integer.toString(time);
	}
	
	
	

	@Override
	public void generate(FactoryBuild factBuild, int time) 
	{
		String id = this.getID(time);
		if(!factBuild.modelPropContains(id)) {
			factBuild.addProp("(declare-const "+id+" Bool)\n");
			factBuild.addProp("(declare-const known_"+id+" Bool)\n");
			factBuild.addProp("(declare-const Obs_"+id+" Bool)\n");
			factBuild.addProp("(assert (=> Obs_"+id+" (and "+id+" known_"+id+") ))\n");
			factBuild.addProp("(declare-const ObsNot_"+id+" Bool)\n");
			factBuild.addProp("(assert (=> ObsNot_"+id+" (and (not "+id+") known_"+id+") ))\n");
			if(time>0) {
				String idM = this.getID(time-1);
				factBuild.addProp("(declare-const keep_"+id+" Bool)\n");
				factBuild.addProp("(assert (= keep_"+id+" (= "+idM+" "+id+")))\n");
				factBuild.addProp("(declare-const keepKnown_"+id+" Bool)\n");
				factBuild.addProp("(assert (= keepKnown_"+id+" (= known_"+idM+" known_"+id+")))\n");
			}
		}
	}
	
}
