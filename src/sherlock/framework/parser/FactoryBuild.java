package sherlock.framework.parser;

import java.util.ArrayList;

public class FactoryBuild 
{
	ArrayList<Factory> propFactory;
	ArrayList<Factory> rulesFactory;
	private String modelProp,modelRules;
	
	public FactoryBuild(ArrayList<Factory> propFactory,ArrayList<Factory> rulesFactory)
	{
		this.propFactory = propFactory;
		this.rulesFactory = rulesFactory;
		this.modelProp = "";
		this.modelRules = "";
	}
	
	public FactoryBuild()
	{
		this.propFactory = new ArrayList<>();
		this.rulesFactory = new ArrayList<>();
		this.modelProp = "";
		this.modelRules = "";
	}
	
	public String generate(int timeLimit)
	{
		for(Factory pFactory : this.propFactory) {
			for(int i=0;i<=timeLimit;i++) {
				pFactory.generate(this,i);
			}
		}
		for(Factory rFactory : this.rulesFactory) {
			for(int i=0;i<=timeLimit;i++) {
				rFactory.generate(this,i);
			}
		}
		return getModel();
	}

	public ArrayList<Factory> getPropFactory() {
		return propFactory;
	}

	public void addPropFactory(Factory propFactory) {
		this.propFactory.add(propFactory);
	}

	public ArrayList<Factory> getRulesFactory() {
		return rulesFactory;
	}

	public void addRulesFactory(Factory rulesFactory) {
		this.rulesFactory.add(rulesFactory);
	}
	
	public String getModel() 
	{
		return this.modelProp+this.modelRules;
	}
	
	public boolean modelPropContains(String def) 
	{
		return this.modelProp.contains(def);
	}
	
	public void addProp(String def)
	{
		this.modelProp+=def;
	}
	
	public void addRules(String def)
	{
		this.modelRules+=def;
	}
	
	
	
}
