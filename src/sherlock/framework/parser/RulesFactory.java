package sherlock.framework.parser;

import java.util.ArrayList;

public class RulesFactory extends Factory
{
	private String signature;
	private ArrayList<Factory> allPropositions;
	private ArrayList<Integer> timeProp;
	protected boolean isCoherence;
	protected boolean isStrong;
	private String precondition;
	
	
	public RulesFactory(String name,String signature,ArrayList<Factory> allPropositions,ArrayList<Integer> timeProp)
	{
		super(name);
		this.signature = signature;
		this.allPropositions = allPropositions;
		this.timeProp = timeProp;
		this.isCoherence = false;
		this.isStrong = false;
		this.precondition = "";
	}
	
	public RulesFactory(String name)
	{
		super(name);
		this.signature = "";
		this.allPropositions = new ArrayList<>();
		this.timeProp = new ArrayList<>();
		this.isCoherence = false;
		this.isStrong = false;
		this.precondition = "";
	}
	
	public void addSign(String sign)
	{
		this.signature += sign;
	}
	
	public void addProp(Factory prop)
	{
		this.allPropositions.add(prop);
	}
	
	public void addTimeProp(int time)
	{
		this.timeProp.add(time);
	}
	
	public void setStrong(boolean isStrong) {
		this.isStrong = isStrong;
	}
	
	public void setCoherence(boolean isCoherence)
	{
		this.isCoherence = isCoherence;
	}
	
	public void setPremisse(String precondition)
	{
		this.precondition = precondition;
	}
	
	public String getPremisse()
	{
		return this.precondition;
	}
	
	
	public String getID(int time) 
	{
		return name+"_"+Integer.toString(time);
	}
	public void generate(FactoryBuild factBuild,int time)
	{
		String instanceSign = "";
		int index =0;
		for(int i =0;i<signature.length();i++) {
			if(signature.charAt(i)=='%') {
				Factory prop = this.allPropositions.get(index);
				prop.generate(factBuild,time+timeProp.get(index));
				String idProp = prop.getID(time+timeProp.get(index));
				if(this.caseNotPred(instanceSign)) {
					String instanceTemp = instanceSign.substring(0,instanceSign.length()-6);
					instanceTemp+="(and (not "+ idProp+") known_"+idProp+")";
					instanceSign = instanceTemp;
					i+=2;
				}
				else {
					instanceSign+="(and "+ idProp+" known_"+idProp+")";
				}
				index+=1;
			}
			else {
				instanceSign+=signature.charAt(i);
			}
		}
		String idR = this.getID(time);
		factBuild.addRules("(declare-const "+idR+" Bool)\n");
		factBuild.addRules("(assert (= "+idR+" "+instanceSign+" ))\n");
	}
	
	private boolean caseNotPred(String instance)
	{
		if(instance.length()>5) {
			return (instance.substring(instance.length()-6,instance.length()-1).equals("(not "));
		}
		return false;
	}

	

}
